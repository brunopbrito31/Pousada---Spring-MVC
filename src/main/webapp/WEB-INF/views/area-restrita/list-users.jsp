<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Usuários - Lista de Usuários </title>
        <link rel="stylesheet" href="/static/css/user-list.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" 
            integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" 
            crossorigin="anonymous"
        >
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/includes/inc-top-are-res.jsp" />
        <main>
            
            <div class="area-cards">
                <c:forEach items="${users}" var="user">
                    <div class="card-usuario" id="card-usuario-${user.id}">

                        <div class="form-campo-VALORID" id="icone-${user.id}">
                            <c:choose>  
                                <c:when test="${user.autho.ordinal() == 0}">  
                                    <i class="fas fa-user-tie"></i>
                                </c:when>
                                <c:when test="${user.autho.ordinal() == 1}"> 
                                    <i class="fas fa-user-shield"></i> 
                                </c:when>  
                                <c:otherwise>
                                    <i class="fas fa-user"></i>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="form-campo">
                            <label for="nome-${user.id}">Nome:</label>
                            <input type="text" id="nome-${user.id}" value="${user.name}" disabled>
                        </div>

                        <div class="form-campo">
                            <label for="email-${user.id}">Email:</label>
                            <input type="text" id="email-${user.id}" value="${user.mail}" disabled>
                        </div>

                        <div class="form-campo">
                            <button type="button" class="botao-editar" onclick="editarUsuario('${user.id}')">Editar</button>
                        </div>

                        <div class="form-campo">
                            <button type="button" class="botao-desativar" onclick="desativarUsuario('${user.id}')">Desativar</button>
                        </div>
                        
                    </div>
                </c:forEach>

                <div class="container-paginacao">
                <ul class="botoes-paginacao">
                    <c:forEach var="i" begin="${1}" end="${qtPages}">
                        <c:choose>  
                            <c:when test="${i-1 == pageNo}">  
                                <li id="actual"><a href="/restrict-area/users?pageNo=${i-1}">${i}</a></li></li>
                            </c:when> 
                            <c:otherwise>
                                <li><a href="/restrict-area/users?pageNo=${i-1}">${i}</a></li></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/views/includes/inc-bot-are-res.jsp" />
        <script defer src="/static/js/users.js"></script>
    </body>
</html>