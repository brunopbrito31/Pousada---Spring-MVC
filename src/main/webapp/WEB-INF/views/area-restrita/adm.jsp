<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ADM - Configurações </title>
        <link rel="stylesheet" href="/static/css/adm.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/includes/inc-top-are-res.jsp" />
        <main>
            <form class="form-alt-config">

                <fieldset class="multiple-field">
                    <legend>Topo</legend>
                    <div class="campo-form">
                        <label for="titleTop">Título:</label>
                        <input type="text" id="titleTop" value="${config.titleTop}">
                    </div>
                    <div class="campo-form">
                        <label for="imgTopUrlName">Url da Imagem:</label>
                        <input type="text" id="imgTopUrlName" value="${config.imgTopUrlName}">
                    </div>
                    <div class="campo-form">
                        <label for="msgTop">Mensagem:</label>
                        <input type="text" id="msgTop" value="${config.msgTop}">
                    </div>
                </fieldset>

                <fieldset class="multiple-field">
                    <legend>Quartos</legend>
                    <div class="campo-form">
                        <label for="titleProducts">Titulo:</label>
                        <input type="text" id="titleProducts" value="${config.titleProducts}">
                    </div>
                    <div class="campo-form">
                        <label for="descriptionProducts">Descrição:</label>
                        <input type="text" id="descriptionProducts" value="${config.descriptionProducts}">
                    </div>
                    <div class="campo-form">
                        <label for="subdescriptionProducts">Subdescrição:</label>
                        <input type="text" id="subdescriptionProducts" value="${config.subdescriptionProducts}">
                    </div>
                </fieldset>

                <fieldset class="multiple-field">
                    <legend>Mapa</legend>
                    <div class="campo-form">
                        <label for="localizationMap">Localização/Chave:</label>
                        <input type="text" id="localizationMap" value="${config.localizationMap}">
                    </div>
                    <div class="campo-form">
                        <label for="mapDescription">Descrição:</label>
                        <input type="text" id="mapDescription" value="${config.mapDescription}">
                    </div>
                </fieldset>

                <fieldset class="multiple-field" id="ult">
                    <legend>Sobre</legend>
                    <div class="campo-form-top">
                        <div class="campo-form">
                            <label for="slogan">Slogan:</label>
                            <input type="text" id="slogan" value="${config.slogan}">
                        </div>
                        <div class="campo-form">
                            <label for="aboutImgUrlName">Url da Imagem:</label>
                            <input type="text" id="aboutImgUrlName" value="${config.aboutImgUrlName}">
                        </div>
                    </div>
                    <div class="campo-form">
                        <label for="about">Texto</label>
                        <textarea id="about">${config.about}</textarea>
                    </div>
                </fieldset>

                <div class="botao-enviar">
                    <button type="button" onclick="atualizarConfigs()">Alterar</button>
                </div>
            </form>
        </main>
        <jsp:include page="/WEB-INF/views/includes/inc-bot-are-res.jsp" />
        <script src="/static/js/adm.js" defer></script>
    </body>
</html>