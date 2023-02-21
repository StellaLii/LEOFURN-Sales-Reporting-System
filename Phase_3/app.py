from flask import Flask, render_template, request,url_for
#from flask_mysqldb import MySQL
import pandas as pd
import numpy as np

import pymysql
import os
db = pymysql.connect(host="localhost",
                     port = 3306,
                     user="gatechUser",
                     passwd="gatech030",
                     db="cs6400_2021_01_Team030")

app = Flask(__name__)
# todo: 1. add check constraint on create table sql schema, 2. add error-handing 3, group run sql together?
app.config['SQLALCHEMY_POOL_RECYCLE'] = 28700

@app.route('/view_holiday',methods=['GET', 'POST'])
def view_holiday():
    cur = db.cursor()
    cur.execute("SELECT day, holiday FROM holiday")
    res = list(cur.fetchall())
    cur.execute("SELECT MIN(day), MAX(day) FROM day")
    day_range = list(cur.fetchall())[0]
    if request.method=='POST':
        message = ''
        d = request.form.get('date')
        h = request.form.get('holiday')
        query = f"""INSERT holiday (day, holiday)
                    VALUES ("{d}","{h}");
                  """
        if d and h:
            try:
                code = cur.execute(query)
                message = f"Successfully add {h} on {d}"
                cur.execute("SELECT day, holiday FROM holiday")
                res_new = list(cur.fetchall())
                db.commit()
                cur.close()
                return render_template('view_holiday.html',holiday_list=res_new,message = message,min_date = day_range[0],max_date = day_range[1])
            except:
                pass
        message += "Insert fail, please input valid date and holiday name"
        cur.close() 
        return render_template('view_holiday.html',holiday_list=res,message = message,min_date = day_range[0],max_date = day_range[1])
    cur.close() 
    return render_template('view_holiday.html',holiday_list=res,min_date = day_range[0],max_date = day_range[1])


@app.route('/view_pop',methods=['GET', 'POST'])
def view_pop():
    cur = db.cursor()
    cur.execute("SELECT city_name, state, population, city_size FROM Population")
    res = list(cur.fetchall())
    if request.method=='POST':
        message = ''
        cityname = request.form.get('cityname')
        state = request.form.get('state')
        population = request.form.get('population')
        query = f"""UPDATE Population
                    SET population = {population},
                     city_size = CASE WHEN population<3700000 THEN 'Small'
                     WHEN population<6700000 THEN 'Medium'
                     WHEN population <9000000 THEN 'Large'
                     ELSE 'Extra Large' END
                     WHERE city_name = '{cityname}' and state = '{state}';
                  """
        if cityname and population and state:
            try:
                code = cur.execute(query)
                if code ==1: 
                    message = f"Successfully update population for {cityname}, {state}"
                    cur.execute("SELECT city_name, state,population, city_size FROM Population")
                    res_new = list(cur.fetchall())
                    db.commit()
                    cur.close()
                    return render_template('view_pop.html',city_list=res_new,message = message)
            except:
                pass
        message = "Update fail, please input valid city name and population"
        cur.close() 
        return render_template('view_pop.html',city_list=res,message = message)
    cur.close() 
    return render_template('view_pop.html',city_list=res)

@app.route('/view_report1' ,methods=['GET'])
def view_report1():
    cur = db.cursor()
    query = """
    SELECT Category.Category_name, SUM(Product.PID), MIN(Product.regular_price), ROUND(AVG(Product.regular_price),2), MAX(Product.regular_price)
    FROM Category
    LEFT JOIN Product_Category ON Category.Category_name = Product_Category.Category_name
    LEFT JOIN Product ON Product_Category.PID = Product.PID
    GROUP BY Category_name
    ORDER BY Category_name ASC;
    """
    cur.execute(query)
    res = list(cur.fetchall())
    cur.close() 
    return render_template('view_report1.html',cat_list=res)

@app.route('/view_report2' ,methods=['GET'])
def view_report2():
    cur = db.cursor()
    query = """
            WITH regular_sold AS
            (SELECT PID, product_name, regular_price, SUM(quantity_sold) AS totalunitatregularprice, SUM(quantity_sold*regular_price) AS reg_rev
            FROM
            (
            SELECT saleID, quantity_sold, day, sales.PID AS PID, product_name, regular_price, category_name
            FROM Sales, Product, product_category
            WHERE Sales.PID = Product.PID
            AND product_category.PID = sales.PID
            AND (Sales.PID, Sales.day) not in (SELECT PID, day FROM DiscountPrice)
            AND category_name = "Couches and Sofas"
            ) AS A
            GROUP BY PID
            ),
            discount_sold AS
            (SELECT PID, product_name, discount_price, SUM(quantity_sold) AS totalunitatdisrprice, SUM(quantity_sold*discount_price) AS dis_rev
            FROM
            (
            SELECT saleID, quantity_sold, sales.day AS day, sales.PID AS PID, product_name, discount_price, category_name
            FROM sales, product, discountprice, product_category
            WHERE Sales.PID = Product.PID
            AND product_category.PID = sales.PID
            AND Product.PID = DiscountPrice.PID
            AND sales.day = DiscountPrice.day
            AND sales.PID = DiscountPrice.PID
            AND category_name = "Couches and Sofas"
            ) AS B
            GROUP BY PID
            ),
            predicted_regular AS
            (
            SELECT PID, SUM(quantity_sold*regular_price) AS pre_rev1
            FROM
            (
            SELECT saleID, quantity_sold, day, sales.PID AS PID, product_name, regular_price, category_name
            FROM Sales, Product, product_category
            WHERE Sales.PID = Product.PID
            AND product_category.PID = sales.PID
            AND (Sales.PID, Sales.day) not in (SELECT PID, day FROM DiscountPrice)
            AND category_name = "Couches and Sofas"
            ) AS C
            GROUP BY PID
            ),
            predicted_discount AS
            (
            SELECT PID, SUM(quantity_sold*regular_price*0.75) AS pre_rev2
            FROM
            (
            SELECT saleID, quantity_sold, sales.day AS day, sales.PID AS PID, product_name, discount_price, category_name, regular_price
            FROM sales, product, discountprice, product_category
            WHERE Sales.PID = Product.PID
            AND product_category.PID = sales.PID
            AND Product.PID = DiscountPrice.PID
            AND sales.day = DiscountPrice.day
            AND sales.PID = DiscountPrice.PID
            AND category_name = "Couches and Sofas"
            ) AS D
            GROUP BY PID
            )
            SELECT regular_sold.PID, regular_sold.product_name, regular_price, (totalunitatregularprice + totalunitatdisrprice) AS totalunit, totalunitatregularprice, totalunitatdisrprice, round((reg_rev + dis_rev)) AS actualrev, round(pre_rev1+pre_rev2), round((reg_rev + dis_rev - pre_rev1 - pre_rev2)) AS diff
            FROM regular_sold, discount_sold, predicted_regular, predicted_discount
            WHERE regular_sold.PID = discount_sold.PID
            AND regular_sold.PID = predicted_regular.PID
            AND regular_sold.PID = predicted_discount.PID
            AND abs(reg_rev + dis_rev - pre_rev1 - pre_rev2) >= 5000
            GROUP BY regular_sold.PID
ORDER BY (reg_rev + dis_rev - pre_rev1 - pre_rev2) DESC
    """
    cur.execute(query)
    res = list(cur.fetchall())
    cur.close() 
    return render_template('view_report2.html',act_list=res)


@app.route('/view_report3',methods=['GET', 'POST'])
def view_report3_scripts():
    #create a cursor
    cur = db.cursor()
    # execute select statement to fetch data to be displayed in combo/dropdown
    cur.execute("""SELECT DISTINCT Store.state
                    FROM Sales
                    INNER JOIN Store ON Store.store_ID = Sales.store_ID""") 
    statelist = sorted(list(set([_[0] for _ in cur.fetchall()] )))

    # Filter's on
    if request.method=='POST':
        state_selected = request.form.get('state_selected') 
        # Filter
        query = f"""
        WITH reg_sales AS (
            SELECT Store.store_ID, ROUND(SUM(quantity_sold *regular_price),2) AS reg_revenue,YEAR(sales.day) AS Year
            FROM Sales, Product,Store
            WHERE Sales.PID = Product.PID
            AND Sales.store_ID = Store.store_ID
            AND (Sales.PID, Sales.day) not in (SELECT PID, day FROM DiscountPrice)
            AND Store.state = '{state_selected}'
            GROUP BY YEAR(sales.day),store_ID),
            dis_sales AS (
            SELECT Store.store_ID, ROUND(SUM(quantity_sold *discount_price),2) AS dis_revenue,YEAR(sales.day) AS Year
            FROM Sales, Product,Store, DiscountPrice
            WHERE Sales.PID = Product.PID
            AND Sales.store_ID = Store.store_ID
            AND Product.PID = DiscountPrice.PID
            AND Sales.day = DiscountPrice.day
            AND Store.state = '{state_selected}'
            GROUP BY YEAR(sales.day),store_ID),
            store_list AS(
            SELECT *
            FROM store
            WHERE state = '{state_selected}')

            SELECT reg_sales.Year, store_list.store_ID, store_list.street_address, store_list.city_name,store_list.state,ROUND((IFNULL(reg_revenue,2)+IFNULL(dis_revenue,0)),2) AS Revenue
            FROM store_list
            LEFT JOIN reg_sales ON store_list.store_ID  = reg_sales.store_ID
            LEFT JOIN dis_sales ON reg_sales.store_ID = dis_sales.store_ID AND reg_sales.Year = dis_sales.Year
            ORDER BY Year ASC, Revenue DESC
        """
        cur.execute(query)
        res = list(cur.fetchall())
        cur.close() 
        return render_template("view_report3.html", statelist = statelist,
                                                    revenue_list = res)
    cur.close()      
    return render_template("view_report3.html", statelist = statelist)


@app.route('/view_report4' ,methods=['GET'])
def view_report4():
    cur = db.cursor()
    query = """
    SELECT groudhogday_furniture_sold, total_sold, ROUND(avg_sold,2), S.yr
    FROM (
    SELECT SUM(quantity_sold) AS groudhogday_furniture_sold, Year(day) AS yr
    FROM sales
    INNER JOIN product_category ON product_category.PID = sales.PID 
    WHERE product_category.category_name = "outdoor furniture"
    AND Month(day) = 2 AND Day(day) = 2 
    GROUP BY Year(day)
    ) AS S
    INNER JOIN (
    SELECT Year(day) AS yr, SUM(quantity_sold) AS total_sold, (SUM(quantity_sold)/365) AS avg_sold
    FROM (
    SELECT saleID, quantity_sold, day, sales.PID AS PID, category_name
    FROM sales 
    INNER JOIN product_category ON product_category.PID = sales.PID 
    WHERE Product_Category.category_name = "outdoor furniture"
    ) AS T
    GROUP BY Year(day)
    ) AS T
    ON S.yr = T.yr
    ORDER BY S.yr ASC; 
    """
    cur.execute(query)
    res = list(cur.fetchall())
    cur.close() 
    return render_template('view_report4.html',out_list=res)

@app.route('/view_report5',methods=['GET', 'POST'])
def view_report5_scripts():
    #create a cursor
    cur = db.cursor()
    #execute select statement to fetch data to be displayed in combo/dropdown
    cur.execute("""SELECT DISTINCT YEAR(Sales.day) AS Year,MONTH(Sales.day) AS Month FROM Sales
                    """) 
    #fetch all rows ans store as a set of tuples 
    results = cur.fetchall()
    yearlist = sorted(list(set([_[0] for _ in results])))
    monthlist = sorted(list(set([_[1] for _ in results] )))
    # default: blank
    result_list = []
    # Filter's on
    if request.method=='POST':
        year_selected = request.form.get('year_selected')
        month_selected = request.form.get('month_selected')
        #print(year_selected,month_selected)
        query = f"""WITH cat_state_sales AS (
                  SELECT Year(Sales.day) as Year, Month(Sales.day) as Month, Category_name, Store.state, SUM(quantity_sold) as total_sold
                  FROM sales
                  JOIN product_category ON sales.PID = product_category.PID
                  JOIN store ON sales.store_ID = store.store_ID
                  WHERE Year(Sales.day)={year_selected} AND Month(Sales.day) = {month_selected}
                  GROUP BY 3,4)

                  SELECT * 
                  FROM cat_state_sales
                  WHERE (category_name, total_sold) in (SELECT Category_name, MAX(total_sold) FROM cat_state_sales GROUP BY 1)
                  ORDER BY category_name"""
        cur.execute(query)
        res = cur.fetchall()
        cur.close()
        return render_template("view_report5.html", yearlist = yearlist,
                                                    monthlist = monthlist,
                                                    result_list=res)
    cur.close()
    return render_template("view_report5.html", yearlist = yearlist,
                                                    monthlist = monthlist,
                                                    result_list=result_list)

@app.route('/view_report6',methods=['GET'])
def view_report6_scripts():
    #create a cursor
    cur = db.cursor()
    query = """WITH reg_sales AS (
                SELECT Year, city_size, sum(total_sales) AS regular_revenue
                FROM (
                SELECT YEAR(sales.day) AS Year, Population.city_size, quantity_sold*regular_price AS total_sales 
                FROM sales 
                JOIN Product ON Sales.PID = Product.PID
                JOIN Store ON Sales.store_ID = Store.store_ID
                JOIN Population ON  Store.city_name=Population.city_name AND Store.state = Population.state
                WHERE (Sales.PID, Sales.day) NOT IN (SELECT PID, day FROM DiscountPrice)
                )t 
                GROUP BY 1,2),
            dis_sales AS (
                SELECT YEAR(sales.day) AS Year, Population.city_size,SUM(quantity_sold*discount_price) AS discount_revenue
                FROM sales 
                JOIN Product ON Sales.PID = Product.PID
                JOIN Store ON Sales.store_ID = Store.store_ID
                JOIN Population ON Population.city_name = Store.city_name AND Store.state = Population.state
                JOIN discountprice ON Sales.PID =DiscountPrice.PID AND Sales.day = DiscountPrice.day
                GROUP BY 1,2)

            SELECT Year, ROUND(SUM(CASE WHEN city_size = 'Small' THEN total_rev ELSE 0 END),2) AS Small, 
                         ROUND(SUM(CASE WHEN city_size = 'Medium' THEN total_rev ELSE 0 END),2) AS Medium, 
                         ROUND(SUM(CASE WHEN city_size = 'Large' THEN total_rev ELSE 0 END),2) AS Large,
                         ROUND(SUM(CASE WHEN city_size = 'Extra Large' THEN total_rev ELSE 0 END),2) AS 'Extra Large'
            FROM (
                SELECT reg_sales.year, reg_sales.city_size, regular_revenue+discount_revenue AS total_rev
                FROM reg_sales
                LEFT OUTER JOIN dis_sales ON reg_sales.year = dis_sales.year AND reg_sales.city_size = dis_sales.city_size
                UNION 
                SELECT dis_sales.year, dis_sales.city_size, regular_revenue+discount_revenue AS total_rev
                FROM reg_sales
                RIGHT OUTER JOIN dis_sales ON reg_sales.year = dis_sales.year AND reg_sales.city_size = dis_sales.city_size
                ) tmp
            GROUP BY 1
            ORDER BY 1"""
    cur.execute(query)
    res = cur.fetchall()
    cur.close()
    return render_template("view_report6.html", result_list=res)


@app.route('/view_report7',methods=['GET'])
def view_report7():
    cur = db.cursor()
    cur.execute('SELECT DISTINCT(childcare_limit) FROM store ORDER BY 1')
    care_level = [c[0] for c in list(cur.fetchall())]
    cur.execute('SELECT @MAX_DATE := MAX(day) FROM day;')
    query = """
    WITH base AS(
      SELECT DISTINCT
      DATE_FORMAT(day,'%Y-%m') as YM
      FROM day
      WHERE DATEDIFF(@MAX_DATE,day)<=366
    ),
    sale_data AS(
    SELECT b.YM, childcare_limit, ROUND(SUM(total_sold),2) as total_sales
    FROM base b
    LEFT JOIN (
    SELECT DATE_FORMAT(Sales.day,'%Y-%m') as YM, Store.childcare_limit, SUM(Sales.quantity_sold*Product.regular_price) AS total_sold
    FROM Sales, Product, Store
    WHERE Sales.PID = Product.PID
        AND Sales.store_ID = Store.store_ID
        AND (Sales.PID, Sales.day) not in (SELECT PID, day FROM DiscountPrice)
        AND DATEDIFF(@MAX_DATE, Sales.day)<=366
    GROUP BY 1,2
    UNION ALL
    SELECT DATE_FORMAT(Sales.day,'%Y-%m') as YM, Store.childcare_limit, SUM(Sales.quantity_sold* discountprice.discount_price) AS total_sold
    FROM Sales, Product, Store, DiscountPrice
    WHERE Sales.PID = Product.PID
        AND Sales.store_ID = Store.store_ID
        AND Product.PID = DiscountPrice.PID
        AND Sales.day = DiscountPrice.day
        AND DATEDIFF(@MAX_DATE,Sales.day)<=366
    GROUP BY 1,2) t ON b.YM = t.YM
    GROUP BY 1,2
    ORDER BY 1,2)
    

    SELECT YM
    """
    col_list = []
    for c in care_level:
        if c == 0:
            col_name = 'No_childcare'
        else:
            col_name = 'Childcare_'+str(c)+'_min'
        query+= f',SUM(CASE WHEN childcare_limit = {c} THEN total_sales ELSE 0 END) AS "{col_name}"'
        col_list.append(col_name)
    query += " FROM sale_data GROUP BY 1 ORDER BY 1"
    
    cur.execute(query)
    res = list(cur.fetchall())
    cur.close()
    return render_template('view_report7.html',clevel =col_list ,res_list=res)


@app.route('/view_report8' ,methods=['GET'])
def view_report8():
    cur = db.cursor()
    query = """
    WITH store_sale_cat AS(
    SELECT Store.store_ID, Product_Category.category_name as category_name,
    SUM(Sales.quantity_sold) as total_sold
    FROM Store
    INNER JOIN Sales ON Store.store_ID = Sales.store_ID
    INNER JOIN Product ON Sales.PID = Product.PID
    INNER JOIN Product_Category ON Product.PID = Product_Category.PID
    GROUP BY Store.store_ID, Product_Category.category_name)

    SELECT category_name, 'Restaurant' as st ,SUM(total_sold) as total_sold
    FROM store_sale_cat
    WHERE store_ID IN (SELECT store_id FROM Store WHERE restaurant = '1')
    GROUP BY 1
    UNION
    SELECT category_name, 'Non-Restaurant' as st ,SUM(total_sold) as total_sold
    FROM store_sale_cat
    WHERE store_ID NOT IN (SELECT store_id FROM Store WHERE restaurant = '1')
    GROUP BY 1
    ORDER BY 1,2
    """
    cur.execute(query)
    res = list(cur.fetchall())
    cur.close() 
    return render_template('view_report8.html',resimp_list=res)

@app.route('/view_report9' ,methods=['GET'])
def view_report9():
    cur = db.cursor()
    query = """

    WITH camp_sale AS
    (SELECT Sales.PID, SUM(quantity_sold) AS sold_dur_camp
     FROM Sales
     JOIN discountprice ON Sales.day = discountprice.day AND sales.PID = discountprice.PID
     WHERE sales.day IN (SELECT DISTINCT day FROM adcampaign)
     GROUP BY 1),
     non_camp_sale AS
    (SELECT Sales.PID, SUM(quantity_sold) AS sold_out_camp
     FROM Sales 
     JOIN discountprice ON Sales.day = discountprice.day AND sales.PID = discountprice.PID
     WHERE sales.day NOT IN (SELECT DISTINCT day FROM adcampaign)
     GROUP BY 1),
     diff_t AS (
     SELECT product.PID, product_name, IFNULL(sold_dur_camp,0) AS sold_during_campaign ,IFNULL(sold_out_camp,0) AS sold_outside_campaign,(IFNULL(sold_dur_camp,0)- IFNULL(sold_out_camp,0)) AS diff 
     FROM product 
     LEFT JOIN camp_sale ON product.PID = camp_sale.PID
     LEFT JOIN non_camp_sale ON product.PID = non_camp_sale.PID
     )

    SELECT *
    FROM (
    (SELECT *
    FROM diff_t
    ORDER BY diff DESC LIMIT 10)
    UNION 
    (SELECT *
    FROM diff_t
    ORDER BY diff LIMIT 10)) t
    ORDER BY diff DESC
    
    """
    cur.execute(query)
    res = list(cur.fetchall())
    cur.close() 
    return render_template('view_report9.html',aca_list=res)

def generate_stat():
    res = []
    cur = db.cursor()
    directory = "./overview_stats/"
    q_list = [i for i in os.listdir(directory) if i.endswith('sql')]
    for q in q_list:
        file = directory + q
        with open(file, 'r') as sql_file:
            cur.execute(sql_file.read())
            r = [q.split(".")[0]] + [cur.fetchall()[0][0]]
            res.append(r)
    cur.close()
    return res

@app.route('/', methods=['GET'])
def home():
    stat = generate_stat()
    return render_template('view_stats.html', stat_list=stat)

if __name__ == '__main__':
    app.run()

# 
# if __name__ == '__main__':
#     app.run(debug=True)
