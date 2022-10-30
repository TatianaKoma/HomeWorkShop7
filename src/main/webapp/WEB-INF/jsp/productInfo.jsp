<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<body>

<h2>Product Info</h2>
<br>
<br>

<form:form action="saveProduct" modelAttribute="product">
    <form:hidden path="productId"/>
    Name <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>
    Price <form:input path="price"/>
    <form:errors path="price"/>
    <br><br>
    ShopID <form:input path="shopId"/>
    <form:errors path="shopId"/>
    <br><br>

    <input type="submit" value="OK">
</form:form>

</body>

</html>
