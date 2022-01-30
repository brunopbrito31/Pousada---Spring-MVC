let sobre = document.querySelector('#sobre');
let quartos = document.querySelector('#quartos');
let rota = document.querySelector('#mapa');
let linkQuartos = document.querySelector('#link-quartos');
let linkMapa = document.querySelector('#link-mapa');
let linkSobre = document.querySelector('#link-sobre');

function ativarQuartos(){
    linkQuartos.classList.add('ativo');
    linkMapa.classList.remove('ativo');
    linkSobre.classList.remove('ativo');
}

function ativarMapa(){
    linkMapa.classList.add('ativo');
    linkSobre.classList.remove('ativo');
    linkQuartos.classList.remove('ativo');
}

function ativarSobre(){
    linkSobre.classList.add('ativo');
    linkMapa.classList.remove('ativo');
    linkQuartos.classList.remove('ativo');
}

window.addEventListener('scroll', function(e) {
    if(
        window.scrollY >= quartos.offsetTop && 
        window.scrollY < rota.offsetTop
    ){
        ativarQuartos();
    }
    if(
        window.scrollY >= (rota.offsetTop) && 
        window.scrollY < sobre.offsetTop
    ){
        ativarMapa();
    }
    if(
        window.scrollY >= sobre.offsetTop 
    ){
        ativarSobre();
    }
});

quartos.addEventListener('click', function(){
    ativarQuartos();
});

rota.addEventListener('click',function(){
    ativarMapa();
})

sobre.addEventListener('click',function(){
    ativarSobre();
})