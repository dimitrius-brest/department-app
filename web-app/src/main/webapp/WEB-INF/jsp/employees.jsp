<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Employees</title>
    <style>
        .error {
            color: #ff0000;
        }
        .search {
            color: darkgreen;
        }
    </style>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}"/>  <!-- Root directory of application -->
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
                <form:form method="get" action="${context}/employees/update/${employee.id}">
                    <input type="submit" value="Edit">
                </form:form>
            </td>
            <td>
                <form:form method="post" action="${context}/employees/delete/${employee.id}">
                    <input type="submit" value="Delete" onclick="this.disabled=true">
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>

<h3>Search employees by birth date:</h3>
<form:form method="post" modelAttribute="date_range" action="${context}/employees/search/${department.id}">
    <form:label path="startDate">In the range from:</form:label>
    <form:input path="startDate" type="date"/>
    <form:label path="endDate">to:</form:label>
    <form:input path="endDate" type="date"/>
    <input type="submit" value="Search">
    <span class="error">${invalid_range_message}</span>
    <span class="search">${search_message}</span>
</form:form>

<c:if test="${department != null}">
    <br>
    <a href="${context}/employees/add/${department.id}">Add new Employee to the Department</a>
    <br>
</c:if>
<br>
<a href="${context}/departments/all">Back to Departments list</a>
</body>
</html>
