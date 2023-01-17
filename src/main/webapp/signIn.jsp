<%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 08.01.2023
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cinema</title>
    <link rel="stylesheet" href="style/main.css">
    <link rel="stylesheet" href="bootstrap/bootstrap-grid.min.css">
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
        <div class="flex align-items-center">
            <form action="signIn" class="login_store"  method="post">
                <input type="hidden" name="command" value="signIn"/>
                <div>
                    ${error}
                </div>
                <div class="enter">
                    <fmt:message key="registr"/>
                </div>
                <div class="input_block">
                    <input title=<fmt:message key="enter_name"/> placeholder="введіть ваше ім'я" class="input" name="name" required>
                </div>
                <div class="input_block">
                    <input title="<fmt:message key="enter_surname"/>" placeholder="введіть ваше прізвище" class="input" name="surname" required>
                </div>

                <div class="input_block">
                    <input title="<fmt:message key="enter_email"/>" placeholder="введіть пошту" class="input" name="email" required>
                </div>
                <div class="input_block">
                    <input title="<fmt:message key="enter_phone"/>" placeholder="введіть номер телефону" class="input" name="phone_number" required>
                </div>
                <div class="input_block">
                    <input title="<fmt:message key="enter_password"/>" placeholder="введіть пароль" class="input" name="password" required>
                </div>
                <button class="button_enter">
                    <fmt:message key="sign_up"/>

                </button>
            </form>
        </div>
    </div>
</main>
<script src="https://kit.fontawesome.com/1467b92032.js" crossorigin="anonymous" ></script>

</body>
</html>
