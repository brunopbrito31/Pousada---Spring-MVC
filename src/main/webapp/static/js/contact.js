async function apagar(num){
    if(confirm("Deseja prosseguir com a exclusão?")){
        let response = await fetch(`http://localhost:8080/contacts/${num}`,{method:'DELETE'});
        let status = response.status;
        let contactInHtml = document.querySelector(`#contact-${num}`);
        
        if(status === 204){
            document.getElementsByTagName('tbody')[0].removeChild(contactInHtml);
        }else{
            alert("houve um erro na exclusão");
        }   
    }
}

async function responder(num){

    let response = await fetch(`http://localhost:8080/contacts/${num}`,{method: 'GET'});
    let data = await response.json();
    let status = response.status;

    if(status === 200) {
        
        let containerPai = document.querySelector('#div-pai-tabela');
        let container = 
        `<div class="container-resultado-resposta-ativo">
            <p id="wel-ms">
                Nome do(a) cliente: ${data.user.name} |Email: ${data.user.mail}<br>Phone:(${data.user.phones[0].areaCode}) ${data.user.phones[0].number}<br> 
                Data do Contato: ${data.sendDate.substring(0,10)} 
                <i class="fas fa-times-circle" id='bt-fech' onclick='fecharModal()'></i>
            </p>
            <p>Mensagem do Cliente:</p>
            <textarea id="msg-clie" disabled>${data.msg.trim()}</textarea>

            <div class="container-resposta-consultor" id="form-resu-resp">
                <form id="form-res" class="form-env" onsubmit="enviarResposta(e)" >
                    <input type="hidden"  id="idContact" value=${data.id} />
                    <div class="campo-form">
                        <label for="res">Resposta:</label><br>
                        <textarea id="res" name="msgres" placeholder="Digite aqui a resposta"></textarea>
                    </div>
                    <div class="campo-sub">
                        <button id="subm" type="button" onclick='enviarResposta()'>Enviar Resposta</button>>
                    </div>
                </form>
            </div>
        </div>`

        containerPai.insertAdjacentHTML("afterbegin",container);
    }
}
async function enviarResposta(){
    let idContact = document.querySelector('#idContact').value;
    let msg = document.querySelector('#res').value;
    let configurationsFetch = {method: 'POST',body: msg};

    alert('Realizando o envio... Clique em ok e aguarde alguns instantes')
    let response = await fetch(`http://localhost:8080/contacts/response?idcontact=${idContact}`,configurationsFetch);
    let status = response.status;
    if(status === 200) {

        alert('Resposta Enviada Com Sucesso!');
        let lineTr = document.querySelector(`#contact-${idContact}`);

        if(sessionStorage.getItem('aut') === 0){
            document.getElementsByTagName('tbody')[0].removeChild(lineTr);
        }else{
            let divStatus = lineTr.children[5];
            let divButton = lineTr.children[6];
            let textNode = document.createTextNode('Fechado');
            divStatus.replaceChild(textNode, divStatus.childNodes[0]);
            divButton.removeChild(divButton.children[0]);
            divButton.insertAdjacentHTML("afterbegin",`<button onclick='apagar(${idContact})' class='btn-cont'>Apagar</button>`);
        }

        fecharModal();

    }else if(status === 404) {
        alert('Houve uma falha no envio, tente novamente mais tarde')
    }else if(status === 500){
        alert('Houve uma falha de comunicação no servidor, tente novamente mais tarde')
    }
}

let botaoFecharModal = document.querySelector('#bt-fech');

function fecharModal(){
    let containerPai = document.querySelector('#div-pai-tabela');
    let modalAux = document.querySelector('.container-resultado-resposta-ativo');
    containerPai.removeChild(modalAux);
}