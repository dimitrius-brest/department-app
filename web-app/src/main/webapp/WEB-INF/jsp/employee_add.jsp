<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Employee</title>
    <style>
        .colortext {
            background-color: #eeeeee;
            color : #888888;
        }
    </style>
</head>
<body>
<h2>Add a new Employee</h2>
<h3>to the Department "${department.name}"</h3>

<form:form method="post" modelAttribute="employee" action="/employees/add/${department.id}">
    <table>
        <tr>
            <td><form:label path="idDepartment">Department ID</form:label></td>
            <td><form:input path="idDepartment" readonly="true" cssClass="colortext"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First Name</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="middleName">Middle Name</form:label></td>
            <td><form:input path="middleName"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Last Name</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="birthDate">Birth Date</form:label></td>
            <td><form:input path="birthDate"/></td>
        </tr>
        <tr>
            <td><form:label path="salary">Salary</form:label></td>
            <td><form:input path="salary"/></td>
        </tr>
    </table>
</form:form>

<a href="/employees/${department.id}">Cancel</a>
</body>
</html>
