<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pousada BrunoPBrito31</title>
    <link rel="stylesheet" href="/static/estilo.css">
</head>
<body>
    <header>
        <div>
            <b>Pousada</b> Secreta
        </div>
        <nav>
            <ul>
                <li class="item-menu"><a href="#quartos" id="link-quartos">Quartos</a></li>
                <li class="item-menu"><a href="#mapa" id="link-mapa">Rota</a></li>
                <li class="item-menu"><a href="#sobre" id="link-sobre">Sobre</a></li>
            </ul>
        </nav>
    </header>
    <section class="area-apres">
        <section class="top-desc">
            <h1>Pousada Secreta</h1>
            <p>Paz e muita diversão no mesmo lugar</p>
        </section>
        <img src="/static/assets/img/top.jpg">
    </section>
    <main>
        <section class="area-apres-desc">
            <h2>SEJA BEM-VINDO(A)!</h2>
            <p>Os melhores quartos você encontra aqui</p>
            <p>Confira alguns.</p>
        </section>

        

        <section class="area-apres-quar" id="quartos">
            <c:forEach items="${quartos}" var="quarto">
                <div class="item-quarto">
                    <img src="${quarto.urlImg}">
                    <h3>${quarto.description}</h3>
                </div>
            </c:forEach>
        </section>
        
    </main>

    <div class="area-mapa" id="mapa">
        <p>Como Chegar?</p>
        <iframe  id="gmap_canvas" src="https://maps.google.com/maps?q=2880%20Broadway,%20New%20York&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
    </div>

    <div class="container-quem-somos">
        <div id="filtro-imagem">
            <h1>Uma Proposta Diferente...</h1>
            <p>
                Um grupo formado com o intuito de proporcionar lazer e descanso as pessoas que ao longo dos dias possuem uma rotina desgastante, cheia de desafios, está querendo descansar com a melhor vista? Fale com a gente!
            </p>


            <h2>Fale Conosco!</h2>
            <form:form id="frmForm" class="form-contato" method="post" modelAttribute="form">
                <div class="campo-form">
                    <form:input path="name" placeholder="Nome" />
                </div>
                <div class="campo-form" id="div-mai">
                    <form:input path="mail" placeholder="Seu Email" id="inp-mai"/>
                </div>
                <div class="campo-form">
                    <form:select path="gender" placeholder="Sexo" >
                        <form:option value="0">Masculino</form:option>
                        <form:option value="1">Feminino</form:option>
                    </form:select>
                </div>
                <p id="msg-err-mai" style="background-color:red" class="msg-err-inactive">Atenção: Este Email já está cadastrado no sistema!</p>
                <div class="campo-form">
                    <form:label path="birthDate">Data de Nascimento</form:label>
                    <form:input path="birthDate" type="date" placeholder="Data de Nascimento" />
                </div>
                <div class="campo-form">
                    <form:input path="phone" id="pho-inp" placeholder="Telefone" maxlength="13"/>
                </div>
                <div class="campo-form">
                    <form:input path="zipPostal" placeholder="Cep" />
                </div>
                <form:button>Cadastrar</form:button>
            </form:form>
        </div>
        <div class="quem-somos" id="sobre">
            <img src="/static/assets/img/quemsomos.jpg" />
        </div>
    </div>
    <script src="/static/index.js" defer></script>
</body>
</html>