<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sucesso no Envio!</title>
    <link rel="stylesheet" href="/static/estilo.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/includes/inc-top-home.jsp"  />
    <jsp:include page="/WEB-INF/views/includes/inc-top-desta.jsp" />
    <main id="area-inf">
        <div class="container-principal">
            Falha no Envio do Formulário, Aguarde 1 min e tente novamente.
        </div>
        <div class="area-link">
            <a href="/">Voltar Para a Página Inicial </a>
        </div>
    </main>
</body>
</html>