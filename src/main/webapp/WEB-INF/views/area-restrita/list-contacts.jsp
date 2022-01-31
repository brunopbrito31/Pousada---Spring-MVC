<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<% 
    Integer qtPages =  Integer.parseInt( request.getAttribute("qtPages").toString() );
    Integer actualPage = Integer.parseInt( request.getAttribute("pageNo").toString() );
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contatos - Lista de Contatos </title>
        <link rel="stylesheet" href="/static/css/contacts-list.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" 
            integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" 
            crossorigin="anonymous"
        >
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/includes/inc-top-are-res.jsp" />
        <main>
            <div class="area-filtro">
                <input type="text" placeholder="Buscar pelo Email">
                <button type="button"><i class="fas fa-search"></i></button>
            </div>
            <div class="area-lista-contatos" id="div-pai-tabela">
                <table id="tab-cont">
                    <caption id="tit-tabela">Contatos</caption>
                    <thead>
                            <th>Nome do Cliente</th>
                            <th>Email</th>
                            <th>Telefone</th>
                            <th>Mensagem</th>
                            <th>Data</th>
                            <th>Status</th>
                            <th>Ação</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${contacts}" var="contact" >
                            <tr id="contact-${contact.id}">
                                <td>${contact.user.name}</td>
                                <td>${contact.user.mail}</td>
                                <td>(${contact.user.phones[0].areaCode}) ${contact.user.phones[0].number}</td>
                                <td>${contact.msg}</td>
                                <td>${dateFormater.format(contact.sendDate)}</td>
                                <td>${contact.status.ordinal() == 0 ? "Aberto": "Fechado"}</td>
                                <td>
                                    ${contact.status.ordinal() == 0 ?
                                        "<button onclick='responder(".concat(contact.id).concat(")' class='btn-cont-ativ'>Responder</button>")
                                        :"<button onclick='apagar(".concat(contact.id).concat(")' class='btn-cont'>Apagar</button>")
                                    }
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="container-paginacao">
                <ul class="botoes-paginacao">
                    <%
                        for(int i = 1; i <= qtPages; i++){
                            if((i-1) == actualPage){
                                out.print("<li id='actual'><a href='/restrict-area/contacts?pageNo="+(i-1)+"'>"+i+"</a></li>");
                            }else{
                                out.print("<li><a href='/restrict-area/contacts?pageNo="+(i-1)+"'>"+i+"</a></li>");
                            }
                        }
                    %>
                </ul>
            </div>
        </main>
        <input type="hidden" id="aut" value="${aut}">

        <jsp:include page="/WEB-INF/views/includes/inc-bot-are-res.jsp" />
        <script defer src="/static/js/contact.js"></script>
    </body>
</html>