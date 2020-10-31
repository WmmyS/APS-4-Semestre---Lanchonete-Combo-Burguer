/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DaoClientee;
import dao.DaoClienteee;
import entidades.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

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
        
        Cliente cliente = new Cliente();
        try{            
            DaoClienteee daoClienteee = new DaoClienteee();
            if(request.getParameter("credLogin").equals("admin") && request.getParameter("credSenha").equals("123")){
                HttpSession session = request.getSession(true);
                session.setAttribute("name","Admin");  
                session.setAttribute("id","0");                 
                session.setAttribute("permissao","1");                  
            }
            else{
                cliente = daoClienteee.buscarUsario(request.getParameter("credLogin"), request.getParameter("credSenha"));
                if(cliente.getNomecliente() != null){
                    HttpSession session = request.getSession(true);  
                    session.setAttribute("name",cliente.getNomecliente());  
                    session.setAttribute("id",cliente.getIdcliente());                 
                    session.setAttribute("permissao","0");                
                }
            }

        }catch(SQLException a){
            
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");  
            out.println("<link rel=\"shortcut icon\" href=\"images/icone.png\" >");
            out.println("</head>");
            out.println("<body>");
            if(cliente.getNomecliente() == null && !request.getParameter("credLogin").equals("Admin") && !request.getParameter("credSenha").equals("123")){
                out.println("<script> alert('Usuário ou senha incorretos!'); ");
                out.println("window.location.href = \"index.jsp\"; </script>");
            }
            else{
                 out.println("<script> window.location.href = \"index.jsp\"; </script>");
            }
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
