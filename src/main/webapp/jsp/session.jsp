<%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 14.01.2023
  Time: 22:11
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
        <div class="row">
            <div class="col-md-6">
                <img src="" class=" photo" style="height: 500px;">
            </div>
            <div class="col-md-6">
                <div class="flex-column justify-content-between align-content-between height100 width100">
                    <div class="name_film">${session.film.name}

                    </div>
                    <p class="text_film">
                        ${session.film.description}
                    </p>
                    <div class="text_film">
                        Автор: ${session.film.author}
                    </div>
                    <div class="text_film">
                        Рік:${session.film.year}
                    </div>
                    <div class="text_film">
                        Країна: ${session.film.country}
                    </div>
                    <div class="text_price">
                        Ціна:${session.price}грн
                    </div>
                    <div class="text_session_block">
                        <div class="text_session">
                            ${session.timestamp}
                        </div>
<%--                        <div class="text_session underline">--%>
<%--                            19:40--%>
<%--                        </div>--%>
                    </div>
                </div>

            </div>

        </div>
        <div class="places">
            <div class="row">
                <c:forEach items="${tickets}" var="ticket">
                    <c:if test="${ticket.user==null}" >
                <div class="col-md-1">
                    <div class="place_free_a">
                    <a href="sessions?command=buy&id=${ticket.id}" class="place_free_a">
                        <div class="place_free">
                        ${ticket.place}
                        </div>
                    </a>
                    </div>
                </div>
                </c:if>
                    <c:if test="${ticket.user!=null}" >
                        <div class="col-md-1">
                            <div class="place_notfree">
                                    ${ticket.place}
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
<%--                <div class="col-md-1">--%>
<%--                    <div class="place_free">--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-1">--%>
<%--                    <div class="place_free">--%>

<%--                    </div>--%>
<%--                </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_free">--%>

<%--                </div>--%>
<%--            </div><div class="col-md-1">--%>
<%--                <div class="place_notfree">--%>

<%--                </div>--%>
<%--            </div>--%>



















            </div>
        </div>
    </div>
</main>
<script src="https://kit.fontawesome.com/1467b92032.js" crossorigin="anonymous"></script>
</body>
</html>

