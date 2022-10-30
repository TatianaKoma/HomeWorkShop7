<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<body>

<h2>All Carts</h2>

<br>

<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>PersonId</th>
            <th>Sum</th>
            <th>Operations</th>
        </tr>
        <c:forEach var="cart" items="${cart}">

            <c:url var="productButton" value="/listProducts">
                <c:param name="cartId" value="${cart.id}"/>
            </c:url>

            <c:url var="addProductButton" value="/addProductToCart">
                <c:param name="cartId" value="${cart.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteCart">
                <c:param name="cartId" value="${cart.id}"/>
            </c:url>

            <tr>
                <td>${cart.id}</td>
                <td>${cart.personId}</td>
                <td>${cart.sum}</td>
                <td>
                    <input type="button" value="ListProducts"
                           onclick="window.location.href='${productButton}'"/>
                    <input type="button" value="AddProduct"
                           onclick="window.location.href='${addProductButton}'"/>
                    <input type="button" value="DeleteCart"
                           onclick="window.location.href='${deleteButton}'"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>

<input type="button" value="AddCart"
       onclick="window.location.href = 'addNewCart'"/>

<input type="button" value="Back"
       onclick="window.location.href = 'index'"/>
</body>

</html>
