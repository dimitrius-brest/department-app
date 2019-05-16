<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update the Department</title>
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
<h2>Update the Department:</h2>
<form:form method="post" modelAttribute="department" action="${context}/departments/update">
    <table>
        <tr>
            <td><form:label path="id">ID</form:label></td>
            <td><form:input path="id" readonly="true" cssClass="readonly"/></td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Update"></td>
        </tr>
    </table>
</form:form>
<a href="${context}/departments/all">Cancel</a>
</body>
</html>
