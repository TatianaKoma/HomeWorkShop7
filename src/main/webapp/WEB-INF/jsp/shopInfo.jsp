<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<body>

<h2>Shop Info</h2>
<br>
<br>

<form:form action="saveShop" modelAttribute="shop">
    <form:hidden path="id"/>
    Name <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>

    <input type="submit" value="OK">
</form:form>

</body>

</html>
