<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Main Page</title>

</head>
<body>
<h1>Online Shopping</h1>
<h2>${message}</h2>

<div>
    <h3>Welcome ${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/login">Log In</a></h4>
        <h4><a href="/registration">Registration</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">LogOut</a></h4>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <a href="${pageContext.request.contextPath}/getPersons">Person</a>
        <br>
        <a href="${pageContext.request.contextPath}/getShops">Shop</a>
        <br>
        <a href="${pageContext.request.contextPath}/getCarts">Cart</a>
        <br>
        <a href="${pageContext.request.contextPath}/getProducts">Product</a>
    </sec:authorize>
</div>
</body>
</html>
