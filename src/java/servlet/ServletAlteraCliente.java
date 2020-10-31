/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DaoCliente;
import dao.DaoClienteee;
import entidades.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "ServletAlteraCliente", urlPatterns = {"/ServletAlteraCliente"})
public class ServletAlteraCliente extends HttpServlet {

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
        HttpSession session = request.getSession(true);  
        cliente.setIdcliente(Integer.parseInt(session.getAttribute("id").toString()));
        cliente.setNomecliente(request.getParameter("nomeTxt"));
        cliente.setCpf(request.getParameter("cpfTxt"));
        cliente.setTelefone(request.getParameter("telefone1Txt"));
        cliente.setSobrenomecliente(request.getParameter("sobrenomeTxt"));
        cliente.setDatanascimento(Date.valueOf(request.getParameter("dataNascimentoTxt")));
        cliente.setTelefone2(request.getParameter("telefone2Txt"));
        cliente.setEstado(request.getParameter("estadoTxt"));
        cliente.setBairro(request.getParameter("bairroTxt"));
        cliente.setNumero(request.getParameter("numeroTxt"));
        cliente.setCidade(request.getParameter("cidadeTxt"));
        cliente.setRua(request.getParameter("ruaTxt"));
        cliente.setComplemento(request.getParameter("complementoTxt"));
        cliente.setEmail(request.getParameter("emailTxt"));
        //cliente.set(request.getParameter("emailTxt"));
        cliente.setNomeusuario(request.getParameter("nomeUsuarioTxt"));

        DaoClienteee daoCliente = new DaoClienteee();
        try{
            daoCliente.altera(cliente);    
        }catch(SQLException e){
            
        }
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            session.setAttribute("name", cliente.getNomecliente());
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                        out.println("<script> alert('Cadastro alterado com sucesso!');"
                    + "window.location.href=\"index.jsp\"; "
                    + "</script>");
            out.println("<head>");
            out.println("<title>Servlet ServletCliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cliente salvo com sucesso</h1>");

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
