<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<body>

<h2>All Products in Cart</h2>
<br>

<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>ShopId</th>
        </tr>
        <c:forEach var="product" items="${listProducts}">
        <tr>
            <td>${product.productId}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.shopId}</td>

        </tr>
        </c:forEach>
    </table>
</div>
<br>

<input type="button" value="Back"
       onclick="window.location.href = 'getCarts'"/>
</body>

</html>
