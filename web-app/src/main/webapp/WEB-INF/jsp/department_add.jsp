<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add new Department</title>
</head>
<body>
<h2>Add a new Department:</h2>

<form:form method="post" modelAttribute="department" action="/departments/add">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Add department"/></td>
        </tr>
    </table>
</form:form>
<a href="all">Cancel</a>
</body>
</html>
