/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entidades.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class DaoCategoria {
     private Connection conecta;
    private String sql;
    private PreparedStatement ps;      
    
    public DaoCategoria(){
        this.conecta = new DaoUtil().conecta();
    }
    
    public List<Categorias> listarCategoria() throws SQLException{
        
        String sql = "SELECT * FROM categorias";
        ps = conecta.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        List<Categorias> categorias = new ArrayList<Categorias>();
               
        
        while (rs.next()) {
            Categorias c = new Categorias();
            c.setIdcategorias(rs.getInt("idcategorias"));
            c.setDescricao(rs.getString("descricao"));  
            categorias.add(c);
        }
        return categorias;
    }
}
