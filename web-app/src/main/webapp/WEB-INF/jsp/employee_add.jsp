<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Add new Employee</title>
    <style>
        .readonly {
            background-color: #eeeeee;
            color : #888888;
        }
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}"/>  <!-- Root directory of application -->
<h2>Add a new Employee</h2>
<h3>to the Department "${department.name}"</h3>

<form:form method="post" modelAttribute="employee" action="${context}/employees/add/${department.id}">
    <table>
        <tr>
            <td><form:label path="idDepartment">Department ID</form:label></td>
            <td><form:input path="idDepartment" readonly="true" cssClass="readonly"/></td>
            <td><form:errors path="idDepartment" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First Name</form:label></td>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="middleName">Middle Name</form:label></td>
            <td><form:input path="middleName"/></td>
            <td><form:errors path="middleName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Last Name</form:label></td>
            <td><form:input path="lastName"/></td>
            <td><form:errors path="lastName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="birthDate">Birth Date</form:label></td>
            <td><form:input path="birthDate" type="date"/></td>
            <td><form:errors path="birthDate" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="salary">Salary</form:label></td>
            <td><form:input path="salary"/></td>
            <td><form:errors path="salary" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Add Employee" onclick="this.disabled=true"></td>
        </tr>
    </table>
</form:form>

<a href="${context}/employees/${department.id}">Cancel</a>
</body>
</html>
