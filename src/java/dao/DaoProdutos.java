/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Categorias;
import entidades.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class DaoProdutos {

         private Connection conecta;
    private String sql;
    private PreparedStatement ps;      
    
    public DaoProdutos(){
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(Produtos produtos){
        
        String sql = "INSERT INTO produtos(idcategoria, descricao, valor) VALUES(?,?,?)";
                
        try{
            PreparedStatement ps = conecta.prepareStatement(sql);
            
            ps.setInt(1, produtos.getIdcategoria().getIdcategorias());
            ps.setString(2, produtos.getDescricao());
            ps.setFloat(3, produtos.getValor());            
            
            ps.execute();
            
            ps.close();
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }       
    }    
    
    public void alterar(Produtos produtos){
        
        String sql = "UPDATE produtos set idcategoria = ?, descricao = ?, valor = ? WHERE idproduto = ?";
                
        try{
            PreparedStatement ps = conecta.prepareStatement(sql);
            
            ps.setInt(1, produtos.getIdcategoria().getIdcategorias());
            ps.setString(2, produtos.getDescricao());
            ps.setFloat(3, produtos.getValor());            
            ps.setInt(4, produtos.getIdproduto());  
            
            ps.execute();
            
            ps.close();
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }       
    }      
    
    public List<Produtos> listarProdutos() throws SQLException{
        
        String sql = "SELECT idcategoria, c.descricao as cdesc, p.descricao as pdesc, p.idproduto, p.valor FROM produtos p INNER JOIN categorias c ON (p.idcategoria = c.idcategorias)";
        ps = conecta.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        List<Produtos> produtos = new ArrayList<Produtos>();
               
        
        while (rs.next()) {
            Produtos p = new Produtos(); 
            Categorias c = new Categorias();
            c.setIdcategorias(rs.getInt("idcategoria"));
            c.setDescricao(rs.getString("cdesc"));
            p.setIdcategoria(c);
            p.setIdproduto(rs.getInt("idproduto"));
            p.setDescricao(rs.getString("pdesc"));
            p.setValor(rs.getFloat("valor"));
            produtos.add(p);
        }
        
       
        
        return produtos;
    }
    
    public Produtos buscaProduto(int cod) throws SQLException{
        
        String sql = "SELECT idcategoria, c.descricao as cdesc, p.descricao as pdesc, p.idproduto, p.valor FROM produtos p INNER JOIN categorias c ON (p.idcategoria = c.idcategorias) WHERE idproduto = ?";
        ps = conecta.prepareStatement(sql);
        ps.setInt(1, cod);
        ResultSet rs = ps.executeQuery();
        
        Produtos p = new Produtos();
               
        
        if (rs.next()) {
            Categorias c = new Categorias();
            c.setIdcategorias(rs.getInt("idcategoria"));
            c.setDescricao(rs.getString("cdesc"));
            p.setIdcategoria(c);
            p.setIdproduto(rs.getInt("idproduto"));
            p.setDescricao(rs.getString("pdesc"));
            p.setValor(rs.getFloat("valor"));
        }
        
       
        
        return p;
    }    
    
    public ArrayList listarProdutosCategoria(int idCat) throws SQLException{
        
        String sql = "SELECT * FROM produtos WHERE idcategoria = ?";
        ps = conecta.prepareStatement(sql);
        ps.setInt(1, idCat);
        ResultSet rs = ps.executeQuery();
        
        ArrayList produtos = new ArrayList();
               
        
        while (rs.next()) {
            produtos.add(rs.getString("descricao"));
        }    
       
        
        return produtos;
    }    
    
    public int retornaId(String desc) throws SQLException{
        String sql = "SELECT idproduto FROM produtos WHERE descricao = ?";
        ps = conecta.prepareStatement(sql);
        ps.setString(1, desc);
        ResultSet rs = ps.executeQuery();
        
        System.out.println(ps);
        
        int id = 0;
        
        if(rs.next()){
            id = rs.getInt("idproduto");
        }
        return id;
    }
    
    public float retornaValor(int cod) throws SQLException{
        String sql = "SELECT valor FROM produtos WHERE idproduto = ?";
        ps = conecta.prepareStatement(sql);
        ps.setInt(1, cod);
        ResultSet rs = ps.executeQuery();
        
        System.out.println(ps);
        
        float valor = 0;
        
        if(rs.next()){
            valor = rs.getFloat("valor");
        }
        return valor;
    }    
    
  public String retornaDesc(int cod) throws SQLException{
        String sql = "SELECT descricao FROM produtos WHERE idproduto = ?";
        ps = conecta.prepareStatement(sql);
        ps.setInt(1, cod);
        ResultSet rs = ps.executeQuery();
        
        System.out.println(ps);
        
        String desc = "";
        
        if(rs.next()){
            desc = rs.getString("descricao");
        }
        return desc;
    }        
}
