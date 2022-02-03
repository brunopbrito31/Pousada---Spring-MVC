<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <%-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> --%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${config.titleTop}</title>
    <link rel="stylesheet" href="/static/css/estilo.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
</head>
<body>
    <div id="lgpd-container" style="display:none">
        <div class="area-termo">
            <div class="titulo">
                ${term.title}
            </div>
            <div class="conteudo">
                ${term.content}
            </div>
            <button onclick="aceitarTermos()" id="botao-aceite">Li e Estou de acordo</button>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/includes/inc-top-home.jsp" />
    <jsp:include page="/WEB-INF/views/includes/inc-top-desta.jsp" />
    <main>
        <div class="check-in-pes">
            <h2>Pesquise suas Férias</h2>
            <div class="container-campo-pes">
                <input type="text" id="check-in" placeholder="CHECK IN" onfocus="(this.type='date')">
                <i class="fas fa-calendar" id="cal-icon"></i>
            </div>
            <div class="container-campo-pes">
                <input type="text" id="check-out" placeholder="CHECK OUT" onfocus="(this.type='date')">
                <i class="fas fa-calendar" id="cal-icon-2"></i>
            </div>
            <div class="container-campo-pes">
                <select id="hospedes"  placeholder="HÓSPEDES">
                    <option value="1">1 Pessoa</option>
                    <option value="2">2 Pessoas</option>
                    <option value="3">3 Pessoas</option>
                    <option value="4">4 Pessoas ou Mais</option>
                </select>
            </div>
            <div class="area-botao">
                <div id="botao-busca"><i class="fas fa-search"></i></div>
            </div>
        </div>
        <section class="area-apres-desc">
            <h2>${config.titleProducts}</h2>
            <p>${config.descriptionProducts}</p>
            <p>${config.subdescriptionProducts}</p>
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
        <iframe  id="gmap_canvas" src="https://maps.google.com/maps${config.localizationMap}" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
    </div>

    <div class="container-quem-somos">
        <div id="filtro-imagem">
            <h1>${config.slogan}</h1>
            <p>
                ${config.about}
            </p>


            <h2>Fale Conosco!</h2>
            <form:form id="frmForm" class="form-contato" method="post" modelAttribute="form">
                <div class="campo-form">
                    <form:input path="name" placeholder="Nome" />
                </div>

                <div class="campo-form" id="div-mai">
                    <form:input path="mail" placeholder="Seu Email" id="inp-mai"/>
                </div>

                <p id="msg-err-mai" style="background-color:red" class="msg-err-inactive">
                    Atenção: Este Email já está cadastrado no sistema!
                </p>

                <div class="campo-form">
                    <form:select path="gender" id="sex-inp" placeholder="Sexo" >
                        <form:option value="0">Masculino</form:option>
                        <form:option value="1">Feminino</form:option>
                    </form:select>
                </div>

                <div class="campo-form">
                    <form:label path="birthDate">Data de Nascimento</form:label>
                    <form:input path="birthDate" id="dnas-inp" type="date" placeholder="Data de Nascimento" />
                </div>

                <div class="campo-form">
                    <form:input path="phone" id="pho-inp" placeholder="Telefone" maxlength="15"/>
                </div>

                <div class="campo-form">
                    <form:input path="zipPostal" placeholder="Cep" id="zip-inp"/>
                </div>

                <div class="campo-form">
                    <form:input path="street" placeholder="Logradouro" id="str-inp"/>
                </div>

                <div class="campo-form">
                    <form:input path="district" placeholder="Bairro" id="dis-inp"/>
                </div>

                <div class="campo-form">
                    <form:input path="city" placeholder="Cidade" id="cit-inp" />
                </div>

                <div class="campo-form">
                    <form:input path="state" placeholder="Estado" id="sta-inp"/>
                </div>

                <div class="campo-form">
                    <form:textarea path="message" placeholder="Deixe a sua mensagem" id="mesg-inp" /> 
                </div>

                <form:button id="bt-submit">Cadastrar</form:button>
            </form:form>
        </div>
        <div class="quem-somos" id="sobre">
            <img src="${config.aboutImgUrlName}" />
        </div>
    </div>
    <script defer>
        window.addEventListener("load",()=>{
            let termo = document.querySelector('#lgpd-container');
            console.log("entrou no load do window")
            if(localStorage.getItem("aceite-termo") != 1){
                termo.style.display = 'flex';
            }
        })
    </script>
    <script src="/static/js/index.js" defer></script>
    <script src="/static/js/visual.js" defer></script>
</body>
</html>