/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import java.util.List;

/**
 *
 * @author lucas
 */
public class DaoClientee extends DaoCliente{
    
    public List<Cliente> listar(){
        this.abreConexao();
        List<Cliente> cliente;
        cliente = em.createQuery("SELECT c FROM Cliente c").getResultList();
        this.fechaConexao();
        return  cliente;
    }
    

    
}
