let botaoAdicionar = document.querySelector('#botao-adicionar');


let formUpload = document.querySelector('#form-uplo');
var contadorImg = 1;

function incrementarContador(){
    return contadorImg+1;
}

function adicionarElemento(contadorImg){
    let novoEleImg = 
    `<fieldset class="card-imagem" id="img-${contadorImg}">
        <div class="container-imagem" onclick="containerClick(${contadorImg})">
            <img src="https://www.sindssedf.org.br/wp-content/uploads/2015/04/sem-imagem-800.gif" id="img-to-up-${contadorImg}">
        </div>
        <input type="file" name="file" id="file-input-${contadorImg}" onchange="carregarVisu(${contadorImg})">
        <div class="campo-form">
            <input type="text" id="nameArq-${contadorImg}" placeholder="Nome da Imagem">
        </div>
        <div class="campo-form">
            <input type="text" id="title-${contadorImg}" placeholder="Titulo da Imagem">
        </div>
        <div class="area-botoes" id="area-botoes-${contadorImg}"> 
            <button class="bt-up" type="button" onclick="salvarImagem(${contadorImg})">Upload</button>
            <button class="bt-del" type="button" onclick="deletarImg(${contadorImg})">Excluir</button>
        </div>
    </fieldset>`
    formUpload.insertAdjacentHTML('beforeend',novoEleImg);
}


botaoAdicionar.addEventListener('click',()=>{
    contadorImg = incrementarContador();
    adicionarElemento(contadorImg);
})

function deletarImg(num){
    document.querySelector(`#img-${num}`).remove();
}

function containerClick(valueId){
    let inputImagem = document.querySelector(`#file-input-${valueId}`);
    inputImagem.click();
}

function carregarVisu(valueId){
    let inputImagem = document.querySelector(`#file-input-${valueId}`);
    let previsualizacaoImagem = document.querySelector(`#img-to-up-${valueId}`);
    previsualizacaoImagem.src = URL.createObjectURL(inputImagem.files[0]);
}



async function salvarImagem (cont){
    if(confirm('Confirma o Envio?')){
        let inputImagem = document.querySelector(`#file-input-${cont}`);
        let areaBotoes = document.querySelector(`#area-botoes-${cont}`);

        let objImage = {
            nameArq: document.querySelector(`#nameArq-${cont}`).value,
            title: document.querySelector(`#title-${cont}`).value
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
        let status = response.status;

        if(status === 201){
            let elementoSucesso = 
            `<div style="background-color:green; text-align:center">Imagem Enviada!</div>`
            while(areaBotoes.firstChild){
                areaBotoes.removeChild(areaBotoes.firstChild);
            }
            areaBotoes.insertAdjacentHTML('beforeend',elementoSucesso);
            alert('Imagem enviada com sucesso!');
        }else{
            alert('Houve uma falha no envio, tente novamente mais tarde!');
        }

    }
}