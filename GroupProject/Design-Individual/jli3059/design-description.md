# Design Description

> Requirement 1: When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).    
### In order to satisfy this requirement, I designed User, Edit/EnterJobOffer, CurrentJob, ComparisonSetting, Comparement class with appropriate attributes. 
> Requirement 2: When choosing to enter current job details, a user will: Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of: Title, Company, Location (entered as city and state), Cost of living in the location (expressed as an index), Yearly salary, Yearly bonus, Retirement benefits (as percentage matched) (Given as Integer 0-100), Relocation stipend, Restricted stock unit award (expressed as a lump sum vested over 4 years), Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
### In CurrentJob class, besides all the attributes in the requirement2, I also add "isEmpty" attribute to show if the user add current job details. I also design Edit/EnterCurrentjob class, so I add detailed operations, such as save and cancel, in this class. 
> Requirement 3: When choosing to enter job offers, a user will: Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job. Be able to either save the job offer details or cancel. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
### In order to meet this requirement, I designed two class, JobOffer and Edit/EnterJobOffer. Edit/EnterJobOffer class is for all offers details. JobOffer class is to show whether the user has offers or not.
> Requirement 4: When adjusting the comparison settings, the user can assign integer weights to: Yearly salary, Yearly bonus, Retirement benefits, Relocation stipend, Restricted stock unit award. If no weights are assigned, all factors are considered equal.
### I use ComparisonSetting class to meet requirement 4. 
> Requirement 5: When choosing to compare job offers, a user will: Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated. Select two jobs to compare and trigger the comparison. Be shown a table comparing the two jobs, displaying, for each job: Title, Company, Location, Yearly salary adjusted for cost of living, Yearly bonus adjusted for cost of living, Retirement benefits, Relocation stipend, Restricted stock unit award. Be offered to perform another comparison or go back to the main menu.
### I design Comparison class to satisfy this requirement. In Comparison class, There are Job1 and Job2 attributes to be selected and compared. 
> Requirement 6: When ranking jobs, a job’s score is computed as the weighted sum of: AYS + AYB + RS + (RPB * AYS / 100) + (RSUA / 4)
### Rank class is designed for requirement 6.



