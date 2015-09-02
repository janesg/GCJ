<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Web</title>
</head>
<body>
<h1>${message}</h1>

<div>
<h2>Register User</h2>
<sf:form method="POST" modelAttribute="loginService" action="/SpringWeb/onRegistration">
    <fieldSet>
        <table cellspacing="0">
            <tr>
                <th><label for="userId">User Id:</label></th>
                <td><input type="text" name="userId" id="userId" size="10" maxLength="10"/></td>
            </tr>
            <tr>
                <th><label for="password">Password:</label></th>
                <td><input type="password" name="password" id="password" size="10" maxLength="10"/></td>
            </tr>
            <tr>
                <th><label for="fName">First Name:</label></th>
                <td><input type="text" name="fName" id="fName" size="20" maxLength="20"/></td>
            </tr>
            <tr>
                <th><label for="lName">Last Name:</label></th>
                <td><input type="text" name="lName" id="lName" size="20" maxLength="20"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
        </table>
    </fieldSet>  
</sf:form>
</div>

<h3><a href="/SpringWeb/login">Login</a></h3>
</body>
</html>