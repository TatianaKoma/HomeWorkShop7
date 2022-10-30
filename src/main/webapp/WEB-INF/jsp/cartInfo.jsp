<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<body>

<h2>Cart Info</h2>
<br>
<br>

<form:form action="saveCart" modelAttribute="cart">
    <form:hidden path="id"/>
    PersonId <form:input path="personId"/>
    <form:errors path="personId"/>
    <br><br>

    <input type="submit" value="OK">
</form:form>

</body>

</html>
