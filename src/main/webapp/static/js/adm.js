let tituloTopo = document.querySelector('#titleTop');
let imgTopo = document.querySelector('#imgTopUrlName');
let msgTopo = document.querySelector('#msgTop');
let titProdutos = document.querySelector('#titleProducts');
let desProdutos = document.querySelector('#descriptionProducts');
let subProdutos = document.querySelector('#subdescriptionProducts');
let locMap = document.querySelector('#localizationMap');
let desMap = document.querySelector('#mapDescription');
let slogan = document.querySelector('#slogan');
let aboImg = document.querySelector('#aboutImgUrlName');
let about = document.querySelector('#about');

async function atualizarConfigs(){
    if(confirm("Deseja prosseguir com a atualização?")){
        let data = 
        {
            id:"1",
            titleTop: tituloTopo.value,
            imgTopUrlName: imgTopo.value,
            msgTop: msgTopo.value,
            titleProducts: titProdutos.value,
            descriptionProducts: desProdutos.value,
            subdescriptionProducts: subProdutos.value,
            localizationMap: locMap.value,
            mapDescription: desMap.value,
            slogan: slogan.value,
            aboutImgUrlName: aboImg.value,
            about: about.value
        }

        let configurationsFetch = {
            method: 'POST', 
            body: JSON.stringify(data)
        };

        alert('Realizando o envio... Clique em ok e aguarde alguns instantes');
        let response = await fetch("http://localhost:8080/config/alter",configurationsFetch);
        let status = response.status;
        if(status === 200 || status == 201){
            alert('Alteração realizada com sucesso!');
        }else{
            alert('Houve um erro, favor tentar novamente mais tarde!');
        }

    }

}














