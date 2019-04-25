<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${department_name == null}">All Employees:</c:when>
        <c:otherwise>Employees of the Department "${department_name}":</c:otherwise>
    </c:choose>
</h2>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Department ID</td>
        <td>First Name</td>
        <td>Middle Name</td>
        <td>Last Name</td>
        <td>Birth Date</td>
        <td>Salary</td>
        <td>Edit</td>
        <td>Delete</td>
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
            <td>
                <form:form method="get" action="/employees/update/${employee.id}">
                    <input type="submit" value="Edit">
                </form:form>
            </td>
            <td>
                <form:form method="post" action="/employees/delete/${employee.id}">
                    <input type="submit" value="Delete" onclick="this.disabled=true">
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/employees/add">Add new Employee</a>
<br>
<br>
<a href="/departments/all">Back to Departments list</a>
</body>
</html>
