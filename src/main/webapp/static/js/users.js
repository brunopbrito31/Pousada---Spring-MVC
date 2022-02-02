function editarUsuario(idUsu){
    console.log('Entrou na função de edição, id='+idUsu);
}

async function desativarUsuario(idUsu){
    let emailUsu = document.querySelector(`#email-${idUsu}`);
   
    if(confirm('Deseja realmente excluir o usuário?')){
        if(emailUsu.value == sessionStorage.getItem('user')){
            alert('Não pode excluir a si mesmo');
        }else{
            let response = await fetch(`http://localhost:8080/users/${idUsu}`,{method:'DELETE'});
            let status = response.status;
            let userInHtml = document.querySelector(`#card-usuario-${idUsu}`);
            
            if(status === 204){
                document.querySelector('.area-cards').removeChild(userInHtml);
            }else{
                alert("houve um erro na exclusão");
            }  
        }
    }
}