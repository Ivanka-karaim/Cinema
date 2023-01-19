<%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 14.01.2023
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
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
    <link href="https://fonts.googleapis.com/css2?family=Rubik+Wet+Paint&display=swap" rel="stylesheet"></head>

<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&family=Mulish:ital,wght@0,400;0,500;0,600;0,700;0,800;1,400;1,500&family=Oswald:wght@300;400;500&family=Rubik&display=swap" rel="stylesheet">

<body>
<tags:head admin="true"/>
<main>
    <div class="container">
        <form action="sessions" method="get">
            <input type="hidden" name="command" value="edit_sessions"/>
            <div class="flex align-items-center " style="margin-left: 25%;margin-bottom: 50px">
            <button class="button_enter"><fmt:message key="admin.edit_sessions"/></button>
            </div>
        </form>
    <form action="" method="get" >
        <input type="hidden" name="command" value="logout"/>
        <div class="flex align-items-center " style="margin-left: 25%;">
        <button class="button_enter "><fmt:message key="account.logout"/></button>
        </div>
    </form>

    </div>
</main>
<footer>
    <ul style="display: flex; justify-content: center" class="header">
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=en"><fmt:message key="english" /></a></li>
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>
    </ul>
</footer>

</body>
</html>

