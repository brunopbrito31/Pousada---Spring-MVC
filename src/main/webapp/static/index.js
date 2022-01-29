let mail  = document.querySelector('#inp-mai');
let msgE  = document.querySelector('#msg-err-mai');

mail.addEventListener('input',async ()=>{

    msgE.classList.remove('msg-err-active');
    let response = await fetch(`http://localhost:8080/users/search?mail=${mail.value}`);
    let status = response.status;

    if(status === 200){
        msgE.classList.remove('msg-err-inactive');
        msgE.classList.add('msg-err-active');
    }else{
        msgE.classList.add('msg-err-inactive');
    }    
});

/* Máscaras ER */
function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}
function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}
function mtel(v){
    v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
    v=v.replace(/^(\d{2})(\d)/g,"($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
    v=v.replace(/(\d)(\d{4})$/,"$1-$2"); //Coloca hífen entre o quarto e o quinto dígitos
    return v;
}
function id( el ){
	return document.getElementById( el );
}
window.onload = function(){
	id('pho-inp').onkeyup = function(){
		mascara( this, mtel );
	}
}

