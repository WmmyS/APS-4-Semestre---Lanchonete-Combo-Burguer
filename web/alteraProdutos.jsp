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
    <!--links de funcionalidades da página-->
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="slick/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="aos/aos.css">
</head>


    
<!--Afunção de aparecer os itens é carregada com a página-->
<body>
    <!--Área de login-->

    <!--Área de cadastro-->
   
    <!--Faixa superior-->
    <header>
        <div id="topContainer">
            <!--Deixado para colocarmos botões de redes sociais-->
            <%
                if(session.getAttribute("name") == null){
                    %>
                    <div id="linkLogin" onclick="abrir();">
                        <a>Login</a>
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
        <!--Menu da página-->
    <div id="containerBox">
        <nav id="MenuSuperior">
            <ul>
                <!--Itens de menu-->
                <li><a><img class="logoIndex" src="images/logo.png"></a></li>
                                             
                                
                <%
                if(session.getAttribute("name") != null && session.getAttribute("permissao") == "0"){
                    %>
                        <li><a  href="index.jsp" >Home</a></li>                
                        <li><a id="linkMenu" href="#contato" >Contato</a></li>
                        <li><a href="meusPedidos.jsp" >Meus Pedidos</a></li>
                        <li><a href="pedido.jsp" >Monte seu lanche</a></li>
                    <%
                }
                else if(session.getAttribute("name") != null && session.getAttribute("permissao") == "1"){
                    %>
                        <li><a  href="index.jsp" >Home</a></li>  
                        <li><a href="listaProdutos.jsp" >Consulta Produtos</a></li>
                        <li><a href="cadProdutos.jsp" >Cadastrar Produtos</a></li>
                        <li><a href="listaPedidos.jsp" >Consulta Pedidos</a></li>
                    <%
                }

                   
                %>   

            </ul>
        </nav>
        <!--div de separação do menu e o resto da página-->
        <div id="app">
            <div class="slides">
                <div class="slide1"><img src="images/slide1.png"></div>
                <div class="slide1"><img src="images/slide2.png"></div>
                <div class="slide1"><img src="images/slide3.png"></div>
            </div>
      
            
            <script>
                function voltar(){
                   window.location.href = "listaProdutos.jsp";
                }
                function confere(){
                    
                    if(document.getElementById("categoria").value === ""){
                        alert("Selecione a categoria!");
                        document.getElementById("categoria").focus();
                        
                    }
                    else{
                        document.getElementById("cadProduto").submit();
                    }
                }
            </script>
            <!--Conteúdo da página-->
            <section>
                <div>
                    <!--Área de separação do sistema-->
                    <div id="quadro">
                        <h2>Cadastro de Produtos</h2>
                        <div id="sistema" style="    width: 50%;" >
                            
                            <%
                                DaoCategoria daoCat = new DaoCategoria();
                                List<Categorias> categorias = new ArrayList<Categorias>();
                                categorias = daoCat.listarCategoria();
                            %>
                            <form action="ServletAlteraProduto" method="GET" id="cadProduto">
                                <center>
                                <table border="0">
                                    <tr>
                                        <td><label style="font-size: 17px;" for="text">Codigo Produto </label>
                                            <input type="text" id="codP" name="codP" readonly="true"></td></tr>
                                        <tr><td><label style="font-size: 17px;     " for="categoria">Categoria</label>
                                        <select style="  margin-left: -6px;  width: 70.8%;" name="categoria" id="categoria" >
                                            <option value="">Selecione...</option>
                                            <% 
                                            for(int i = 0; i < categorias.size(); i++){
                                            %> <option value="<% out.print(categorias.get(i).getIdcategorias()); %>"><% out.print(categorias.get(i).getDescricao()); %></option> <%
                                            }
                                            %>                                   
                                            </select></td></tr>                                        
                                        <td><label style="font-size: 17px;" for="text">Descri��o </label>
                                            <input type="text" id="desc" name="desc"></td></tr>
                                        <td><label style="font-size: 17px; " for="text">Valor </label>
                                            <input style="    margin-left: 31px;" type="text" id="valor" name="valor"></td></tr>      
                                        <td><center><input type="button" value="Alterar" onclick="confere()"> <input type="button" value="Voltar" onclick="voltar()"> </center></td></tr> 

                                    
                                </table>
                                </center>
                            </form>
                                                            <%
                                Produtos p = new Produtos();
                                DaoProdutos daoP = new DaoProdutos();
                                p = daoP.buscaProduto(Integer.parseInt(request.getParameter("codigo")));
                                

                                
                                %>
                                <script>
                                    document.getElementById("codP").value = '<%out.print(p.getIdproduto()); %>';
                                    document.getElementById("desc").value = '<%out.print(p.getDescricao()); %>';
                                    document.getElementById("valor").value = '<%out.print(p.getValor()); %>';
                                    document.getElementById("categoria").value = '<%out.print(p.getIdcategoria().getIdcategorias()); %>';
                                   
                                </script>
                                <%
                                

                            %>      

                                
                                
                        </div>
                            
                    </div>

                </div>
            </section>
            <!--Rodapé da página-->
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
<!--Scripts relacionados à funcionalidades da página-->
<script type="text/javascript" src="slick/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="slick/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="slick/slick.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="aos/aos.js"></script>
</html>