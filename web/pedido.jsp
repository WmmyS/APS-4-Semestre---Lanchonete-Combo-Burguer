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
    <div id="telaLogin" data-aos="fade-up">
        <form action="ServletLogin" class="inputLogin" method="POST">
            <p>Login</p><input id="credLogin" name="credLogin" type="text" required>
            <p>Senha</p><input id="credSenha" name="credSenha" type="password" required>
            <div class=espacobtn>
                <button type="submit" class="botao" >Entrar</button>
                <button type="button" class="botao" onclick="fechar()">Cancelar</button>
            </div>
        </form>
    </div>
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
            <!--Conteúdo da página-->
            <section>
                <div>
                    <!--Área de separação do sistema-->
                    <div id="quadro">
                        <h2><a href="#quadro">Monte seu Lanche</a></h2>
                        <div  id="sistema"  >
                            
                            <%
                                DecimalFormat f = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));            
                                f.setMinimumFractionDigits(2); 
                                f.setParseBigDecimal (true);
                                
                                
                                DaoCategoria daoCategoria = new DaoCategoria();
                                List<Categorias> categoria = new ArrayList<Categorias>();
                                
                                try{
                                    categoria = daoCategoria.listarCategoria();
                                }catch(SQLException a){
                                    
                                }
                                
                              
                                DaoProdutos dp = new DaoProdutos();
                                List<Produtos> produtos = new ArrayList<Produtos>();
                                            
                                try{
                                    produtos = dp.listarProdutos();
                                }catch(SQLException b){
                                    
                                }
                                            
                                %>
                                
                                <script>
                                    function Teste(){
                                        
                                        if(document.getElementsByName("Alface").selected){
                                           alert("a");    
                                        }                
                                    };
                                </script>                                
                                
                                
                                
                                <form action="ServletConfirmaPedido" method="POST">
                                    <%
                                for(int i = 0; i < categoria.size(); i++){
                                    %><p style=" font-size: 24px; text-align: left; padding-left: 20px;"><% out.print(categoria.get(i).getDescricao()); %></p>
                                    
                                    
                                    <div id="margem3" style="    background: #fff; color: #000; padding: 10px; margin: 10px;">
                                        <table id="tabela<%out.print(i);%>" border="1" style="text-align: center;
                                                                    width: 100%;
                                                                    border: cornsilk;
                                                                    font-size: 24px;">
                                            <tr>
                                                <td style="width: 150px;">Selecionar</td>
                                                <td style="width: auto;">Descri��o</td>
                                                <td style="width: 200px;">Valor</td>
                                            </tr>
                                            <%                                              
                                                for(int a = 0; a < produtos.size(); a++){
                                                    if(produtos.get(a).getIdcategoria().getIdcategorias() - 1    == i){
                                                        %><tr>
                                                            <% if(i <2){ %>
                                                            <td ><input type="radio" id="<%out.print(produtos.get(a).getDescricao());%>" name="<%out.print(produtos.get(a).getIdcategoria().getIdcategorias());%>" value="<%out.print(produtos.get(a).getDescricao());%>" onclick="Teste()" required> </td>
                                                            <% } 
                                                                else{%>
                                                            <td ><input type="checkbox" id="<%out.print(produtos.get(a).getDescricao());%>" name="<%out.print(produtos.get(a).getDescricao());%>" value="<%out.print(produtos.get(a).getDescricao());%>" onclick="Teste()" > </td>
                                                            <% } %>
                                                            <td ><%out.print(produtos.get(a).getDescricao());%></td>
                                                            <td ><%out.print(f.format(produtos.get(a).getValor()));%></td>
                                                          </tr><%
                                                    }
                                                }
                                                %>
                                                
                                        </table>
                                      </div><%
                                }
                            %>
                            <input style="display: block;
                                            float: right;
                                            margin: 40px;
                                            width: 150px;
                                            height: 30px;
                                            background-color: white;
                                            font-family: cursive;
                                    "type="submit" value="Finalizar">
                                </form>
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