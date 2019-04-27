<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update the Department</title>
    <style>
        .colortext {
            background-color: #eeeeee;
            color : #888888;
        }
    </style>
</head>
<body>
<h2>Update the Department:</h2>
<form:form method="post" modelAttribute="department" action="/departments/update">
    <table>
        <tr>
            <td><form:label path="id">ID</form:label></td>
            <td><form:input path="id" readonly="true" cssClass="colortext"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Update"></td>
        </tr>
    </table>
</form:form>
<a href="/departments/all">Cancel</a>
</body>
</html>
