<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Departments</title>
</head>
<body>
<h2>Departments:</h2>
<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>Average Salary</td>
        <td>has employees</td>
    </tr>
    <c:forEach var="department" items="${departments}">
        <tr>
            <td>${department.id}</td>
            <td>${department.name}</td>
            <td>${department.averageSalary}</td>
            <td>${department.hasEmployees}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
