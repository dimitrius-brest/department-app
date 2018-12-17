<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Departments</title>
</head>
<body>
<h2>Departments:</h2>
<table border="1">
    <tr>
        <td>id</td><td>name</td>
    </tr>
    <c:forEach var="department" items="${departments}">
        <tr>
            <td>${department.get(0)}</td>
            <td>${department.get(1)}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
