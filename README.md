#####Project RST - Web Services

### Where to find the project
Here is the Github repository :
 ```
https://github.com/opsilonn/Project_WebServices
 ```

### The structure of the project
The project consists of a multitude of folders, most notably :
* SQL scripts - files used to create and fill the Database (more on that later)
* Project_Web_Services.REST : Back-End side of the project, its main core
* Project_Web_Services.CLIENT : Front-End side of the project, the website if you will

Please note that we focused more on having all services available instead of having a proper interface.
This is why you can perform 100% of the requirements with POSTMAN, but only some of them are available and fully implemented on the front website.



## Getting Started
The programmation is fully completed, but we have a small requirement : creating the database.
Don't worry ! We've got you covered.


```
You'll need to create an SQL database.
Here are the file to help you create & fill the database :
Creating the Database : Project_WebServices\SQL scripts\databaseCreation.sql
Filling the Database : Project_WebServices\SQL scripts\databaseFill.sql

You'll need to open phpMyAdmin (and, notably, also open Wamp / Wamp / whatever you use).
Import the databaseCreation.sql FIRST, then the databaseFill.sql in the database web_services newly created.

Nice, we are now ready to go !
```

Another element is that you can test the REST api :
- with the website (no installation required), but not everything is implemented
- with POSTMAN, but you'll need to use the following Authorization :
   Basic Auth
   Username = test_username
   Password = test_password
 

## Using the REST API Documentation
Whenever an error occurs within the API, it will display you a link to its documentation.
 ```
 http://localhost:8080/Project_Web_Services.REST/rest/v1/api-doc
 ```

 To use it, please go to the following link `https://petstore.swagger.io/#/` and put in the search bar the link to the documentation. The Swagger website will then render the Swagger UI documentation.

From this documentation, you may want to try some requests by cliking on the button `Try it out` and by filling the forms. Please, be aware that if the class rest.resource.util.AuthorizationFilter is provided to JAX-RS, the website will not be able to access to the API's resources. In this case, you should first disable for a moment this class for your tests.


## Built With

* [Eclipse.Mars.2 Release (4.5.2)](https://www.eclipse.org/mars/) - The Java IDE
* [PhpMyAdmin 4.8.5](https://https://www.phpmyadmin.net) - The SQL database
* [Postman](https://learning.getpostman.com) - A software used to run some request tests
  


## Authors

It was made by the following Efrei Paris students :
* **BEGEOT Hugues** - [his Git repository](https://github.com/opsilonn)
* **BONI François** - [his Git repository](https://github.com/scorpionsdu78)
* **DULCHE Eddy** - [his Git repository](https://github.com/DulcheE)
* **KANN William** - [his Git repository](https://github.com/williamkann)

See also the list of [contributors](https://github.com/opsilonn/Project_WebServices/graphs/contributors) who participated in this project.

Note : we are currently in our 4th year (2019-20), in a Software Engineering cursus.
