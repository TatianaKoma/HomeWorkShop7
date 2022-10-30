<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<body>

<h2>All Products</h2>
<br>

<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>ShopId</th>
            <th>Operations</th>
        </tr>
        <c:forEach var="product" items="${product}">

            <c:url var="updateButton" value="/updateProduct">
                <c:param name="productId" value="${product.productId}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteProduct">
                <c:param name="productId" value="${product.productId}"/>
            </c:url>
            <tr>
                <td>${product.productId}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.shopId}</td>
                <td>
                    <input type="button" value="Update"
                           onclick="window.location.href='${updateButton}'"/>
                    <input type="button" value="Delete"
                           onclick="window.location.href='${deleteButton}'"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>

<input type="button" value="Add"
       onclick="window.location.href = 'addNewProduct'"/>

<input type="button" value="Back"
       onclick="window.location.href = 'index'"/>
</body>

</html>
