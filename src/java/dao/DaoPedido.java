/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import entidades.Pedidos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lucas
 */
public class DaoPedido {
    private Connection conecta;
    private String sql;
    private PreparedStatement ps;      
    
    public DaoPedido(){
        this.conecta = new DaoUtil().conecta();
    }    
        
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dh = new SimpleDateFormat("HH:mm:ss");
        
    Date data = new Date();    
    
    public void salvar(Pedidos pedido){
        
        Date dataUtil = new Date();
        java.sql.Date dataSql = null;

        df = new SimpleDateFormat("dd/MM/yyyy");

        dataUtil = new java.sql.Date(dataUtil.getTime());
        dataSql = (java.sql.Date) dataUtil;
 
        String sql = "INSERT INTO pedidos(idCliente, DataPedido, ValorPedido) VALUES(?,?,?)";
                
        try{
            PreparedStatement ps = conecta.prepareStatement(sql);
            
            ps.setInt(1, pedido.getIdcliente().getIdcliente());
            ps.setDate(2, dataSql);
            ps.setDouble(3, pedido.getValorpedido());            
            
            ps.execute();
            
            ps.close();
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }       
    }
    
    public void salvarI(Pedidos pedido, ArrayList cod, ArrayList valor, int qtde){
        
        Date dataUtil = new Date();
        java.sql.Date dataSql = null;

        df = new SimpleDateFormat("dd/MM/yyyy");

        dataUtil = new java.sql.Date(dataUtil.getTime());
        dataSql = (java.sql.Date) dataUtil;
 
        String sql = "INSERT INTO pedidos(idCliente, DataPedido, ValorPedido) VALUES(?,?,?)";
                
        try{
            PreparedStatement ps = conecta.prepareStatement(sql);
            
            ps.setInt(1, pedido.getIdcliente().getIdcliente());
            ps.setDate(2, dataSql);
            ps.setDouble(3, qtde * pedido.getValorpedido());            
            
            ps.execute();
            
            ps.close();
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }    
        
        try{
            int codPedido = listarUltimoCod();    
            
            for(int i = 0; i < cod.size(); i++){
                
                String sqll = "INSERT INTO itenspedido(idproduto, idpedido, quantidade, valorunitario, valortotal) VALUES(?,?,?,?,?)";

                try{
                    PreparedStatement ps = conecta.prepareStatement(sqll);

                    ps.setInt(1, Integer.parseInt(cod.get(i).toString()));
                    ps.setInt(2, codPedido);
                    ps.setInt(3, qtde);   
                    ps.setFloat(4, Float.parseFloat(valor.get(i).toString()));
                    ps.setFloat(5, qtde * Float.parseFloat(valor.get(i).toString()));

                    ps.execute();

                    ps.close();

                }catch(Exception e){
                    throw new RuntimeException(e);
                }                
                
            }
        }catch(SQLException e){
            
        }
        
    }
    
    public List<Pedidos> buscapedidoIDcliente(int id) throws SQLException{
        
        String sql = "SELECT * FROM pedidos WHERE idCliente = ?";
        PreparedStatement ps = conecta.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();        
        
        List<Pedidos> pedidos = new ArrayList<Pedidos>();
        
        
        while(rs.next()){
            Pedidos p = new Pedidos();
            p.setIdpedido(rs.getInt("idpedido"));
            p.setDatapedido(rs.getDate("datapedido"));
            p.setValorpedido(rs.getDouble("valorpedido"));
            pedidos.add(p);
        }
            
        return pedidos;
    }
    
    public Pedidos buscapedidoIDpedido(int id) throws SQLException{
        
        String sql = "SELECT * FROM pedidos WHERE idpedido = ?";
        PreparedStatement ps = conecta.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();        
        
        
        Pedidos p = new Pedidos();
        
        if(rs.next()){
            
            p.setIdpedido(rs.getInt("idpedido"));
            p.setDatapedido(rs.getDate("datapedido"));
            p.setValorpedido(rs.getDouble("valorpedido"));
        }
            
        return p;
    }    
    
    public List<Pedidos> buscapedidoData() throws SQLException{
        

        
        String sql = "SELECT * FROM pedidos p INNER JOIN cliente c ON(p.idcliente = c.idcliente) ";
        PreparedStatement ps = conecta.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();        
        
        List<Pedidos> pedidos = new ArrayList<Pedidos>();
        
        
        while(rs.next()){
            Pedidos p = new Pedidos();
            Cliente cliente = new Cliente();
            cliente.setIdcliente(rs.getInt("idcliente"));
            cliente.setNomecliente(rs.getString("nomecliente"));
            cliente.setSobrenomecliente(rs.getString("sobrenomecliente"));
            p.setIdcliente(cliente);
            p.setIdpedido(rs.getInt("idpedido"));
            p.setDatapedido(rs.getDate("datapedido"));
            p.setValorpedido(rs.getDouble("valorpedido"));
            pedidos.add(p);
        }
            
        return pedidos;
    }   
    
    public List<Pedidos> buscapedidoData(String dataI, String dataF) throws SQLException{
        
        String sql = "SELECT * FROM pedidos p INNER JOIN cliente c ON(p.idcliente = c.idcliente) WHERE (datapedido BETWEEN ? AND ?)";
        PreparedStatement ps = conecta.prepareStatement(sql);
        ps.setDate(1, java.sql.Date.valueOf(dataI));
        ps.setDate(2, java.sql.Date.valueOf(dataF));
        
        
                
        System.out.println(ps);

        ResultSet rs = ps.executeQuery();        
        
        List<Pedidos> pedidos = new ArrayList<Pedidos>();
        
        
        while(rs.next()){
            Pedidos p = new Pedidos();
            Cliente cliente = new Cliente();
            cliente.setIdcliente(rs.getInt("idcliente"));
            cliente.setNomecliente(rs.getString("nomecliente"));
            cliente.setSobrenomecliente(rs.getString("sobrenomecliente"));
            p.setIdcliente(cliente);
            p.setIdpedido(rs.getInt("idpedido"));
            p.setDatapedido(rs.getDate("datapedido"));
            p.setValorpedido(rs.getDouble("valorpedido"));
            pedidos.add(p);
        }
            
        return pedidos;
    }       
    
   
    
    public ArrayList buscaDesc(int idPedido) throws SQLException{
        
        String sql = "SELECT * FROM itenspedido WHERE idpedido = ?";
        PreparedStatement ps = conecta.prepareStatement(sql);
        ps.setInt(1, idPedido);
        ResultSet rs = ps.executeQuery();  
        
        ArrayList descricao = new ArrayList();
        
        
        while(rs.next()){        
            descricao.add(rs.getInt("idproduto"));
        }
        return descricao;
    }
    
    public int buscaQtde(int idPedido) throws SQLException{
        
        String sql = "SELECT * FROM itenspedido WHERE idpedido = ?";
        PreparedStatement ps = conecta.prepareStatement(sql);
        ps.setInt(1, idPedido);
        ResultSet rs = ps.executeQuery();  
        
        int qtde = 0;
        
        
        if(rs.next()){        
            qtde = rs.getInt("quantidade");
        }
        return qtde;
    }    
    
    public int listarUltimoCod() throws SQLException {
        
        String sql = "select last_value as id from pedidos_idpedido_seq";
        PreparedStatement ps = conecta.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int codigo = 0;
        

        if(rs.next()){
            codigo = rs.getInt("id");    
        }
        return codigo;
    }           
    
    public void deletarUltimo() throws SQLException {
        
        String sql = "DELETE FROM pedidos WHERE idpedido = (select last_value as id from pedidos_idpedido_seq)";
        PreparedStatement ps = conecta.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

    }     
        
        
        
    
    
}

