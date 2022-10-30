<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<body>

<h2>All Persons</h2>

<br>

<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Operations</th>
        </tr>
        <c:forEach var="person" items="${person}">

            <c:url var="updateButton" value="/updatePerson">
                <c:param name="personId" value="${person.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deletePerson">
                <c:param name="personId" value="${person.id}"/>
            </c:url>
            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.surname}</td>
                <td>${person.email}</td>
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
       onclick="window.location.href = 'addNewPerson'"/>

<input type="button" value="Back"
       onclick="window.location.href = 'index'"/>
</body>

</html>
