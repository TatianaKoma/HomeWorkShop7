<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<body>

<h2>Person Info</h2>
<br>
<br>

<form:form action="savePerson" modelAttribute="person">
    <form:hidden path="id"/>
    Name <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>
    Surname <form:input path="surname"/>
    <form:errors path="surname"/>
    <br><br>
    Email <form:input path="email"/>
    <form:errors path="email"/>
    <br><br>
    Username <form:input path="username"/>
    <form:errors path="username"/>
    <br><br>
    Password <form:input path="password"/>
    <form:errors path="password"/>
    <br><br>

    <input type="submit" value="OK">
</form:form>

</body>

</html>
