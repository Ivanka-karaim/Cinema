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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
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
<tags:head admin="false"/>
<main>
    <div class="container">
        <div class="header">

            <a href="sessions?command=sessions&sort=name" style="text-decoration-line: none; ">
                <div class="button_enter flex" style=" width:100%; padding: 5px 60px; font-size: 20px; line-height: 30px">
                    <fmt:message key="sort.a_z"/>
                </div>
            </a>

            <a href="sessions?command=sessions" style="text-decoration-line: none; ">
                <div class="button_enter flex" style=" width:100%; padding: 5px 60px; font-size: 20px; line-height: 30px">
                    <fmt:message key="sort.date"/>
                </div>
            </a>
            <a href="sessions?command=sessions&sort=countPlace" style="text-decoration-line: none; ">
                <div class="button_enter flex" style=" width:100%; padding: 5px 60px; font-size: 20px; line-height: 30px">
                    <fmt:message key="sort.count"/>
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

                <button class="button_enter" style="width: 30%"> <fmt:message key="filter"/></button>
                </div>
            </form>
        </div>
        <div class="row">
            <c:forEach  items="${session}" var="session1">
            <div class="col-md-4 ">
                <div class="session">
<%--                    <jsp:useBean id="session" scope="request" type="java.util.List"/>--%>


                    <a href="sessions?command=session&id=${session1.id}" class="a_session">
                        <img class="photo" src="${session1.photo}">
                        <div class="text_session_block">
                            <div class="text_session">
                                ${session1.name}

                            </div>
                            <div class="text_session">
                                    ${session1.price}

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
            <nav aria-label="Page navigation example">
                <ul class="flex justify-content-center pagination">
                    <c:forEach items="${nOfPages}" var="number">
                        <c:choose>
                        <c:when test="${currentPage==number}">
                    <li class="page-items">
                        <button class="page-link-no text_film" style="line-height: 30px" >${number}</button>
                    </li>
                        </c:when>
                            <c:otherwise>
                        <li class="page-items">
                            <button class="page-link text_film" style="line-height: 30px" onclick="current(${number})">${number}</button>
                        </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
<%--                        <li class="page-item"><a class="page-link" href="publicationView?command=publication&recordsPerPage=5&currentPage=2">2</a></li>--%>
<%--                    <li class="page-item"><a class="page-link" href="publicationView?command=publication&recordsPerPage=5&currentPage=3">3</a></li>--%>
                </ul>
            </nav>
<%--            <div class="col-md-4">--%>

<%--            </div>--%>
<%--            <div class="col-md-4">--%>

<%--            </div>--%>
        </div>

    </div>
</main>
<footer>
    <ul style="display: flex; justify-content: center" class="header">
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=en"><fmt:message key="english" /></a></li>
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>
    </ul>
</footer>
<script src="https://kit.fontawesome.com/1467b92032.js" crossorigin="anonymous"></script>
<script>
    function current(page){
        const mass = window.location.href.split("?")
        if (mass.length===2) {
            const hr = mass[0]
            const kvp = window.location.href.split("?")[1].split("&");
            console.log(kvp);
            let i = 0;

            for (; i < kvp.length; i++) {
                console.log(kvp);
                if (kvp[i].startsWith("currentPage=")) {
                    let pair = kvp[i].split('=');
                    pair[1] = page;
                    kvp[i] = pair.join('=');
                    break;
                }
            }
            if (i >= kvp.length) {
                kvp[kvp.length] = ["currentPage", page].join('=');
            }
            // can return this or...
            console.log(kvp);
            let params = kvp.join('&');

            // reload page with new params

            window.location.href = hr+"?"+params;
        }else {
            const hr = window.location.href;
            window.location.href= hr+"?currentPage="+page;
        }





    }
</script>

</body>
</html>
