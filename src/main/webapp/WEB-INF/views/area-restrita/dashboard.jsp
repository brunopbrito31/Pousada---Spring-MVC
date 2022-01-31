<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - Area-Restrita </title>
        <link rel="stylesheet" href="/static/dash.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/includes/inc-top-are-res.jsp" />
        <main>
            <div class="faixa-cards">
                <c:forEach items="${cards}" var="card">
                    <div class="card-item">
                        <a href="${card.link}">
                            <h2>${card.name}</h2>
                            <i class="${card.classIcon}" ></i>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </main>
        <jsp:include page="/WEB-INF/views/includes/inc-bot-are-res.jsp" />
    </body>
</html>