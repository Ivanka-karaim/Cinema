
<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 27.09.2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>


<%--<html lang="${sessionScope.lang}">--%>

<%--<head>--%>
<%--  <title>Login</title>--%>
<%--  <meta charset="UTF-8">--%>
<%--  <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--    <link rel="stylesheet" type="text/css" href="style/util.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="style/main.css">--%>
<%--</head>--%>
<%--<body>--%>

<%--<div class="limiter">--%>
<%--<div class="container-login100">--%>
<%--<div class="wrap-login100">--%>
<%--  <form class="login100-form validate-form" action="login" method="post" >--%>

<%--    <input type="hidden" name="command" value="login"/>--%>
<%--    <span class="login100-form-title p-b-26">--%>
<%--        <fmt:message key="welcome"/>--%>
<%--        </span>--%>
<%--<span class="login100-form-title p-b-48">--%>
<%--            <i class="zmdi zmdi-font"></i>--%>
<%--        </span>--%>
<%--      <br><br>--%>
<%--      <div class="wrap-input100 validate-input" >--%>
<%--<input class="input100" type="text" name="username" placeholder="<fmt:message key="enter_login" />" title=<fmt:message key="enter_login" />>--%>

<%--</div>--%>

<%--<div class="wrap-input100 validate-input" data-validate="Enter password">--%>
<%--            <span class="btn-show-pass">--%>
<%--                <i class="zmdi zmdi-eye"></i>--%>
<%--            </span>--%>
<%--<input class="input100" type="password" name="userpass" placeholder="<fmt:message key="enter_password" />" title=<fmt:message key="enter_password" />>--%>
<%--</div>--%>
<%--      <br>--%>
<%--<div class="container-login100-form-btn">--%>
<%--  <div class="wrap-login100-form-btn">--%>
<%--      <div class="login100-form-bgbtn"></div>--%>
<%--      <button class="login100-form-btn">--%>
<%--          <fmt:message key="account.login"/>--%>
<%--      </button>--%>
<%--  </div>--%>
<%--</div>--%>
<%--      <br>--%>
<%--<div class="text-center p-t-115">--%>
<%--            <span class="txt1">--%>
<%--               <fmt:message key="not_registered_yet"/>--%>
<%--            </span>--%>
<%--<a class="txt2" href='signIn.jsp'>--%>
<%--  <fmt:message key="sign_up" />--%>
<%--</a>--%>
<%--</div>--%>

<%--</form>--%>
<%--    <br>--%>
<%--<form  action="publicaton" method="post" >--%>
<%--<input type="hidden" name="command" value="publication"/>--%>
<%--<input type="hidden" name="currentPage" value="1"/>--%>
<%--<button>--%>
<%--    <fmt:message key="guest" />--%>
<%--</button>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<ul>--%>
<%--<li><a href="index.jsp?sessionLocale=en"><fmt:message key="english" /></a></li>--%>
<%--<li><a href="index.jsp?sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>--%>
<%--</ul>--%>
<%--</body>--%>
<%--</html>--%>

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
                <div class="text_login">Сеанси</div>
            </a>
            <a href="account" class="login">
                <div class="text_login">Профіль</div>
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
                <% if (request.getAttribute("errorMessageLogin") != null ) {%>
                <div>
                    <%=request.getAttribute("errorMessageLogin") %>
                </div>
                <% } %>
                <div class="enter">
                    Вхід
                </div>

                <div class="input_block">
                    <input title="введіть пошту" class="input" name="email">
                </div>


                <div class="input_block">
                    <input title="введіть пароль" class="input" name="password" type="password" id="password">

                </div>
                <label class="text_film" style="font-size: 20px"><input type="checkbox" title="показати пароль" id="see" onclick="testSee()">      показати пароль</label>
                <button class="button_enter">
                    Увійти
                </button>
                <a href='signIn.jsp' class="registr">
                    Зареєструватись
                </a>
                <a href="sessions" class="registr">
                    Увійти як гість
                </a>
            </form>

        </div>
    </div>
</main>
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