# Design Discussion

## Part 1. Individual Designs    
### Design 1
![jli3059](/GroupProject/Design-Team/Image/jli3059.png)
#### **Pros**
Design 1 from Jianan Li is considering every detail of the requirements of Assignment 5. The design takes every limitation into account. The design also uses appropriate relations to connect each class. She even uses association class to show an association relationship between two other classes. 
#### **Cons**           
Jianan's design is not very concise. In actual software design, the application should be user-friendly. Being simple is very important. Meanwhile, this design forgets to implement the function of sorting all jobs in the list.
### Design 2
![schen781](/GroupProject/Design-Team/Image/schen781.PNG)
#### **Pros**
Shiming’s design is simple and concise. He breaks down all the requirements into small classes which simplifies each class’s implementation and maintenance effort. It can show the overall feature of this App design from the chart. 
#### **Cons**   
Shiming’s design lacks a clear relationship between weight and CompareJobs. And it creates some complexity of the Job class which needs to handle the rankingScore calculation.

### Design 3
![mzubair6](/GroupProject/Design-Team/Image/mzubair6.png)
#### **Pros**
Zubair's UML diagram has tried to cover all the requirements with as minimal classes as possible. 
This design uses an innovate way to use weak association for `Comaprison`class which only will be created 
when the comparison is made. 

The relationships among classes are well defined as well. 

#### **Cons**
This design has delegated a few details on implementation phase and doesn't provide any opinion how these shall be implemented. 
For example calss `Job_Rank_Comparison` and `Job_offer` can both implement score calculation. 

&nbsp;
## Part2. Team Design
![design](/GroupProject/Design-Team/Image/design.png)
For team design, we combine all the strengths of the team. After the discussion, our team decides to use Shiming’s design idea. The job class covers both current job and job offer, and we use the “IsCurrentJob” attribute to distinguish if it is current job or job offers. In order to make the “job” class concise, we build the “Package” class to record offer package details and use the “Rank” class to calculate the job score. And we also change the compareJobs class’s attribute from List of Job instances into List of Rank instances to further simplify the relationship and implementation effort.

&nbsp;
## Part3. Summary
From P3L2 A Tale of Analysis and Design, we know the proper steps to design software, such as analyzing requirements, refining classes and attributes, adding attributes, identifying operations, adding and refining relationships, and refining the whole class diagram. In terms of teamwork, every team member presents and explains the UML diagram and the design idea. According to our working experience, we have decided to make our final software design as straightforward as possible for user-friendly. 