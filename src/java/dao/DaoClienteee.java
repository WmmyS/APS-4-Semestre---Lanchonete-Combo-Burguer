/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class DaoClienteee {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    
    private Connection conecta;
    private String sql;
    private PreparedStatement ps;      
    
    public DaoClienteee(){
        this.conecta = new DaoUtil().conecta();
    }
    
    public Cliente buscarUsario(String user, String senha) throws SQLException{
        
        String sql = "SELECT * FROM cliente WHERE nomeusuario = ? AND  senha = ?";
        ps = conecta.prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, senha);
        ResultSet rs = ps.executeQuery();
        
        Cliente cliente = new Cliente();
        if (rs.next()) {
            
            cliente.setNomecliente(rs.getString("nomecliente"));
            cliente.setIdcliente(rs.getInt("idcliente"));
            
        }
    
        
        return cliente;
    }
    
    public void salvar(Cliente c) throws SQLException{
        
        String sql = "INSERT INTO cliente(nomecliente, sobrenomecliente, cpf, rua, numero, complemento, bairro, cidade, nomeusuario, senha, datanascimento, telefone, telefone2, estado, email) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
        PreparedStatement ps = conecta.prepareStatement(sql);
        ps.setString(1, c.getNomecliente());
        ps.setString(2, c.getSobrenomecliente());
        ps.setString(3, c.getCpf());
        ps.setString(4, c.getRua());
        ps.setString(5, c.getNumero());
        ps.setString(6, c.getComplemento());
        ps.setString(7, c.getBairro());
        ps.setString(8, c.getCidade());
        ps.setString(9, c.getNomeusuario());
        ps.setString(10, c.getSenha());
        ps.setDate(11, (java.sql.Date) c.getDatanascimento());
        ps.setString(12, c.getTelefone());
        ps.setString(13, c.getTelefone2());
        ps.setString(14, c.getEstado());
        ps.setString(15, c.getEmail());
        
        ResultSet rs = ps.executeQuery();
        
    }
    
    public void altera(Cliente c) throws SQLException{
        
        String sql = "UPDATE cliente set nomecliente = ?, sobrenomecliente = ?, cpf = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, nomeusuario = ?, datanascimento = ?, telefone = ?, telefone2 = ?, estado = ?, email = ? WHERE idcliente = ?";
                
        PreparedStatement ps = conecta.prepareStatement(sql);
        ps.setString(1, c.getNomecliente());
        ps.setString(2, c.getSobrenomecliente());
        ps.setString(3, c.getCpf());
        ps.setString(4, c.getRua());
        ps.setString(5, c.getNumero());
        ps.setString(6, c.getComplemento());
        ps.setString(7, c.getBairro());
        ps.setString(8, c.getCidade());
        ps.setString(9, c.getNomeusuario());
        ps.setDate(10, (java.sql.Date) c.getDatanascimento());
        ps.setString(11, c.getTelefone());
        ps.setString(12, c.getTelefone2());
        ps.setString(13, c.getEstado());
        ps.setString(14, c.getEmail());
        ps.setInt(15, c.getIdcliente());
        
        ResultSet rs = ps.executeQuery();
        
    }    
    
    public List<Cliente> listar() throws SQLException{
        
        String sql = "SELECT * FROM cliente";
        ps = conecta.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        List<Cliente> cliente = new ArrayList<Cliente>();
        
        while(rs.next()){
            Cliente c = new Cliente();
            
            c.setIdcliente(rs.getInt("idcliente"));
            c.setNomecliente(rs.getString("nomecliente"));
            c.setSobrenomecliente(rs.getString("sobrenomecliente"));
            c.setCpf(rs.getString("cpf"));
            c.setRua(rs.getString("rua"));
            c.setNumero(rs.getString("numero"));
            c.setComplemento(rs.getString("complemento"));
            c.setBairro(rs.getString("bairro"));
            c.setCidade(rs.getString("cidade"));
            c.setNomeusuario(rs.getString("nomeusuario"));
            c.setSenha(rs.getString("senha"));
            c.setDatanascimento(rs.getDate("datanascimento"));
            c.setTelefone(rs.getString("telefone"));
            c.setTelefone2(rs.getString("telefone2"));
            c.setEstado(rs.getString("estado"));
            c.setEmail(rs.getString("email"));
            
            cliente.add(c);
        }
        
        return  cliente;
    }

    public Cliente retornaCliente(int id) throws SQLException{
        
        String sql = "SELECT * FROM cliente WHERE idcliente = ?";
        ps = conecta.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        Cliente c = new Cliente();
        
        if(rs.next()){
            
            
            c.setIdcliente(rs.getInt("idcliente"));
            c.setNomecliente(rs.getString("nomecliente"));
            c.setSobrenomecliente(rs.getString("sobrenomecliente"));
            c.setCpf(rs.getString("cpf"));
            c.setRua(rs.getString("rua"));
            c.setNumero(rs.getString("numero"));
            c.setComplemento(rs.getString("complemento"));
            c.setBairro(rs.getString("bairro"));
            c.setCidade(rs.getString("cidade"));
            c.setNomeusuario(rs.getString("nomeusuario"));
            c.setSenha(rs.getString("senha"));
            c.setDatanascimento(rs.getDate("datanascimento"));
            c.setTelefone(rs.getString("telefone"));
            c.setTelefone2(rs.getString("telefone2"));
            c.setEstado(rs.getString("estado"));
            c.setEmail(rs.getString("email"));
        }
        
        return c;
    }    
}

