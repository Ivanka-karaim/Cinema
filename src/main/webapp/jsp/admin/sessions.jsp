<%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 14.01.2023
  Time: 12:56
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
    <table class="table table-hover mt-2">
        <thead>
        <tr><th class="text_film" style="font-size: 20px">Назва Фільму</th><th class="text_film" style="font-size: 20px">Ціна</th><th class="text_film" style="font-size: 20px">Дата</th><th></th><th></th></tr></thead>
        <input type="hidden" name="command" value="publicationView"/>

        <c:forEach var="session" items="${session}">
            <%-- Share name of publication to the command--%>
            <tr><td><a  style="text-decoration-color: white" href="session?command=session&id=${session.id}"><div class="text_login">${session.film.name}</div></a></td>

                <td class="text_film" style="font-size: 20px"> ${session.price}</td>
                <td class="text_film" style="font-size: 20px"> ${session.timestamp}</td>

                <td> <button class="button_enter" style="font-size: 25px; width:100%" onclick="location.href='sessions?command=delete_session&id=${session.id}'">
                    Видалити
                </button></td>
                <td> <button class="button_enter" style="font-size: 20px; line-height: 30px!important;  width:100%" onclick="location.href='sessions?command=session&id=${session.id}'">
                    Перевірити кількість місць
                </button></td>

            </tr>
        </c:forEach>

    </table>
    <form action="edit_sessions" method="get">
        <input type="hidden" name="command" value="add_session_form"/>
        <button class="button_enter" style="margin-top: 30px">Додати сеанс</button>
    </form>
<%--    <div class="container">--%>
<%--        <div class="row">--%>

<%--                <div class="session">--%>
<%--                    <c:forEach  items="${session}" var="session1">--%>
<%--                        <a href="" class="a_session">--%>
<%--                            <img class="photo" src="">--%>
<%--                            <div class="text_session_block">--%>
<%--                                <div class="text_session">--%>
<%--                                        ${session1.film.name}--%>
<%--                                    Незвичайний світ--%>
<%--                                </div>--%>
<%--                                <div class="text_session">--%>
<%--                                        ${session1.price} грн--%>
<%--                                    200 грн--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="text_session_block">--%>
<%--                                <div class="text_session">--%>
<%--                                        ${session1.timestamp}--%>
<%--                                    30 листопада--%>
<%--                                </div>--%>
<%--                                <div class="text_session underline">--%>
<%--                                    19:40--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </a>--%>
<%--                    </c:forEach>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="col-md-4">--%>

<%--            </div>--%>
<%--            <div class="col-md-4">--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
    </div>
</main>
<script src="https://kit.fontawesome.com/1467b92032.js" crossorigin="anonymous"></script>
</body>
</html>

