<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update the Employee</title>
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
<h2>Update the Employee</h2>
<h3>in the department "${department.name}":</h3>
<form:form method="post" modelAttribute="employee" action="${context}/employees/update">
    <table>
        <tr>
            <td><form:label path="id">ID</form:label></td>
            <td><form:input path="id" readonly="true" cssClass="readonly"/></td>
            <td><form:errors path="id" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="idDepartment">Department</form:label></td>
            <td>
                <form:select path="idDepartment">
                    <form:options items="${departments}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
            <td><form:errors path="idDepartment" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First Name</form:label></td>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>
            <td><form:label path="middleName">Middle Name</form:label></td>
            <td><form:input path="middleName"/></td>
            <td><form:errors path="middleName" cssClass="error"/></td>
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
            <td colspan="3"><input type="submit" value="Update"></td>
        </tr>
    </table>
</form:form>
<a href="${context}/employees/${department.id}">Cancel</a>
</body>
</html>
