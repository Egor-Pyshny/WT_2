<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restik</title>
</head>
<body>

<div>
    <div>
        <c:choose>
            <c:when test="${empty sessionScope.UserId }">

                <div>
                    <form action="Authorization" method="get">
                        <input type="submit" class="signin" id="to_sing_in"
                               value="<fmt:message key="main.login.button" />">
                    </form>
                    <form action="Registration" method="get">
                        <input type="submit" class="signup" id="to_sing_up"
                               value="<fmt:message key="main.signup.button" />">
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    <form action="Restik" method="post">
                        <input type="hidden" name="command" value="LOG_OUT"/>
                        <input type="submit" class="signin" value="<fmt:message key="main.logout.button" />">
                    </form>
                    <c:if test="${sessionScope.isAdmin == 'Administrator'}">
                        <form action="Administrator" method="get">
                            <input type="submit" class="signup" value="<fmt:message key="main.admin.button" />">
                        </form>
                    </c:if>

                </div>

            </c:otherwise>
        </c:choose>


    </div>
</div>


<form action="Restik" method="get">
    <label for="category"><fmt:message key="main.showprod.label.category"/>:</label>
    <input type="text" id="category" name="category"/>
    <button type="submit"><fmt:message key="main.showprod.button"/></button>
</form>

<c:if test="${not empty sessionScope.UserId}">
    <form action="Basket" method="get">
        <button type="submit" class="view-cart-button"><fmt:message key="main.showcart.button"/></button>
    </form>
</c:if>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="language.text.english"/></option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="language.text.russian"/></option>
    </select>
</form>
<div style="display: block;>
    <h1><fmt:message key="main.product.header"/></h1>
    <c:forEach var="product" items="${products}">
        <hr/>
        <div class="product-box">
            <h2>${product.productName}</h2>
            <img src="data:image/jpg;base64,${product.image}" alt="" width="240" height="300"/>
            <p><fmt:message key="main.product.price"/>: ${product.price}</p>
            <p><fmt:message key="main.product.category"/>: ${product.category}</p>
            <c:if test="${not empty sessionScope.UserId}">
                <form action="Restik" method="post">
                    <input type="hidden" name="command" value="ADD_TO_BASKET">
                    <input type="hidden" name="productId" value="${product.id}">
                    <button type="submit"><fmt:message key="main.product.add.button"/></button>
                </form>
            </c:if>
        </div>
    </c:forEach>
</div>
</body>

</html>