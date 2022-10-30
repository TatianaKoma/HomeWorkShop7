<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<body>

<h2>Product Update</h2>
<br>
<br>

<form:form action="saveUpdatedProduct" modelAttribute="updatedProduct">
    <form:hidden path="productId"/>
    Name <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>
    Price <form:input path="price"/>
    <form:errors path="price"/>
    <br><br>
    ShopId <form:input path="shopId"/>
    <form:errors path="shopId"/>
    <br><br>

    <input type="submit" value="OK">
</form:form>

</body>

</html>
