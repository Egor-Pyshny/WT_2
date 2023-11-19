<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
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
    <title>Your basket</title>
</head>

<body>
<form action="Restik" method="get">
    <input type="hidden" name="command" value="TO_MAIN">
    <input type="submit" value="На главную">
</form>
<h1><fmt:message key="shoppingcart.header" /></h1>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="language.text.english" /></option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="language.text.russian" /></option>
    </select>
</form>
<c:if test="${empty sessionScope.basket}">
    <p><fmt:message key="shoppingcart.empty" />.</p>
</c:if>

<div>
<c:forEach var="products" items="${sessionScope.basket}">
    <div>
        <h2>${products.product.productName}</h2>
        <img src="data:image/jpg;base64,${products.product.image}" alt="" width="240" height="300"/>
        <p><fmt:message key="shoppingcart.label.category" />: ${products.product.category}</p>
        <p><fmt:message key="shoppingcart.label.amount" />: ${products.amount}</p>
            <form action="Basket" method="post">
            <input type="hidden" name="command" value="UPDATE_BASKET">
            <input type="hidden" name="productId" value="${products.product.id}">
                <label for="quantity"><fmt:message key="shoppingcart.label.amount" />:</label>
                <input type="number" id="quantity" name="quantity" value="${products.amount}" required>
            <button type="submit">Increase or decrease quantity</button>
        </form>

        <form action="Basket" method="post">
            <input type="hidden" name="command" value="REMOVE_FROM_BASKET">
            <input type="hidden" name="productId" value="${products.product.id}">
            <button type="submit"><fmt:message key="shoppingcart.button.remove" /></button>
        </form>
    </div>
</c:forEach>
</div>
<c:if test="${not empty sessionScope.basket}">
<form action="Order" method="post">
    <button type="submit"><fmt:message key="shoppingcart.button.order" /></button>
</form>
</c:if>
</body>

</html>