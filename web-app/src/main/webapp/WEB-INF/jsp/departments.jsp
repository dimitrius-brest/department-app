<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Departments</title>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}"/>  <!-- Root directory of application -->
<h2>Departments:</h2>
<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>Average Salary</td>
        <td>has employees</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach var="department" items="${departments}">
        <tr>
            <td>${department.id}</td>
            <td><a href="${context}/employees/${department.id}">${department.name}</a></td>
            <td>${department.averageSalary}</td>
            <td>${department.hasEmployees}</td>
            <td>
                <form:form method="get" action="${context}/departments/update/${department.id}">
                    <input type="submit" value="Edit">
                </form:form>
            </td>
            <td align="center">
                <c:choose>
                    <c:when test="${department.hasEmployees}">Not Empty</c:when>
                    <c:otherwise>
                        <form:form method="post" action="${context}/departments/delete/${department.id}">
                            <input type="submit" value="Delete" onclick="this.disabled=true">
                        </form:form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="${context}/departments/add">Add new Department</a>
</body>
</html>
