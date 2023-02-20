# Installation and User Instructions
## JobComparison App
### Andriorid/App
![mainmenu](/GroupProject/Design-Team/Image/UserManual/mainmenu.PNG)

03/2022
Version 2.0

## DISCLAIMER
Copyright © 20YY by [Gatech CS6300 SP22 Team181]

All rights reserved. No part of this publication may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the publisher. For permission requests, write to the publisher, addressed “Attention: Permissions Coordinator,” at the address below.

## Document Revisions
|  Date   | Version Number  |Document Change  |
|  ----  | ----  |----  |
| 03/05/2022  | 1.0 |Initial Draft |
| 03/14/2022  | 2.0 | Add Weight and rankingScore feature |

## Table of Contents
- [Preface](#heading)
  * [Description of the User](#sub-heading)
  * [Explanation of Safety Warnings](#sub-heading-1)
  * [Obtaining Documentation and Information](#sub-heading-1)
    + [Internet](#sub-sub-heading)
- [Description of the product](#heading-1)
  * [Purpose of the Product](#sub-heading-1)
  * [Product Overview](#sub-heading-1)
  * [Product Constraints](#sub-heading-1)
  * [System Environment](#sub-heading-1)

- [User Interface](#heading-2)
  * [Main Menu](#sub-heading-2)
    + [Add Job button](##sub-heading-1)
    + [Compare Jobs Button](#sub-heading-2)
    + [Add/Update Weight button](#sub-heading-2)

- [Release](#heading-2)
- [Related Document](#heading-2)



<!-- toc -->

## 1 Preface
### 1.1 Description of the User
This is for users who intend to compare multiple job offers.

It is a simple user system, and intuitive. No communication or saving between devices is necessary.
### 1.2 Explanation of Safety Warnings
*DANGER!*

Danger indicates a hazard with a high level of risk which, if not avoided, will result in death or serious injury
*WARNING!*

Warning indicates a hazard with a medium level of risk which, if not avoided, could result in death or serious injury.
*CAUTION!*

Caution indicates a hazard with a low level of risk which, if not avoided, could result in minor or moderate injury.

### 1.3 Obtaining Documentation and Information
#### 1.3.1 Internet
The latest version of the documentation is available at the following address:
https://github.gatech.edu/gt-omscs-se-2022spring/6300Spring22Team181/tree/master/GroupProject/JobCompare6300


## 2 Description of the product
### 2.1 Purpose of the Product
A simple, single-user job offer comparison app helps  to compare job offers with benefits, in different locations, and other aspects beyond salary.

### 2.2 Product Overview
- This application is designed for one user per installation only. This application cannot track multiple users' settings.
- No registration and login process for this application.
- All classes in this application will be unit tested.
- This application will run on a phone or tablet device based on the Android system.
- Cost of living index will also come from user input

### 2.3 Product Constraints
- The user will have only one current job.
- The user will have 100 job offers at maximum.
- If the user wants to use this job comparison system, the user must have at least one current job and one job offer, or more than two job offers.
- Current job and job offers will be stored in a local database of SQLite
- No connection is needed to an external database.

### 2.4 System Environment
The application must run on Android OS and at least support the OS version "API 29: Android 10.0 (Q)". The application must interact with a SQLite database to track the state of our classes between runtimes.


## 3 User Interface
### 3.1 Main Menu
Main access point of the App. It includes FOUR buttons.
- Add Job
- COMPARE JOBS
- Add/Update WEIGHT

![mainmenu](/GroupProject/Design-Team/Image/UserManual/mainmenu.PNG)

#### 3.1.1 Add Job button
1. Push this button will bring user 
- a. enter or edit current job detail
- b. enter job offers

2. When choosing to enter current job details, a user will be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
  * Title
  * Company
  * Location (entered as city and state)
  * Compesation Button:
    + Cost of living in the location (expressed as an index)
    + Yearly salary
    + Yearly bonus
    + Retirement benefits (as percentage matched) (Given as Integer 0-100)
    + Relocation stipend
    + Restricted stock unit award (expressed as a lump sum vested over 4 years)
3. When choosing to enter job offers, a user will be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
4. After entering the job details, users will be able to either save the job offer details or cancel. If duplicate current job saved, a toast warning will pop up.
5. Job detail UI 

![job](/GroupProject/Design-Team/Image/UserManual/CurrentJob.PNG)

![Package](/GroupProject/Design-Team/Image/UserManual/conflict_current_job.PNG)

6.  After users fill in all the required information, the Save Job button will store DB into SQLite database. and Yearly Salary, Yearly Bonus will readjust based on the cost of living index. RSUA will compute into each year value while Retirement Benefit will convert to per year base value.

#### 3.1.2 Adjust Comparison button
1. Adjust Comparison button is used for the user to define his/her preference when the system calculates job ranking score. 
2. When adjusting the comparison settings, the user can assign integer weights to:
- Yearly salary
- Yearly bonus
- Retirement benefits
- Relocation stipend
- Restricted stock unit award
3. If no weights are assigned, all factors are considered equal.
4. Adjust Comparison UI.

![Weight](/GroupProject/Design-Team/Image/UserManual/addWeight.PNG)

#### 3.1.3 Compare Jobs  Button
1. This button will be enabled if and only if there are 2 or more jobs enter in the system.
2. Push this button a user will be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
3. Users can select two out of the list to see future details.Our app supports more than 2 jobs.

4. Compare Jobs UI:

![RankJobMenu](/GroupProject/Design-Team/Image/UserManual/Ranking.PNG)

5. Compare 2 Jobs UI:

![CompareJob](/GroupProject/Design-Team/Image/UserManual/compareJob.PNG)

6. Rank score is based on this formula:
W1*AYS + W2*AYB + W3*RS + W4*(RPB * AYS / 100) + W5*(RSUA / 4)
- AYS = yearly salary adjusted for cost of living
- AYB = yearly bonus adjusted for cost of living
- RBP = retirement benefits percentage
- RS = relocation stipend
- RSUA = restricted stock unit award
- W1-5 are its associated weight value/Sum of each category

## 4 Release
### 4.1 Release Method
A release notification will be sent out for users when there is a new release commit into github.
### 4.2 Planned release
|  Term   | Meaning  |
|  ----  | ----  |
| v1.0  | Initial version, only have initial UI setup|
| v2.0  | Add Adjust comparison class/add rank score calculation/add current job indication |
| v3.0  | Some UI optimizations (TBD) |

## 5 Related Document
All related documents are available at the following address:
https://github.gatech.edu/gt-omscs-se-2022spring/6300Spring22Team181/tree/master/GroupProject/Docs
|  #   | Document Title  | Version  |Author  |
|  ----  | ----  |----  |----  |
| 1  | UserManal |2.0  |Team181 |
| 2  | DesignDocument |1.0 |Team181 |
| 3  | TestPlan |1.0  |Team181 |
| 4  | UserCase |1.0  |Team181 |
