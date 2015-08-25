<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCJ MVC Example</title>
</head>
<body>
<sf:form method="POST" modelAttribute="loginInfo" action="/gcj-mvc/onLogin">
   <table>
    <tr>
        <td><sf:label path="userId">User Id</sf:label></td>
        <td><sf:input path="userId" /></td>
    </tr>
    <tr>
        <td><sf:label path="password">Password</sf:label></td>
        <td><sf:input path="password" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</sf:form>

<h1>${error}</h1>
</body>
</html>