#### This includes all the non-functional requirements of the system.

##### Security 
- This is a simple user app which is mainly for local use only. It won’t allow have any internet connections to outside world, therefore it will be safe from most of the attacks which occur due to network communication.
- The app will not need too much permissions from the user, only needed permission might be of `storage` to save database file. 

##### Reliability 
- We will validate/constrain all the user input types to avoid any invalid input crashing the app. And we assume the store server is reliable in our project.

##### Maintainability 
- We assume the store server is self-maintained by itself. Other than that, we don't think this app is complicated to maintain.
- Addition of new features will need newer releases. 
