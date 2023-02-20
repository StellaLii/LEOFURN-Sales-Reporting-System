# Use Case Model

**Author**: team-181

## 1 Use Case Diagram
![schen781](/GroupProject/Design-Team/Image/use_case_model.png)


## 2 Use Case Descriptions



Add Job:
- *Requirements: This allows the user to add and edit his/her current job. or add new offer
- *Pre-conditions: This has to be the first job a user adds into the system.
- *Post-conditions: User must fill up this information before his/her hits the “save” button.
Title
Company
Location (entered as city and state)
Compensation Package
- *Scenarios: When a user opens the app, he/she will request to add the current job to start with if job stores in the system are 0. Otherwise, a user only can edit his or her current job. Once a user completes the information, “save” will store the job into the store server while “cancel” will return to the main menu. After the user hit “save” and it saves successfully, a new window will pop up asking whether the user wants to add more new offers. “Yes” will lead to add new job case while “No” will return to the main menu.



Adjust the comparison settings:
- *Requirements: This allows the user to put in how he/she wants to weigh each compensation in the job. 
- *Pre-conditions: There has to be 2 or more jobs stored in the system.
- *Post-conditions: A user needs to hit the “save” button to update the weight settings otherwise the system will use the default number(1).
- *Scenarios: When a user has entered 2 or more job offers, the “Adjust” button will be enabled. That means a Weight instance is created. And it will contain a default weight value(1).  If the user decides to change it, he/she will see a form with a list of compensations. Once it is filled, “Save” will store those attributes in the system and return to the main menu while “cancel” will return to the main menu.



Compare Job:
- *Requirements: This will rank all the jobs based on the adjustment number.
- *Pre-conditions: There has to be 2 or more jobs stored in the system.
- *Post-conditions: if there is a tie, it will show the jobs based on its enter order. Current job will always be highlighted. 
- *Scenarios: When a user has entered 2 or more job offers, the “Compare” button will be enabled. That means a compare instance is created.  If the user decides to compare, the system will create a rank instance with updated rank score associated with each job. And it will be stored in a job list with descending order as an attribute of  Compare instance. 



Display Job List:
- *Requirements: This allows the user to see a sorted ranking job table with minimal information based on the adjustment. And a user can future select 2 of them to see more detail.
- *Pre-conditions: There has to be 2 or more jobs stored in the system. 
- *Post-conditions: CompareJob case has to be run successfully.
- *Scenarios: A user will see a sorted ranking table after he/she hits the “Compare” button. Next to each job, it will have a select button for the user to select 2 of them to show more detail. “Current job” by default is selected. If the user didn’t pick the second one and hit the “More” button, it printed an error message. “More” will switch to a new table with more job information compared side by side for the user to review. And a return button will return back to the Display Job List table page while a close button will return to the main menu.


Accept job:
- *Requirements: This allows the user to accept the offer and update current job and remove the rest of the new offers in the system. 
- *Pre-conditions: There has to be 2 or more jobs stored in the system. 
- *Post-conditions: Once a user selects a job by hitting the accept button, the system will update the current job to that job and remove the rest of jobs from the store server. That case, the next time the user uses this system, he doesn’t need to start over again.
- *Scenarios: TBD, this is an optional feature we want to put in the app.
