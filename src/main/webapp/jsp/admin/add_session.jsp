<%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 14.01.2023
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="https://fonts.googleapis.com/css2?family=Rubik+Wet+Paint&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&family=Mulish:ital,wght@0,400;0,500;0,600;0,700;0,800;1,400;1,500&family=Oswald:wght@300;400;500&family=Rubik&display=swap" rel="stylesheet">

</head>
<body>
<header>
    <div class="container">
        <div class="header">

            <div class="logo">CiNeMa</div>
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
    <form action="add_session" method="post" class="login_store">

        <input type="hidden" name="command" value="add_session"/>
        <div>${error}</div>
        <div class="input_block text_film" style="font-size: 15px">
            Виберіть дату
        <input title="time"  type="datetime-local" name="timestamp" class="input">
        </div>
        <div class="input_block text_film" style="font-size: 15px">
            Введіть ціну
        <input title="price" type="text" name="price" class="input">
        </div>
        <div class="input_block text_film" style="font-size: 15px">
            Виберіть фільм
        <select title="film" name="film"  class="input">
                <c:forEach var="film" items="${films}" >
                    <option value="${film.id}">${film.name}</option>
                </c:forEach>
            </select>
        </div>
        <button class="button_enter">Зберегти</button>

    </form>
        </div>
    </div>
</main>

</body>
</html>
