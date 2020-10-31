/*Validação de teste de login*/
function autenticacao(){
    
    let login = document.getElementById('credLogin');
    let password = document.getElementById('credSenha');

    if(login.value == "" || password.value == ""){
        alert("Digite o usuário e senha!")
    }else{
        let l = login.value
        let p = login.value

        if( l == "admin" && p == "admin" ){
            alert(`Bem vindo usuário ${l}!`)
            alert("{ usuario: "+login.value+", senha: "+password.value+" }")
        } else{
            alert("Usuário e senha incorretos");
        }   
    }
};

/*Função de rolagem animada*/
$('nav a#linkMenu').click(function(e){
    e.preventDefault();
    var id=$(this).attr('href'),
        targetOffset = $(id).offset().top,
        menuHeight = $('nav').innerHeight();
    $('html, body').animate({
       scrollTop: targetOffset - menuHeight
    },500);
})
$('div#quadro h2 a').click(function(e){
    e.preventDefault();
    var id=$(this).attr('href'),
        targetOffset = $(id).offset().top,
        menuHeight = $('nav').innerHeight();
    $('html, body').animate({
        scrollTop: targetOffset - menuHeight
    },500);
})

/*Campos que irão números*/
function somenteNumeros(num) {
    var er = /[^0-9.]/;
    er.lastIndex = 0;
    var campo = num;
    if (er.test(campo.value)) {
      campo.value = "";
    } else{
        return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
    }
}

/*Configurações do slick - Troca de imagens de Banners*/
$('.slides').slick({
    autoplay: true,
    speed:500,
    autoplaySpeed: 3000,
    fade: true,
    pauseOnHover: false,
    arrows: false,
});



/*Fnção para abrir a janela de login e desabilitar os links da página*/
function abrir() {
    let login = document.querySelector('div#telaLogin');
    login.style.display="block";
};
function fechar() {
    let login = document.querySelector('div#telaLogin');
    login.style.display="none";
};
function cadastrar(){
    let telacad = document.querySelector('div#telaCadastro');
    telacad.style.display="block";
};
function cancelar(){
    let telacad = document.querySelector('div#telaCadastro');
    telacad.style.display="none";
};
function voltarHome(){
    window.document.location.href = 'index.jsp';
}

function voltarHome(){
    window.open('index.jsp');
};