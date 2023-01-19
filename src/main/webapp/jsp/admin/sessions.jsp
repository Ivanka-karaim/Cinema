<%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 14.01.2023
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

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
<tags:head admin="true"/>
<main>
    <div class="container">
    <table class="table table-hover mt-2">
        <thead>
        <tr><th class="text_film" style="font-size: 20px"><fmt:message key="film.name"/></th><th class="text_film" style="font-size: 20px"><fmt:message key="session.price"/></th><th class="text_film" style="font-size: 20px"><fmt:message key="session.date"/></th><th></th><th></th></tr></thead>


        <c:forEach var="session" items="${session}">
            <%-- Share name of publication to the command--%>
            <tr><td><a  style="text-decoration-color: white" href="session?command=session&id=${session.id}"><div class="text_login">${session.name}</div></a></td>

                <td class="text_film" style="font-size: 20px"> ${session.price}</td>
                <td class="text_film" style="font-size: 20px"> ${session.timestamp}</td>

                <td> <button class="button_enter" style="font-size: 25px; width:100%" onclick="location.href='sessions?command=delete_session&id=${session.id}'">
                    <fmt:message key="delete"/>
                </button></td>
                <td> <button class="button_enter" style="font-size: 20px; line-height: 30px!important;  width:100%" onclick="location.href='sessions?command=session&id=${session.id}'">
                    <fmt:message key="check_count_free_place"/>
                </button></td>

            </tr>
        </c:forEach>

    </table>
    <form action="edit_sessions" method="get">
        <input type="hidden" name="command" value="add_session_form"/>
        <button class="button_enter" style="margin-top: 30px"><fmt:message key="admin.add_new_session"/></button>
    </form>
    </div>
</main>
<footer>
    <ul style="display: flex; justify-content: center" class="header">
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=en"><fmt:message key="english" /></a></li>
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>
    </ul>
</footer>
<script src="https://kit.fontawesome.com/1467b92032.js" crossorigin="anonymous"></script>
</body>
</html>

