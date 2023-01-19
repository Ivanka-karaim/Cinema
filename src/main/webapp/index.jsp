
<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 27.09.2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "custom" uri = "/WEB-INF/customTag.tld" %>


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
            <form action="account" class="login_store"  method="post">
                <input type="hidden" name="command" value="login"/>
                <c:if test="${error!=null}">
                <div>
                    <fmt:message key="${error}"/>

                </div>
                </c:if>


                <div class="enter">
                    <fmt:message key="login.enter"/>
                </div>

                <div class="input_block">
                    <input title=<fmt:message key="enter_email"/> class="input" name="email" required>
                </div>


                <div class="input_block">
                    <input title=<fmt:message key="enter_password"/> class="input" name="password" type="password" id="password" required>

                </div>
                <label class="text_film" style="font-size: 20px"><input type="checkbox" title=<fmt:message key="see_password"/> id="see" onclick="testSee()">      <fmt:message key="see_password"/></label>
                <button class="button_enter">
                    <fmt:message key="sign_in"/>
                </button>
                <a href='signIn.jsp' class="registr">
                    <fmt:message key="sign_up"/>
                </a>
                <a href="sessions" class="registr">
                    <fmt:message key="guest"/>
                </a>
            </form>

        </div>
    </div>
    <div style="height: 80px; "></div>
</main>

<footer>
    <ul style="display: flex; justify-content: center" class="header">
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=en"><fmt:message key="english" /></a></li>
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>
    </ul>
</footer>
<script>
    function testSee() {
        var chbox = document.getElementById('see');
        const input = document.getElementById('password')
        if (chbox.checked) {
            input.setAttribute('type', 'text');

        } else {
            input.setAttribute('type', 'password');
        }
    }
</script>
<script src="https://kit.fontawesome.com/1467b92032.js" crossorigin="anonymous" ></script>

</body>
</html>