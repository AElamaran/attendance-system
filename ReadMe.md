## Attendance System

# Backend- Spring Boot

* Use maria Db sql for database. So,use mariadb.jdbc
* TO talk with database use jpa in repositories.
* Use lombok for getter, setter, Constructors.
* For the Home page create crud in Student table. Which is include post, get, get by id, delete, put and search.
* for student management system handle custom excption handling.
* Create DTO reponse for common response  in exception hanling. 
* For validation use Jakarta and spring boot starter validation.
* did basic authentication by activate spring security.
* Did configuration in security filterchain for customized authentication, diable csrf, session management, handle jwt filter .
* For JWT use jjwt-aoi, jjwt-impl, jjwt-jackson.(Verson 0.12.5)
* Not Regitration done by here. So, create UserInput.sql for automatically fill the user table.
* valid credential are in the UserInput.sql file.
* Did not do bycryption, because do not use register function here.
* Handle Cross Origin

## Front-end

* Use Pure Html, CSS, JavaScript.
* First Use login.html file as a root file.
* Store JWT token in local storage by successfull Login.
* Successfull Login navigate to home page.
* Logout function delete the JWT token from local storage and navigate to Login page again.
* Did not Implement registration system which is done in backend.
* Use Visual Studio's Live Server to Api Connection

## Challenges
* cross origin issue.
* Jwt library version issue.
