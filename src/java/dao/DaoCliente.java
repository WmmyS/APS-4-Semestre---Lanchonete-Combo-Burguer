/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author lucas
 */
public class DaoCliente {
    
    private static EntityManagerFactory emf;
    public static EntityManager em;
    
    public void abreConexao(){
        emf = Persistence.createEntityManagerFactory("APSPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void fechaConexao(){
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    public void salvar(Cliente cliente){
        this.abreConexao();
        em.persist(cliente);
        this.fechaConexao();
    }


    
    
    
}
