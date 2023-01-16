<%@ page import="org.project.db.entity.Session" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 12.01.2023
  Time: 16:50
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
        <div class="header">

            <a href="sessions?command=sessions&sort=name" style="text-decoration-line: none; ">
                <div class="button_enter flex" style=" width:100%; padding: 0 60px; font-size: 20px">
                Сортувати за ім'ям
                </div>
            </a>

            <a href="sessions?command=sessions" style="text-decoration-line: none; ">
                <div class="button_enter flex" style=" width:100%; padding: 0 60px; font-size: 20px">
                Сортувати за датою
                </div>
            </a>
            <a href="sessions?command=sessions&sort=countPlace" style="text-decoration-line: none; ">
                <div class="button_enter flex" style=" width:100%; padding: 0 60px; font-size: 20px">
                Сортувати за кількістю доступних місць
                </div>
            </a>
        </div>

        <div>
            <form method="get">
                <div class="header justify-content-center" style="margin-top: 30px">
                <input type="hidden" name="command" value="filter"/>
                    <select name="film" class="input" style="width: 40%; margin-right: 50px">
                        <c:forEach  items="${films}" var="film">
                            <option  value="${film.id}">${film.name}</option>
                        </c:forEach>
                    </select>

                <button class="button_enter" style="width: 30%">Фільтрувати</button>
                </div>
            </form>
        </div>
        <div class="row">
            <c:forEach  items="${session}" var="session1">
            <div class="col-md-4 ">
                <div class="session">
<%--                    <jsp:useBean id="session" scope="request" type="java.util.List"/>--%>


                    <a href="sessions?command=session&id=${session1.id}" class="a_session">
                        <img class="photo" src="">
                        <div class="text_session_block">
                            <div class="text_session">
                                ${session1.name}

                            </div>
                            <div class="text_session">
                                    ${session1.price} грн

                            </div>
                        </div>
                        <div class="text_session_block">
                            <div class="text_session">
                                ${session1.timestamp}

                            </div>
<%--                            <div class="text_session underline">--%>
<%--                                    ${session1.timestamp}--%>
<%--                            </div>--%>
                        </div>
                    </a>

                </div>
            </div>
            </c:forEach>
<%--            <div class="col-md-4">--%>

<%--            </div>--%>
<%--            <div class="col-md-4">--%>

<%--            </div>--%>
        </div>

    </div>
</main>
<script src="https://kit.fontawesome.com/1467b92032.js" crossorigin="anonymous"></script>
</body>
</html>
