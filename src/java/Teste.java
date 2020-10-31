
import dao.DaoCategoria;
import dao.DaoCliente;
import dao.DaoClientee;
import dao.DaoClienteee;
import dao.DaoPedido;
import dao.DaoProdutos;
import entidades.Cliente;
import entidades.Pedidos;
import entidades.Produtos;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucas
 */
public class Teste {
    public static void main(String[] args) {

     
        Cliente cliente = new Cliente();
        cliente.setNomecliente("Lucas");
        cliente.setCpf("564654");
        cliente.setTelefone("3241564564");
        cliente.setSobrenomecliente("Silva");
        cliente.setDatanascimento(java.sql.Date.valueOf("2020-10-26"));
        cliente.setTelefone2("54654654");
        cliente.setEstado("SP");
        cliente.setBairro("Centro");
        cliente.setNumero("54");
        cliente.setCidade("São Paulo");
        cliente.setRua("Rua 11");
        cliente.setComplemento("");
        cliente.setEmail("lucassilva@gmail.com");
        cliente.setNomeusuario("lucassilva");
        cliente.setIdcliente(8);
        DaoClienteee daoCliente = new DaoClienteee();
        try{
            daoCliente.altera(cliente);    
        }catch(SQLException e){
            System.out.println(e);
        }       
    }
}
