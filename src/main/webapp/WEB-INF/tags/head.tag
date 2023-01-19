<%@ attribute name="admin" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <div class="container">
        <div class="header">
            <div class="logo">CiNeMa</div>
            <c:if test="${!admin}">
            <a href="sessions" class="login">
                <div class="text_login"><fmt:message key="header.sessions"/></div>
            </a>
            </c:if>
            <a href="account" class="login">
                <div class="text_login"><fmt:message key="header.account"/></div>
                <div class="icon"><i class="fa-solid fa-user"></i></div>
            </a>
        </div>
    </div>
</header>