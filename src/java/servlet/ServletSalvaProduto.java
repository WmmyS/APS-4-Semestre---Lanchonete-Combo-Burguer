/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DaoProdutos;
import entidades.Categorias;
import entidades.Produtos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
@WebServlet(name = "ServletSalvaProduto", urlPatterns = {"/ServletSalvaProduto"})
public class ServletSalvaProduto extends HttpServlet {

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
        
        Produtos produtos = new Produtos();
        Categorias categorias = new Categorias();
        DaoProdutos daoProdutos = new DaoProdutos();
        categorias.setIdcategorias(Integer.parseInt(request.getParameter("categoria")));
        produtos.setIdcategoria(categorias);
        produtos.setDescricao(request.getParameter("desc"));
        produtos.setValor(Float.parseFloat(String.valueOf(request.getParameter("valor").replaceAll(",", "."))));
        daoProdutos.salvar(produtos);

        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletSalvaProduto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletSalvaProduto at " + request.getParameter("categoria") + "</h1>");
            out.println("<h1>Servlet ServletSalvaProduto at " + request.getParameter("desc") + "</h1>");
            out.println("<h1>Servlet ServletSalvaProduto at " + request.getParameter("valor").replaceAll(",", ".") + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            response.sendRedirect("listaProdutos.jsp");
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
