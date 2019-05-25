## "Department" application
[![Build Status](https://travis-ci.org/dimitrius-brest/department-app.svg?branch=master)](https://travis-ci.org/dimitrius-brest/department-app)
[![Coverage Status](https://coveralls.io/repos/github/dimitrius-brest/department-app/badge.svg?branch=master)](https://coveralls.io/github/dimitrius-brest/department-app?branch=master)

### Description of the project
This project allows to deal with a list of Departments and Employees inside this Departments via web-browser. 

### How to build and deploy the project
1. Clone the project from repository.
2. Go to the root directory of the project ("/department-app/").
3. Make sure you have Maven installed. In command line write and run: `mvn clean install`
4. If build was successful, you will get two war-files:
   * /department-app/rest/target/**department-rest.war**
   * /department-app/web-app/target/**department-web.war**
5. Copy this two war-files to your Tomcat "webapps" directory. Start Tomcat if it's not yet.

### Running web-application
1. We presume you deployed the project at **localhost**. To test the REST API of the application, write in your browser:
   * http://localhost:8080/department-rest/departments/all
   * http://localhost:8080/department-rest/employees/all
<br>-- If everything is OK, you will get some JSON entities in both cases.
2. To start web-application, write in your browser:
   * http://localhost:8080/department-web/departments/all
3. That's it!

### Log files
Log file can be found in Tomcat "log" directory under the name:
* **department_rest_log_file.log** - for the REST layer
