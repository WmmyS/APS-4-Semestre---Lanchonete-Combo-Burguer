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



<%
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
    


    List<Pedidos> pedidos = new ArrayList<Pedidos>();
    DaoPedido daoPedido = new DaoPedido();

    if(request.getParameter("dataI") != null && request.getParameter("dataF") != null){        
        try{
            dt.parse(request.getParameter("dataI"));
            try{
                dt.parse(request.getParameter("dataF"));
                %> <script> 
                    if(<% out.print(request.getParameter("dataI"));%> !== "" & <% out.print(request.getParameter("dataF")); %> !== ""){

                        <%
                        try{
                            pedidos = daoPedido.buscapedidoData(request.getParameter("dataI"), request.getParameter("dataF"));   


                        }catch(SQLException e){

                        }   
                        %>
                    }
            

            </script><%
            }catch(Exception a){
                %><script> alert('Data Inicial n�o est� no formato correto'); </script><%
            }

        }catch(Exception b){
                %><script> alert('Data Final n�o est� no formato correto'); </script><%
        }
    }
    

%>
    

   
    
    
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
            

            <!--Conteúdo da página-->
            <section>
                <div>
                    <!--Área de separação do sistema-->
                    <div id="quadro">
                        <% if(session.getAttribute("name") != null && session.getAttribute("permissao") == "1"){ %>
                        <h2>Consulta de pedidos por Data</h2>
                        <div id="sistema" style="    " >
                            
                            <form>
                                <label style="font-size: 17px;" for="text">Data Inicial </label>
                                <input type="date" style="height: 30px; font-size: 14pt" name="dataI" required>
                                <label style="font-size: 17px;" for="text">Data Final </label>
                                <input type="date" style="height: 30px; font-size: 14pt" name="dataF" required>
                                <input class="botao" type="submit" value="Obter" >
                                <input class="botao" style="margin-left: -10px" type="button" value="Limpar" onclick="limpar()">
                                
                            </form>
                            <br>
                                <center>
                                    
                                    <%
                                       
                                        
                                        
                                        
                                        if(request.getParameter("dataI") != null && request.getParameter("dataF") != null){
                                            %><p style="font-size: 21px;">Pedidos realizados entre <% out.print(df.format(dt.parse(request.getParameter("dataI"))) +" e "+df.format(dt.parse(request.getParameter("dataF")))); %></p><%
                                        }
                                    
                                    %>
                                
                                    <form action="vizualizaPedido.jsp">
                                <table border="1">
                                    <tr>
                                        <th style="font-size: 22px; width: 154px;">Selecionar</th>
                                        <th style="font-size: 22px; width: 154px;">C�digo Pedido</th>
                                        <th style="font-size: 22px; width: 350px;">Cliente</th>
                                        <th style="font-size: 22px; width: 180px;">Data Pedido</th>
                                        <th style="font-size: 22px; width: 100px;">Valor</th>
                                    </tr>

                                
                                
                                <%
                                DecimalFormat f = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));            
                                f.setMinimumFractionDigits(2); 
                                f.setParseBigDecimal (true);
                                    
                                
                                    
                                if(pedidos != null){
                                    float valor = 0;
                                    for(int i = 0; i < pedidos.size(); i++){
                                        valor+=pedidos.get(i).getValorpedido();                                       


                                %><tr>
                                    <td style="font-size: 22px;"> <input type="radio" value="<% out.print(pedidos.get(i).getIdpedido()); %>" name="codigo" required></td>
                                    <td style="font-size: 22px;"> <% out.print(pedidos.get(i).getIdpedido()); %> </td>
                                    <td style="font-size: 22px;"> <% out.print(pedidos.get(i).getIdcliente().getNomecliente()+" "+pedidos.get(i).getIdcliente().getSobrenomecliente()); %> </td>
                                    <td style="font-size: 22px;"> <% out.print(df.format(pedidos.get(i).getDatapedido())); %> </td>
                                    <td style="font-size: 22px;"> <% out.print(f.format(pedidos.get(i).getValorpedido())); %> </td>                                       
                                        
                                    </tr>
                                    
                                    <%
                                }
                                    
                                %>
                                <tr>
                                    <td  colspan="5" style="font-size: 22px; text-align: right; padding-right: 27px;"><% out.print("Valor Total: "+f.format(valor)); %></td>
                                </tr>
                                </table>
                                <input class="botao" style="width: 250px" type="submit" value="Vizualizar Pedido Selecionado">
                                </form>
                                </center>

                                
                                <%
                                }
                                %>
                                
                        </div>
                    <% } else{ %>
                                <div><h1 style="color: white; font-size: 40pt; margin-left: 20px" >Ops!</h1></div>
                                <div id="sistema"> <a style="cursor: pointer; color: #000; font-weight: 900; font-size: 30pt "onclick ="voltarHome();">Acho que voc� n�o deveria estar aqui! </br> Clique em mim para voltar ao in�cio!</a></div>
                                
                                <% };%>        
                    </div>
                                
<script>
    function limpar(){
    <% pedidos = new ArrayList<Pedidos>(); %>
        window.location.href = "listaPedidos.jsp";
    };
</script>                                

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