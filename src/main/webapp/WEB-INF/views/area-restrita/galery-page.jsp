<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Galeria de Imagem - Nova </title>
        <link rel="stylesheet" href="/static/css/area-res-ger.css">
        <link rel="stylesheet" href="/static/css/galery.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" 
            integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" 
            crossorigin="anonymous"
        >
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/includes/inc-top-are-res.jsp" />
        <main>
            <div class="container-form">
                <form id="form-uplo">
                    <fieldset class="card-imagem" id="img-1">
                        <div class="container-imagem" onclick="containerClick(1)">
                            <img src="https://www.sindssedf.org.br/wp-content/uploads/2015/04/sem-imagem-800.gif" id="img-to-up-1">
                        </div>
                        <input type="file" name="file" id="file-input-1" onchange="carregarVisu(1)">
                        <div class="campo-form">
                            <input type="text" id="nameArq-1" placeholder="Nome da Imagem">
                        </div>
                        <div class="campo-form">
                            <input type="text" id="title-1" placeholder="Titulo da Imagem">
                        </div>
                        <div class="area-botoes" id="area-botoes-1"> 
                            <button class="bt-up" type="button" onclick="salvarImagem(1)">Upload</button>
                            <button class="bt-del" type="button" onclick="deletarImg(1)">Excluir</button>
                        </div>
                    </fieldset>
                </form>
            </div>

            <button type="button" id="botao-adicionar">Adicionar Imagem</button>
        </main>
        <jsp:include page="/WEB-INF/views/includes/inc-bot-are-res.jsp" />
        <script defer src="/static/js/galery.js"></script>
    </body>
</html>