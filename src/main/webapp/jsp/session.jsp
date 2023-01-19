<%--
  Created by IntelliJ IDEA.
  User: Іванка
  Date: 14.01.2023
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
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
<tags:head admin="false"/>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <img src="" class=" photo" style="height: 500px;">
            </div>
            <div class="col-md-6">
                <div class="flex-column justify-content-between align-content-between height100 width100">
                    <div class="name_film">${session.name}

                    </div>
                    <p class="text_film">
                        ${session.description}
                    </p>
                    <div class="text_film">
                        <fmt:message key="session.author"/>: ${session.author}
                    </div>
                    <div class="text_film">
                        <fmt:message key="session.year"/>:${session.year}
                    </div>
                    <div class="text_film">
                        <fmt:message key="session.country"/>: ${session.country}
                    </div>
                    <div class="text_price">
                        <fmt:message key="session.price"/>:${session.price}грн
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
                    <c:choose>
<%--                    <c:if test="${ticket.user==false}" >--%>
                        <c:when test="${ticket.user==false}">
                <div class="col-md-1">
                    <div class="place_free_a">
<%--                    <a href="sessions?command=buy&id=${ticket.id}" class="place_free_a">--%>
<%--                        <div class="place_free">--%>
<%--                        ${ticket.place}--%>
<%--                        </div>--%>
<%--                    </a>--%>
                    <div onclick="buy(${ticket.id},${ticket.place})"  class="place_free_a">
                        <div class="place_free">
                        ${ticket.place}
                        </div>
                    </div>
                    </div>
                </div>
                        </c:when>
<%--                </c:if>--%>
                        <c:otherwise>
<%--                    <c:if test="${ticket.user}" >--%>
                        <div class="col-md-1">
                            <div class="place_notfree">
                                    ${ticket.place}
                            </div>
                        </div>
<%--                    </c:if>--%>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
<div class="pop_up" id="pop_up">
    <div class="pop_up_container">
        <div class="pop_up_body" id="pop_up_body">
            <div style="display:flex; flex-direction: column; align-items:center;">
                <c:choose>
                    <c:when test="${user.id==null}">

                        <div class="text_film" style=" color: black">Ви ще не зареєстровані</div>
                        <form action="account" method="get">
                        <button class="button_enter">
                            <fmt:message key="sign_in"/>
                        </button>
                        </form>

                    </c:when>
                    <c:otherwise>
                <form action="account" method="post">
                    <input type="hidden" name="command" value="buy"/>
                    <div id="label1"> Місце:
                        <div id="label"></div>
                    </div>
                    <input type="hidden" name="place"  id="place"/>
                    <label><fmt:message key="enter_card_number"/></label>
                    <input name="number_cart" style="width:80%; text-align: center"/>
                    <div style="display: flex; justify-content: space-between; margin: 0 70px" >
                        <label><fmt:message key="enter_date"/></label>
                        <label><fmt:message key="enter_cvv"/></label>
                    </div>
                    <div style="display: flex">
                    <input name="date" style="width:30%"/>
                    <input type="password" name="cvv" style="width: 30%"/>
                    </div>
                    <button class="button_enter">
                        <fmt:message key="buy"/>
                    </button>


                </form>
                    </c:otherwise>
                </c:choose>
                <div class="pop_up_close" id="pop_up_close" onclick="closePop('pop_up')">&#10006
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
    <ul style="display: flex; justify-content: center" class="header">
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=en"><fmt:message key="english" /></a></li>
        <li><a class="text_film" style="font-size: 20px; padding:30px; margin:20px" href="account?sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>
    </ul>
</footer>
<script src="https://kit.fontawesome.com/1467b92032.js" crossorigin="anonymous"></script>
<script >
    var number_ticket = 0;

    function getTicket(){
        return number_ticket;
    }

    function buy(num, place) {
        const popUp = document.getElementById('pop_up');
        popUp.classList.add('active');
        number_ticket = num;
        document.getElementById('place').value=num;
        document.getElementById('label1').innerText+=place;

    }

    function closePop(name){
        const popUp = document.getElementById(name);
        popUp.classList.remove('active');
    }

</script>
</body>
</html>

