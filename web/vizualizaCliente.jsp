<%@page import="dao.DaoClienteee"%>
<%@page import="dao.DaoClientee"%>
<%@page import="entidades.Cliente"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entidades.Pedidos"%>
<%@page import="dao.DaoPedido"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Produtos"%>
<%@page import="dao.DaoProdutos"%>
<%@page import="entidades.Categorias"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DaoCategoria"%>
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
    
   <%
        List<Cliente> cliente;
        DaoClienteee daoClientee = new DaoClienteee();
        cliente = daoClientee.listar();  
        Cliente clienteV = new Cliente();
        clienteV = daoClientee.retornaCliente(Integer.parseInt(session.getAttribute("id").toString()));
    %>
    
    <script>
         
        function validaEmail(){
            
            
            
            var comp = document.getElementById("emailTxt").value;
            
            <%
                for(Cliente c: cliente){
                    %>
                        if(comp === "<%out.print(c.getEmail());%>"){
                            alert("Email já está sendo utilizado, tente outro email!");     
                            document.getElementById("emailTxt").value = '';
                            document.getElementById("emailTxt").focus();
                        }   

                    <%
                }
                    
            %>

            
        };
        function validaCPF(){
            var comp = document.getElementById("cpfTxt").value;
            
            <%
                for(Cliente c: cliente){
                    %>
                        if(comp === "<%out.print(c.getCpf());%>"){
                            alert("CPF já está sendo utilizado, tente outro CPF!");     
                            document.getElementById("cpfTxt").value = '';
                            document.getElementById("cpfTxt").focus();
                        }   

                    <%
                }
                    
            %>

            
        };   
        
        function valida(){
            if(document.getElementById("nomeTxt").value === ""){
                alert("Campo nome está vazio!");
                document.getElementById("nomeTxt").focus();
            }
            else if(document.getElementById("cpfTxt").value === ""){
                alert("Campo CPF está vazio!");
                document.getElementById("cpfTxt").focus();
            }        
            else if(document.getElementById("telefone1Txt").value === ""){
                alert("Campo telefone 1 está vazio!");
                document.getElementById("telefone1Txt").focus();
            }  
            else if(document.getElementById("sobrenomeTxt").value === ""){
                alert("Campo Sobrenome está vazio!");
                document.getElementById("sobrenomeTxt").focus();
            }         
            else if(document.getElementById("dataNascimentoTxt").value === ""){
                alert("Campo Data de Nascimento está vazio!");
                document.getElementById("dataNascimentoTxt").focus();
            }       
            else if(document.getElementById("estadoTxt").value === ""){
                alert("Campo estado está vazio!");
                document.getElementById("estadoTxt").focus();
            }  
            else if(document.getElementById("bairroTxt").value === ""){
                alert("Campo bairro está vazio!");
                document.getElementById("bairroTxt").focus();
            }   
            else if(document.getElementById("numeroTxt").value === ""){
                alert("Campo número está vazio!");
                document.getElementById("numeroTxt").focus();
            } 
            else if(document.getElementById("cidadeTxt").value === ""){
                alert("Campo cidade está vazio!");
                document.getElementById("cidadeTxt").focus();
            }  
            else if(document.getElementById("ruaTxt").value === ""){
                alert("Campo rua está vazio!");
                document.getElementById("ruaTxt").focus();
            }              
            else if(document.getElementById("emailTxt").value === ""){
                alert("Campo email está vazio!");
                document.getElementById("emailTxt").focus();
            }  
            else if(document.getElementById("nomeUsuarioTxt").value === ""){
                alert("Campo nome usuário está vazio!");
                document.getElementById("nomeUsuarioTxt").focus();
            }  
          
            else{
                document.getElementById('formCadastro').submit();
            }
            

           
            
        };
    </script>    
</head>


    
<!--AfunÃ§Ã£o de aparecer os itens Ã© carregada com a pÃ¡gina-->
<body>
    <!--Ãrea de login-->
    <div id="telaLogin" data-aos="fade-up">
        <form action="ServletLogin" class="inputLogin" method="POST">
            <p>Login</p><input id="credLogin" name="credLogin" type="text" required>
            <p>Senha</p><input id="credSenha" name="credSenha" type="password" required>
            <div class=espacobtn>
                <button type="submit" class="botao" >Entrar</button>
                <button type="button" class="botao" onclick="href='index.jsp'" >Cancelar</button>
            </div>
        </form>
    </div>
    <!--Ãrea de cadastro-->
   
    <!--Faixa superior-->
    <header>
        <div id="topContainer">
            <!--Deixado para colocarmos botÃµes de redes sociais-->
            
        </div>
    </header>
    <div id="teste"></div>
        <!--Menu da pÃ¡gina-->
    <div id="containerBox">
        <nav id="MenuSuperior">
            <ul>
                <!--Itens de menu-->
                <li><a><img class="logoIndex" src="images/logo.png"></a></li>
                <li><a  href="index.jsp" >Home</a></li>                
                                
                <%
                if(session.getAttribute("name") != null && session.getAttribute("permissao") == "0"){
                    %>
                        <li><a href="meusPedidos.jsp" >Meus Pedidos</a></li>
                        <li><a href="pedido.jsp" >Monte seu lanche</a></li>
                    <%
                }
                else if(session.getAttribute("name") != null && session.getAttribute("permissao") == "1"){
                    %>
                        <li><a href="listaProdutos.jsp" >Consulta Produtos</a></li>
                        <li><a href="cadProdutos.jsp" >Cadastrar Produtos</a></li>
                        <li><a href="listaPedidos.jsp" >Consulta Pedidos</a></li>
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
            <section style="height: 1210px;">
                <div>
                    <!--Ãrea de separaÃ§Ã£o do sistema-->
                    <div style="padding-top: 77px;" id="quadro">
                        <div id="sistemaCliente">
                        
                        <div style="    display: block;" id="telaCadastro"  >
                            <form action="ServletAlteraCliente" class="inputCadastro" method="GET" id="formCadastro">
                                   <h2>Cadastro de cliente</h2>
                                   <div class="esquerda">
                                       <label for="nomeTxt">Nome </label>
                                       <input id="nomeTxt" name="nomeTxt" type="text" required>
                                       <label for="cpfTxt">CPF </label>
                                       <input type="text" name="cpfTxt" id="cpfTxt" placeholder="CPF" maxlength="14" onkeypress="somenteNumeros(this)" onfocusout="validaCPF()" required>
                                       <label for="telefone1Txt">Telefone 1 </label>
                                       <input type="text" id="telefone1Txt" name="telefone1Txt" onkeypress="somenteNumeros(this)" required>

                                   </div>
                                   <div class="direita">
                                       <label for="sobrenomeTxt">Sobrenome </label>
                                       <input id="sobrenomeTxt" name="sobrenomeTxt" type="text" required>
                                       <label for="dataNascimentoTxt">Data de Nascimento </label>
                                       <input type="date" id="dataNascimentoTxt" name="dataNascimentoTxt" required>
                                       <label for="telefone2Txt">Telefone 2 </label>
                                       <input type="text" id="telefone2Txt" name="telefone2Txt" onkeypress="somenteNumeros(this)" >
                                   </div>

                                   <div class="endereco">
                                       <label for="cadEndereco" class="cadEnderecoLabel">Endereço</label>
                                       <fieldset id=cadEndereco>
                                           <div class="esquerda">
                                               <label for="estadoTxt">Estado </label>
                                               <input id="estadoTxt" name="estadoTxt" required>
                                               <label for="bairroTxt">Bairro </label>
                                               <input id="bairroTxt" name="bairroTxt" required>
                                               <label for="numeroTxt">Número </label>
                                               <input type="text" id="numeroTxt" name="numeroTxt" onkeypress="somenteNumeros(this)" required>                  
                                           </div>
                                           <div class="direita">
                                               <label for="cidadeTxt">Cidade </label>
                                               <input type="text" id="cidadeTxt" name="cidadeTxt" required>
                                               <label for="ruaTxt">Rua </label>
                                               <input id="ruaTxt" name="ruaTxt" required>  
                                               <label for="complementoTxt">Compl. </label>
                                               <input id="complementoTxt" name="complementoTxt" required>

                                           </div>
                                       </fieldset>
                                   </div>
                                   <div class="confirmCad">
                                       <div>
                                           <label for="emailTxt">Email</label>
                                           <input type="email" id="emailTxt" name="emailTxt" onfocusout="validaEmail()" required>
                                       </div>                
                                       <div>
                                           <label for="nomeUsuarioTxt">Usuário</label>
                                           <input type="text" id="nomeUsuarioTxt" name="nomeUsuarioTxt">
                                       </div>
                                   </div>
                                   <div class="espacoBotoes">
                                       <button type="button" class="botao" onclick="valida()">Alterar</button>
                                       <button type="button" class="botao" onclick="window.location.href = 'index.jsp'">Cancelar</button>
                                   </div>
                               </form>        
                            
                            <script>
                            
    
    
               
            document.getElementById("nomeTxt").value = '<% out.print(clienteV.getNomecliente()); %>';
            document.getElementById("cpfTxt").value = '<% out.print(clienteV.getCpf()); %>';
            document.getElementById("telefone1Txt").value = '<% out.print(clienteV.getTelefone()); %>';
            document.getElementById("sobrenomeTxt").value = '<% out.print(clienteV.getSobrenomecliente()); %>';
            document.getElementById("dataNascimentoTxt").value = '<% out.print(clienteV.getDatanascimento()); %>';
            document.getElementById("estadoTxt").value = '<% out.print(clienteV.getEstado()); %>';
            document.getElementById("bairroTxt").value = '<% out.print(clienteV.getBairro()); %>';
            document.getElementById("numeroTxt").value = '<% out.print(clienteV.getNumero()); %>';
            document.getElementById("cidadeTxt").value = '<% out.print(clienteV.getCidade()); %>';
            document.getElementById("ruaTxt").value = '<% out.print(clienteV.getRua()); %>';
            document.getElementById("emailTxt").value = '<% out.print(clienteV.getEmail()); %>';
            document.getElementById("nomeUsuarioTxt").value = '<% out.print(clienteV.getNomeusuario()); %>';
            document.getElementById("senhaTxt").value = '<% out.print(clienteV.getSenha()); %>';
            
            
                            </script>
                              
                               

                                
                        </div>
                        </div>
                            
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