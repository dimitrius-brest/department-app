<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<h2>Employees:</h2>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Department ID</td>
        <td>First Name</td>
        <td>Middle Name</td>
        <td>Last Name</td>
        <td>Birth Date</td>
        <td>Salary</td>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.idDepartment}</td>
            <td>${employee.firstName}</td>
            <td>${employee.middleName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.birthDate}</td>
            <td>${employee.salary}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/departments/all">Back to Departments list</a>
</body>
</html>
