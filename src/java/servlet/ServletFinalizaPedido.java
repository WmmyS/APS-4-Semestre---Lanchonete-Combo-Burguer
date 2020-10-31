/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DaoPedido;
import dao.DaoProdutos;
import entidades.Cliente;
import entidades.Pedidos;
import entidades.Produtos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
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
@WebServlet(name = "ServletFinalizaPedido", urlPatterns = {"/ServletFinalizaPedido"})
public class ServletFinalizaPedido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * 
     */
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        DaoProdutos daoProdutos = new DaoProdutos();
        List<Produtos> produtos = new ArrayList<Produtos>();
        ArrayList produtosUsados = new ArrayList();
        ArrayList precos = new ArrayList();
        
        try{
            produtos = daoProdutos.listarProdutos();
            for(int i = 0; i < produtos.size(); i++){
                if(request.getParameter(String.valueOf(produtos.get(i).getIdproduto())) != null){
                    produtosUsados.add(String.valueOf(produtos.get(i).getIdproduto()));
                    precos.add(String.valueOf(produtos.get(i).getValor()));
                }
            }
            


        }catch(SQLException e){
        }
  
        Pedidos p = new Pedidos();
        Cliente c = new Cliente();
        HttpSession session=request.getSession(true);
        c.setIdcliente(Integer.parseInt(session.getAttribute("id").toString()));     
        
        p.setIdcliente(c);
        p.setValorpedido(Double.parseDouble(request.getParameter("valor").substring(12)));
        DaoPedido daoPedido = new DaoPedido();
        daoPedido.salvarI(p, produtosUsados, precos, Integer.parseInt(request.getParameter("qtde").toString()));  
        
      
       
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Finaliza Pedido</title>");   
            out.println("<link rel=\"shortcut icon\" href=\"images/icone.png\" >");
            out.println("</head>");
            out.println("<body>");
            //HttpSession session=request.getSession(true); 
            out.println("<script> alert('Pedido Finalizado com Sucesso!'); ");
            out.println("window.location.href = \"meusPedidos.jsp\"; </script>");

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
