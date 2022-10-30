<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<body>

<h2>All Shops</h2>
<br>

<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Products</th>
            <th>Operation</th>
        </tr>
        <c:forEach var="shop" items="${shop}">

            <c:url var="deleteButton" value="/deleteShop">
                <c:param name="shopId" value="${shop.id}"/>
            </c:url>
            <tr>
                <td>${shop.id}</td>
                <td>${shop.name}</td>
                <td>${shop.products}</td>
                <td>

                    <input type="button" value="Delete"
                           onclick="window.location.href='${deleteButton}'"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>

<input type="button" value="Add"
       onclick="window.location.href = 'addNewShop'"/>

<input type="button" value="Back"
       onclick="window.location.href = 'index'"/>
</body>

</html>
