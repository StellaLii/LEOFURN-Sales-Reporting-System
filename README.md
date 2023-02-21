# cs6400-2021-01-Team030

<img width="1650" alt="Interface" src="https://github.gatech.edu/storage/user/40466/files/26fcb980-9f57-11eb-97ea-1cd61daafbd2">
This web-based data reporting system is built using the Python Flask Framework and HTML.

## Features
Users can view overall statistics, view & edit population, view & edit holiday, and view analysis reports from the database.

## Prerequisites

Python >= 3.6  
Flask module >= 1.1.2  
PyMySQL >= 1.0.2

Refer to the PDF file "Team030 Project SetUp.PDF" in the repository for detailed setup and installation instructions.

## Configuring the application

In app.py:  
```
define(host = "localhost");
define(port = 3306/3307);
define(user="gatechUser");
define(passwd="gatech030");
define(db="cs6400_2021_01_Team030");
```
The configuration must match the database.

In phpMyAdmin, run the following query in database "cs6400_2021_01_Team030"
```
SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
```

## Running the application

After installing all the requirements and ensuring that the configurations are done correctly, run the app.

Run the application:  $ python app.py

## Deployment

This web application has the basic configuration for deployment and it is deployed on HTML.

## Developer

* __Zhaoqin Wang__  email: [zwang3210@gatech.edu](mailto:zwang3210@gatech.edu)
* __Yanlin Chen__  email: [ychen3402@gatech.edu](mailto:ychen3402@gatech.edu)
* __Weijia Leng__  email: [wleng7@gatech.edu](mailto:wleng7@gatech.edu)
* __Jianan Li__  email: [jli3059@gatech.edu](mailto:jli3059@gatech.edu) 
* __Guanyu Zhang__  email: [gzhang329@gatech.edu](mailto:gzhang329@gatech.edu) 


## Acknowledgement

Python Flask Framework - The web framework used  
phpMyAdmin - The database used




 
