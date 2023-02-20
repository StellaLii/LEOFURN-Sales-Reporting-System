# Design Description

### When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offer or job offers are less than 2).  
Once user open the app, it will call startUp() and GUI will pop up 4 click buttons:
- (1) either to enter current Job (if jobNum=0) or eitedCurrentJob. (instantiate a Job Class Object)
- (2) Add NewJob offer (instantiate a Job Class Object)
- (3) Add weight for the compensations (instantiate a weight Class Object)
- (4) CompareJobs (if jobNum>=2, it will enable) (instantiate a CompareJobs Class Object)
     
&nbsp;    
### When choosing to enter current job details, a user will:
1. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
- i. Title
- ii. Company
- iii. Location (entered as city and state)
- iv. Cost of living in the location (expressed as an index)
- v. Yearly salary
- vi. Yearly bonus
- vii. Retirement benefits (as percentage matched) (Given as Integer 0-100)
- viii. Relocation stipend
- ix. Restricted stock unit award (expressed as a lump sum vested over 4 years)


Job class contain all these attributes and its associate method, and to make it cleaner, we use a Package class to keep track of all the compensation related attributes. To distinct whether this is current job or not, we add an isCurrentJob attribute in the Job class.

2. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

This should be easily handled by a GUI submit button and construct or deconstruct the Object.

&nbsp;
### When choosing to enter job offers, a user will:
1. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
2. Be able to either save the job offer details or cancel.
3. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
&nbsp;
### When adjusting the comparison settings, the user can assign integer weights to:
- Yearly salary
- Yearly bonus
- Retirement benefits
- Relocation stipend
- Restricted stock unit award
If no weights are assigned, all factors are considered equal.

We use a Weight Class to keep track of all these attributes. 

&nbsp;
### When choosing to compare job offers, a user will:
1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
2. Select two jobs to compare and trigger the comparison.
3. Be shown a table comparing the two jobs, displaying, for each job:
- i. Title
- ii. Company
- iii. Location 
- iv. Yearly salary adjusted for cost of living
- v. Yearly bonus adjusted for cost of living
- vi. Retirement benefits
- vii. Relocation stipend
- viii. Restricted stock unit award
4. Be offered to perform another comparison or go back to the main menu.

We create a Rank class with calculateJobScore() method to calculate either job's score and connect it with the Job class based on JobID. Then sortedRankingTable() method in CompareJobs class can create a table which based on the job score(descending order). displaySortedJobs() method will print out the ranked job offers. And displayCompareTwoJobs() method will only print out more detail of the two jobs User select. 

&nbsp;
### When ranking jobs, a job's score is computed as the weighted sum of:

AYS + AYB + RS + (RPB * AYS / 100) + (RSUA / 4)

where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
RBP = retirement benefits percentage
RS = relocation stipend
RSUA = restricted stock unit award

The rationale for the RSUA subformula is:
a. value of a restricted stock unit award vests over 4 years
b. average value of the restricted stock unit award per year (RSUA / 4)

For example, if the weights are 2 for the yearly salary, 2 for relocation stipend, and 1 for all other factors, the score would be computed as:

2/7 * AYS + 1/7 * AYB + 2/7 * RS + 1/7 * (RPB * AYS / 100) + 1/7 * (RSUA / 4)

We use Rank class to calculate and rank the job scores. And when the CompareJob Object is constructed, it will take the weight instance and job instance as inputs and calculate its job score. Then displayRanking() method will set its score into CompareJob class. 

### Here is our app conceptial view.

![app_conceptial_view](/GroupProject/Design-Team/Image/app_conceptial_view.png)

