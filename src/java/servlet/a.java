/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DaoCategoria;
import dao.DaoPedido;
import dao.DaoProdutos;
import entidades.Categorias;
import entidades.Cliente;
import entidades.Pedidos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas
 */
@WebServlet(name = "ServletTeste", urlPatterns = {"/ServletTeste"})
public class a extends HttpServlet {

    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
DaoCategoria daoCategoria = new DaoCategoria();
        List<Categorias> categoria = new ArrayList<Categorias>();
                                
        try{
            categoria = daoCategoria.listarCategoria();
        }catch(SQLException a){
        }
        

        
        ArrayList codigo = new ArrayList();
        ArrayList desc = new ArrayList();        
        for(int i = 0; i < categoria.size(); i++){
            if(request.getParameter(categoria.get(i).getIdcategorias().toString()) != null && i < 2){
                
                try{
                    DaoProdutos daoProdutos = new DaoProdutos();
                    codigo.add(daoProdutos.retornaId(request.getParameter(categoria.get(i).getIdcategorias().toString())));
                    desc.add(request.getParameter(categoria.get(i).getIdcategorias().toString()));
                }catch(SQLException a){
                    
                }
                
            }
            else {
                try{
                    ArrayList produtos = new ArrayList();
                    DaoProdutos daoProdutos = new DaoProdutos();
                    produtos = daoProdutos.listarProdutosCategoria(categoria.get(i).getIdcategorias());
                    
                    
                    for(int a = 0; a < produtos.size(); a++){
                        if(request.getParameter(produtos.get(a).toString()) != null){
                            codigo.add(daoProdutos.retornaId(request.getParameter(produtos.get(a).toString())));
                            desc.add(request.getParameter(produtos.get(a).toString()));
                        }
                    }
                    
                    
                    
                }catch(SQLException a){
                    
                }
                    
            }            
        }
        
        
                
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */



            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletTeste</title>");
            out.println("<link rel=\"shortcut icon\" href=\"images/icone.png\" >");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\">");
            out.println("<linkrel=\"stylesheet\"type=\"text/css\"href=\"slick/slick.css\"/>");
            out.println("<linkrel=\"stylesheet\"type=\"text/css\"href=\"slick/slick-theme.css\"/>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<divid=\"topContainer\">");
            out.println("</div>");
            out.println("</header>");    

            
            out.println("<div id=\"quadro\">");     
            out.println("<h2><a href=\"#quadro\">Monte seu Lanche</a></h2>");
            out.println("<div id=\"sistema\"  style= \"height: 500px; \" >");
            out.println("<h1 style=\"font-size: 25px;\">Confirmação de Pedido</h1>");
            out.println("<ul>");
         
            
            out.println("<script>"
                    + "function cancela(){"
                    + "window.location.href=\"ServletPedido.java\";"
                    + "};"
                    + "</script>");                      
            DaoProdutos daoProdutos = new DaoProdutos();
            out.println("<form action=\"ServletFinalizaPedido\" method=\"GET\">");
            float valor = 0;
            out.println("<table><tr>");
            for(int i = 0; i < desc.size(); i++){
                try{
                    valor += daoProdutos.retornaValor(Integer.parseInt(codigo.get(i).toString()));
                }catch(Exception e){
                    
                }
                out.println("<td><input type=\"text\"  name = \""+codigo.get(i)+"\" readonly=\"true\" value=\""+desc.get(i)+"\" style=\"text-align: left; font-size: 25px; background-color: cornsilk; border: navajowhite;\"></td></tr>");
            }
            
            
            out.println("</table><p name=\"valor\" style=\"font-size: 25px;\">Valor Total: "+valor+"</p>");
            
            out.println("<input type=\"submit\" value=\"Confirma Pedido\" >");
            out.println("<input type=\"submit\" value=\"Cancela Pedido\">");
            out.println("</form>");
            
              
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
