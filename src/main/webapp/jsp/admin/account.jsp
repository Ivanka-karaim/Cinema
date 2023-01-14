<%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 14.01.2023
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>
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
            <%if( session.getAttribute("userRole").equals("admin")) { %>
            <a onclick="location.href='account?command=account'"  class="login">
                <div class="text_login">Профіль</div>
                <div class="icon"><i class="fa-solid fa-user"></i></div>
            </a>
            <% } else {%>
            <a href="" class="login">
                <div class="text_login">Увійти</div>
                <div class="icon"><i class="fa-solid fa-user"></i></div>
            </a>
            <%  }%>
        </div>
    </div>
</header>
<main>
    <form action="" method="post" >
        <input type="hidden" name="command" value="logout"/>
        <button class="btn btn-dark btn-lg">Вийти</button>
    </form>
    <form action="sessions" method="get">
        <input type="hidden" name="command" value="edit_sessions"/>
        <button class="btn btn-dark btn-lg">Редагувати сеанси</button>
    </form>
</main>

</body>
</html>

