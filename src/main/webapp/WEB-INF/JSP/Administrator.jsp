<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="${language}">
<head>
    <meta charset="UTF-8">
    <title>Administrator Page</title>
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="language.text.english" /></option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="language.text.russian" /></option>
    </select>
</form>
<h1><fmt:message key="admin.prodadd.header" /></h1>
<% Boolean addFailed = (Boolean) request.getAttribute("addFailed"); %>
<% if (addFailed != null && addFailed) { %>
<p style="color: red;">Ошибка добавления. Повторите попытку.</p>
<% } %>
<form action="Administrator" method="post" enctype="multipart/form-data">
    <label for="productName"><fmt:message key="admin.prodadd.label.name" />:</label>
    <input type="text" name="productName" id="productName" required><br>

    <label for="productPrice"><fmt:message key="admin.prodadd.label.price" />:</label>
    <input type="number" name="productPrice" id="productPrice" required><br>

    <label for="productCategory"><fmt:message key="admin.prodadd.label.category" />:</label>
    <input type="text" name="productCategory" id="productCategory" required><br>

    <label for="productImage"><fmt:message key="admin.prodadd.label.image" />:</label>
    <input type="file" name="productImage" id ="productImage" accept="image/*" required>

    <input type="submit" value="<fmt:message key="admin.prodadd.button" />">
</form>

<form action="Restik" method="get">
    <input type="hidden" name="command" value="TO_MAIN">
    <input type="submit" value="На главную">
</form>

</body>
</html>
