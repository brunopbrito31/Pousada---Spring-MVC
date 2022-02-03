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
        <link rel="stylesheet" href="/static/css/contacts-list.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" 
            integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" 
            crossorigin="anonymous"
        >
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/includes/inc-top-are-res.jsp" />
        <main>
            <form id="form-uplo">
                <input type="file" name="file" id="file-input">
                <div class="container-imagem">
                    <img src="https://th.bing.com/th/id/R.9a43534a5945e8ad6a6f68477130fa4e?rik=9WPx%2fvyxzyfHcw&riu=http%3a%2f%2fwww.infoescola.com%2fwp-content%2fuploads%2f2009%2f10%2fQuadrado-preto-sobre-um-fundo-branco.jpg&ehk=XJF%2bZpKb3Otac9SeSDwL1jMddK6WPx5rh1X73iovGGU%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1" id="img-to-up">
                </div>
                <div>
                    <label for="nameArq">Nome do Arquivo</label>
                    <input type="text" id="nameArq">
                </div>
                <div>
                    <label for="title">Titulo da Imagem</label>
                    <input type="text" id="title">
                </div>
            </form>
            <button id="bt-env" type="button">Enviar</button>
        </main>

        <%-- Separar no css e js correspondente após terminar a página --%>
        <style>
            .container-imagem{
                width: 40vw;
                height: 40vw;
                min-width:40vw;
                min-height:40vw;
                max-width:40vw;
                max-height:40vw;
                background-color:blue;
                display: flex;
                justify-content:center;
                align-items:center;
            }

            #img-to-up{
                width:98.5%;
                height: 98.5%;
            }
            

        </style>

        <script defer >
            let previsualizacaoImagem = document.querySelector('#img-to-up');
            var inputImagem = document.querySelector('#file-input');
            
            inputImagem.addEventListener('change',(evt)=>{
                console.log(inputImagem.value)
                previsualizacaoImagem.src = URL.createObjectURL(inputImagem.files[0]);
            });

            
            let botaoEnviar = document.querySelector('#bt-env');
            botaoEnviar.addEventListener('click',async ()=>{
                let objImage = {
                    nameArq: document.querySelector('#nameArq').value,
                    title: document.querySelector('#title').value
                }
                const formData = new FormData();
                formData.append('file',inputImagem.files[0]);
                formData.append('nameArq',objImage.nameArq);
                formData.append('title',objImage.title);
                let config = {
                    method:"post",
                    body: formData
                }
                let response = await fetch('http://localhost:8080/images',config);

                console.log(response);
            })


        </script>
    </body>
</html>