<%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 08.01.2023
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "custom" uri = "/WEB-INF/customTag.tld"%>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cinema</title>
    <link rel="stylesheet"  href="./style/main.css">
    <link rel="stylesheet" href="./bootstrap/bootstrap-grid.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rubik+Wet+Paint&display=swap" rel="stylesheet"></head>

<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&family=Mulish:ital,wght@0,400;0,500;0,600;0,700;0,800;1,400;1,500&family=Oswald:wght@300;400;500&family=Rubik&display=swap" rel="stylesheet">

<body>
<header>
    <div class="container">
        <div class="header">

            <div class="logo">CiNeMa</div>
            <a href="sessions" class="login">
                <div class="text_login"><fmt:message key="header.sessions"/></div>
            </a>
            <a href="account" class="login">
                <div class="text_login"><fmt:message key="header.account"/></div>
                <div class="icon"><i class="fa-solid fa-user"></i></div>
            </a>

        </div>
    </div>
</header>
<main>
    <div class="container">
        <div class="text_login">
<%--            <custom:Hello message="Hello, ${user.name}" />--%>
        </div>
        <table class="table">
            <thead>
            <tr><th class="text_film" style="font-size: 20px"><fmt:message key="film.name"/></th><th class="text_film" style="font-size: 20px"><fmt:message key="session.price"/></th><th class="text_film" style="font-size: 20px"><fmt:message key="session.date"/></th><th class="text_film" style="font-size: 20px"><fmt:message key="session.place"/></th><th></th></tr></thead>
            <c:forEach items="${tickets}" var="ticket">
             <tr>
                 <td class="text_film" style="font-size: 20px">${ticket.film_name}</td>
                 <td class="text_film" style="font-size: 20px">${ticket.price}</td>
                 <td class="text_film" style="font-size: 20px">${ticket.timestamp}</td>
                 <td class="text_film" style="font-size: 20px">${ticket.place}</td>
             </tr>
            </c:forEach>
        </table>

    </div>
    <form action="" method="get" >
        <input type="hidden" name="command" value="logout"/>
        <div class="flex align-items-center " style="margin-left: 25%;">
            <button class="button_enter "><fmt:message key="account.logout"/></button>
        </div>
    </form>
</main>
<footer>
    <ul style="display: flex; justify-content: center" class="header">
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=en"><fmt:message key="english" /></a></li>
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>
    </ul>
</footer>

</body>
</html>
