<%@page import="java.sql.SQLException"%>
<%@page import="dao.DaoClienteee"%>
<%@page import="entidades.Cliente"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="images/icone.png" >
    <title>Burger - Venha Montar seu lanche</title>
    <!--links de funcionalidades da pÃ¡gina-->
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="slick/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="aos/aos.css">
</head>
<!--AfunÃ§Ã£o de aparecer os itens Ã© carregada com a pÃ¡gina-->
<body onmouseover="AOS.init()">
    <!--Ãrea de login-->

    
    <div id="telaLogin" data-aos="fade-up">
        <form action="ServletLogin" class="inputLogin" method="POST">
            <p>Login</p><input id="credLogin" name="credLogin" type="text" required>
            <p>Senha</p><input id="credSenha" name="credSenha" type="password" required>
            <br><br>

            <div class=espacobtn>
                
                <button type="submit" class="botao">Entrar</button>
                <button type="button" class="botao" onclick="fechar()">Cancelar</button>
            </div>
        </form>
        <a style="color: white;
                  text-decoration: none;
                  position: absolute;
                  margin-top: 235px;
                  margin-left: 50px;
                  font-family: 'Arial Black'"
                  id="btnCad" href="cadCliente.jsp">Cadastrar</a>
    </div>

    <!--Faixa superior-->
    <header>
        <div id="topContainer">
            <!--Deixado para colocarmos botÃµes de redes sociais-->
            
            <%
                if(session.getAttribute("name") == null){
                    %>
                    <div id="linkLogin" onclick="abrir();" style="width: 200px">
                        <a>Área de Login</a>
                    </div>
                    <%
                }
                else{
                    %>
                    <div id="linkLogin">
                        
                        <form action="ServletFinaliza" method="POST" id="formFinaliza" >
                            <a style="margin-right:10px; text-decoration: none; color: white;" align-content: href="vizualizaCliente.jsp"><% out.println("Seja bem vindo "+session.getAttribute("name"));%></a>
                            <button id="botaoSair" type="submit"><img src="images/sair.png" style="width: 20px; margin-top: 1px;"></button>
                        </form>
                    </div>
                    <%                     
                }                    
                %>

        </div>
    </header>
                
    <div id="teste"></div>
        <!--Menu da pÃ¡gina-->
    <div id="containerBox">
        <nav id="MenuSuperior">
            <ul>
                <!--Itens de menu-->
                <li><a><img class="logoIndex" src="images/logo.png"></a></li>
                
                
                <%
                if(session.getAttribute("name") != null && session.getAttribute("permissao") == "0"){
                    %>
                        <li><a  href="index.jsp" >Home</a></li>                
                        <li><a id="linkMenu" href="#sobre" >Sobre nós</a></li>
                        <li><a id="linkMenu" href="#contato" >Contato</a></li>
                        <li><a href="meusPedidos.jsp" >Meus Pedidos</a></li>
                        <li><a href="pedido.jsp" >Monte seu lanche</a></li>
                    <%
                }
                else if(session.getAttribute("name") != null && session.getAttribute("permissao") == "1"){
                    %>
                        <li><a  href="index.jsp" >Home</a></li>
                        <li><a id="linkMenu" href="#contato" >Contato</a></li>
                        <li><a href="listaProdutos.jsp" >Consulta Produtos</a></li>
                        <li><a href="cadProdutos.jsp" >Cadastrar Produtos</a></li>
                        <li><a href="listaPedidos.jsp" >Consulta Pedidos</a></li>
                    <%
                }
                else if((session.getAttribute("name") == null && session.getAttribute("permissao") != "1") || (session.getAttribute("name") == null && session.getAttribute("permissao") != "0")){
                    %>
                        <li><a  href="index.jsp" >Home</a></li>                
                        <li><a id="linkMenu" href="#sobre" >Sobre nós</a></li>
                        <li><a id="linkMenu" href="#contato" >Contato</a></li>
                        <li><a id="linkMenu" href="#quadro" >Conheça nossas Combinações</a></li>
                        <li><a href="cadCliente.jsp" >Cadastre-se</a></li>
                    <%   
                }
   
                %>                
                
            </ul>
        </nav>
        <!--div de separaÃ§Ã£o do menu e o resto da pÃ¡gina-->
        <div id="app">
            <div class="slides">
                <div class="slide1"><img src="images/slide1.png"></div>
                <div class="slide1"><img src="images/slide2.png"></div>
                <div class="slide1"><img src="images/slide3.png"></div>
            </div>
            <!--ConteÃºdo da pÃ¡gina-->
            <section>
                <div>
                    <!--Ãrea de separaÃ§Ã£o do sistema-->
                    <div id="quadro">
                        <h2><a href="#quadro">Monte seu Lanche</a></h2>
                        <% if((session.getAttribute("name") == null && session.getAttribute("permissao") != "1") || (session.getAttribute("name") == null && session.getAttribute("permissao") != "0")){ %>
                        <div id="sistema" data-aos="zoom-in" >
                            <p style="font-size: 16pt; font-style: italic; text-align: justify; max-width: 500px; margin-left: 50%; transform: translateX(-50%);">Aqui você pode Montar seu lanche personalizado 
                                com os ingrdientes de sua preferência. Faça o cadastro ou entre com sua conta
                                agora para fazer seu pedido.
                            </p>
                            <div>
                            <a class="botao"  href="cadCliente.jsp" style="font-size: 18pt; color: black; margin-left: 50px; padding: 10px 30px; color: #FFF; ;">Cadastrar</a>
                            <a class="botao"  onclick="abrir()" style="font-size: 18pt; color: black; margin-left: 50px; padding: 10px 30px; color: #FFF; ">Login</a>
                            </div>       
                        </div>
                        <% } else if(session.getAttribute("name") != null && session.getAttribute("permissao") == "0"){ %>
                            <div id="sistema" data-aos="zoom-in" >    
                                <a class="botao"  href="pedido.jsp" style="font-size: 18pt; color: black; margin-left: 50px; padding: 10px 30px; color: #FFF; ;">Monte seu lanche</a>
                            </div>
                        <% } else if(session.getAttribute("name") != null && session.getAttribute("permissao") == "1"){ %>
                        <div id="sistema" data-aos="zoom-in" >
                            <h1>Modo Administrador</h1>
                        </div>
                        <% }%>
                    </div>
                    <div id="sobre" data-aos="fade-up" >
                        <h2>Sobre nós</h2>
                        <p>A Combo Burger começou no ano de 2020 como um projeto de um grupo de jovens
                        desenvolvedores na criação de uma lanchonete virtual que o cliente pode personalizar
                        seus lanches. Sempre visamos a qualidade dos produtos, preços acessíveis e satisação 
                        de nossos clientes com nosso time de colaboradores desempenhados a entragar um ótimo lanche.
                        Estamos em constante evolução e melhoria para sermos uma das maiores redes de fastfood virtual.
                        Agradecemos a preferência!</p>
                    </div>
                </div>
            </section>
            <!--RodapÃ© da pÃ¡gina-->
            <footer>
                <div id="contato" >
                    <div style="margin-top: 25px;">
                        <img src="images/Unip.png">
                    </div>
                    <h3>Desenvolvido por:</h3>
                    <p>N429160 - WELLINGTON F DE OLIVEIRA</br>
                       F0359E1 - LUCAS CAMPANUCHI CORREA</br>
                       N485HJ6 - VINICIUS ALVES PANOBIANCO</br>
                       N3855J3 - WESLEY MARCOS M DOS SANTOS</p>
                    <div>
                        
                    </div>
                    
                </div>
                
            </footer>
        </div>
    </div>
</body>
<!--Scripts relacionados Ã  funcionalidades da pÃ¡gina-->
<script type="text/javascript" src="slick/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="slick/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="slick/slick.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="aos/aos.js"></script>
</html>