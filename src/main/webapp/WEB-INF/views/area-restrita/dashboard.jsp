<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<% 
    Integer aut;
    if(request.getSession().getAttribute("aut") == null){
        aut = 0;
    }else{
       aut = Integer.parseInt(request.getSession().getAttribute("aut").toString());
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - Area-Restrita </title>
        <link rel="stylesheet" href="/static/css/dash.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/includes/inc-top-are-res.jsp" />
        <main>
            <div class="faixa-cards">
                <c:forEach items="${cards}" var="card">
                    <c:if test= "${aut == 1}">
                        <div class="card-item">
                            <a href="${card.link}">
                                <h2>${card.name}</h2>
                                <i class="${card.classIcon}" ></i>
                            </a>
                        </div>
                    </c:if>
                    <c:if test= "${aut == 0}">
                        <c:if test="${aut == card.autorization}">
                            <div class="card-item">
                                <a href="${card.link}">
                                    <h2>${card.name}</h2>
                                    <i class="${card.classIcon}" ></i>
                                </a>
                            </div>
                        </c:if>
                    </c:if>
                </c:forEach>
            </div>
        </main>
        <jsp:include page="/WEB-INF/views/includes/inc-bot-are-res.jsp" />
        <script>
            window.addEventListener('load',()=>{
                let aut = "${aut}";
                sessionStorage.setItem('aut',aut);
            });
        </script>
    </body>
</html>