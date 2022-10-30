<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<body>

<h2>Add Product to Cart</h2>
<br>
<br>

<form:form action="saveProductToCart" modelAttribute="updatedCart">
<%--    <form:hidden path="id"/>--%>
    CartId <form:input path="id"/>
    <form:errors path="id"/>
    <br><br>
    ProductId <form:input path="productsId"/>
    <form:errors path="productsId"/>
    <br><br>

    <input type="submit" value="OK">
</form:form>

</body>

</html>
