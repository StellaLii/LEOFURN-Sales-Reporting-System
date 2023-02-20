# Test Plan

**Author**: \<Team 181\>

## 1 Testing Strategy

### 1.1 Overall strategy

- unit-testing on each model class.
- integration-testing on fragment level classes.
- system-testing on UI level
- regression-testing on UI level (TBD)

### 1.2 Test Selection

System-testing: 
- use black-box's Equivalence partitioning technique to valid the inputs.
- use while-box's Condition Coverage tehnique to cover difference user input scenarioes.

Unit-testing:
- use block-box's Equivalence partitioning technique to valid the inputs

Integration-testing:
- use while-box's Condition Coverage tehnique to cover difference user input scenarioes.


### 1.3 Adequacy Criterion

- All tests are based on its funtional coverage as passing criteria.

### 1.4 Bug Tracking

- Since we don't plan/enough time to do mutiple version of App, bug tracking is TBD now.

### 1.5 Technology

- We intend to use JUnit for the unit-testing and manual test on the UI(System level) testing.

## 2 Test Cases

|  Test Case    | Purpose  |Why Necessary  |Expected Output   | Actual Output  |Pass/Faill |
|  ----  | ----  |----  |----  |----  |----  |
| Start the App   | Test if the app can start. |Check if the program is ready to run |The app is started |The app is started |✅ |
| Enter the Main Menu  | Test if the user can enter the Main Menu and three buttons are listed. |Check the function of Main Menu |Three buttons list |Three buttons list |✅ |
| Click "Add Job" button   | Test if the user can jump into Job menu. |Check the function of "Add Job" button. |Jump into Job menu |Jump into Job menu |✅ |
| Click "Compare Jobs" button  | Test if the user can jump into Job menu. |Check the function of "Compare Jobs" button.  |Jump into Compare Jobs menu |Jump into  Compare Jobs menu |✅  |
| Click "Add/Update Weight" button  | Test if the user can jump into Weight menu. |Check the function of "Add/Update Weight" button.  |Jump into Weight menu |Jump into Weight menu |✅  |
| Check "Current Job" Button in Job Menu  | Test if the user can mark current job |Check the function of "Current Job" Button in Job Menu  |"Current job" button is checked |"Current job" button is checked |✅   |
| Input "Title" in Job Menu  | Test if the Title can input | Check the function of input of Title|Job title is inputed and saved |Job title is inputed and saved |✅   |
| Input "Company" in Job Menu | Test if the Company can input|Check the function of input of Company | Company is inputed and saved|Company is inputed and saved | ✅  |
|  Input "Location" in Job Menu  | Test if the Location can input |Check the function of input of Location |Location is inputed and saved |Location is inputed and saved |✅ |
|Click "Compensation Package" button in Job Menu  | Test if the user can jump into Packge menu. |Check the function of "Compensation Package" button |Jump into Packge menu |Jump into Packge menu |✅ |
| Click "←" in Job Menu  |  Test if the user can go back to Main Menu|Check the function of "←" button | Jump into Main Menu|Jump into Main Menu|✅ | 
| Input "COL"(Cost of Living) in Packge menu |Test if the COL can input |Check the function of input of COL | COL is inputed|COL is inputed|✅ |
| Input "YS"(Yearly Salary) in Packge menu |Test if the YS can input |Check the function of input of YS | YS is inputed|YS is inputed|✅ |
| Input "YB"(Yearly Bonus) in Packge menu |Test if the YB can input |Check the function of input of YB | YB is inputed|YB is inputed|✅ |
| Input "RB"(Retirement Bonus) in Packge menu |Test if the RB can input |Check the function of input of RB | RB is inputed|RB is inputed|✅ |
| Input "RS"(Relocation Stipend) in Packge menu |Test if the RS can input |Check the function of input of RS | RS is inputed|RS is inputed|✅ |
| Input "RSUA"(Restricted Stock Unit Award) in Packge menu |Test if the RSUA can input |Check the function of input of RSUA | RSUA is inputed|RSUA is inputed|✅ |
| Click “Save Job” button in Package menu | Test if the job package information can be saved |Check the function of “Save Job” button in Package menu |Job package is saved  |Job package is saved |✅ |
| Click "←" in Package Menu  |  Test if the user can go back to Job Menu|Check the function of "←" button | Jump into Job Menu|Jump into Job Menu|✅ | 
| Input "YSW"(Yearly Salary Weight) in Weight menu |Test if the YSW can input |Check the function of input of YSW | YSW is inputed|YSW is inputed|✅ |
| Input "YBW"(Yearly Bonus Weight) in Weight menu |Test if the YBW can input |Check the function of input of YBW | YBW is inputed|YBW is inputed|✅ |
| Input "RBW"(Retirement Bonus Weight) in Weight menu |Test if the RBW can input |Check the function of input of RBW | RBW is inputed|RBW is inputed|✅ |
| Input "RSW"(Relocation Stipend Weight) in Weight menu |Test if the RSW can input |Check the function of input of RSW | RSW is inputed|RSW is inputed|✅ |
| Input "RSUW"(Restricted Stock Unit Weight) in Weight menu |Test if the RSUW can input |Check the function of input of RSUW | RSUW is inputed|RSUW is inputed|✅ |
| Click “Update weight” button in Weight menu | Test if the weight information is updated |Check the function of "Update weight" button in Weight menu |Weight setting is updated|Weight setting is updated|✅ |
| Click "←" in Weight Menu  |  Test if the user can go back to Main Menu|Check the function of "←" button | Jump into Main Menu|Jump into Main Menu|✅ | 
| Select two jobs in Compare Jobs Menu | Test if the saved jobs can be selected | Check the function of select buttion in Compare Jobs Menu| Two jobs are selected|Two jobs are selected|✅ |
| Click "Compare Jobs" button in Compare Jobs Menu|Test if the user can jump into two jobs comparison detail page|Check the function of "Compare Jobs" button in Compare Jobs Menu|Jump into jobs comparison detail page | Jump into jobs comparison detail page|✅ |
| Click "←" in Compare Jobs Menu|Test if the user can go back to Main Menu|Check the function of "←" button | Jump into Main Menu|Jump into Main Menu|✅ | 
|   |  | | | | |


