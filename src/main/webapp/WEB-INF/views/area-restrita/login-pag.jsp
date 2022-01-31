<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<% String error = request.getAttribute("error").toString(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Area-Restrita Login</title>
        <link rel="stylesheet" href="/static/css/login.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
    </head>
    <body>
        <main>
            <form:form method="post" class="form-contato" modelAttribute="formUser">
                <%
                    if(error == "true"){
                        out.print("<p style='background-color:red'>Usuário ou Senha Incorreto.</p>");
                    }
                %>
                <div class="campo-form">
                    <form:input path="user" placeholder="Usuário"/>
                    <i class="far fa-user" id="user-icon"></i>
                </div>

                <div class="campo-form">
                    <form:input type="password" path ="password" placeholder="Senha" />
                    <i class="fas fa-key" id="key-icon"></i>    
                </div>

                <div class="campo-form">
                    <form:button>
                        Entrar
                        <i class="fas fa-angle-double-right"></i>
                    </form:button>
                </div>

                <div class="links-comple">
                    <a href="http://">Esqueci a minha senha<i class="fas fa-question"></i></a>
                    <a href="/">Voltar a Pagina Inicial<i class="fas fa-home"></i></a>
                </div>
            </form:form>
        </main>
    </body>
</html>