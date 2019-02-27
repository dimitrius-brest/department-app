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
        <td>id</td>
        <td>idDepartment</td>
        <td>firstName</td>
        <td>middleName</td>
        <td>lastName</td>
        <td>birthDate</td>
        <td>salary</td>
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

</body>
</html>
