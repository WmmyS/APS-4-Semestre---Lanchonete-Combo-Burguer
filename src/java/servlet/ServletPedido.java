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
import entidades.Produtos;
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
@WebServlet(name = "ServletPedido", urlPatterns = {"/ServletPedido"})
public class ServletPedido extends HttpServlet {

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
        
       
        
        ArrayList teste = new ArrayList();
        
        for(int i = 0; i < categoria.size(); i++){
            if(request.getParameter(categoria.get(i).getIdcategorias().toString()) != null && i < 2){
                
                try{
                    DaoProdutos daoProdutos = new DaoProdutos();
                    teste.add(daoProdutos.retornaId(request.getParameter(categoria.get(i).getIdcategorias().toString())));
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
                            teste.add(daoProdutos.retornaId(request.getParameter(produtos.get(a).toString())));
                        }
                    }
                    
                    
                    
                }catch(SQLException a){
                    
                }
                    
            }            
        }
        
            
                Pedidos p = new Pedidos();
                Cliente c = new Cliente();
                HttpSession session=request.getSession(true); 
                c.setIdcliente(Integer.parseInt(session.getAttribute("id").toString()));
                
                p.setIdcliente(c);
                p.setValorpedido(0.0);
                



                 
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletPedido</title>");            
            out.println("</head>");
            out.println("<body>");

                   

            
            //out.println("<h1>"+daoPedido.salvar(p)+"</h1>");    
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
