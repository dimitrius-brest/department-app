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
        <c:when test="${department == null}">All Employees:</c:when>
        <c:otherwise>Employees of the Department "${department.name}":</c:otherwise>
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

<h3>Search employees by birth date:</h3>
<form:form method="post" modelAttribute="date_range" action="/employees/search/${department.id}">
    <form:label path="startDate">In the range from:</form:label>
    <form:input path="startDate" type="date"/>
    <form:label path="endDate">to:</form:label>
    <form:input path="endDate" type="date"/>
    <input type="submit" value="Search">
</form:form>

<c:if test="${department != null}">
    <br>
    <a href="/employees/add/${department.id}">Add new Employee to the Department</a>
    <br>
</c:if>
<br>
<a href="/departments/all">Back to Departments list</a>
</body>
</html>
