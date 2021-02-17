package com.algaworks.ecommerce.iniciandocomJPA;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void inserirRegistro(){

        Cliente cliente = new Cliente();
        cliente.setId(2);
        cliente.setNome("Osvaldo");
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();


        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }
    @Test
    public void BuscarRegistro(){
        Cliente cliente = entityManager.find(Cliente.class, 2);

        Assert.assertNotNull(cliente);
        Assert.assertEquals("Gustavo", cliente.getNome());
        System.out.println(">>>>>>>>>>>>>>>"+ cliente.getNome());
    }
    @Test
    public void atualizarRegistro(){
        Cliente cliente = new Cliente();
        cliente.setNome("Gustavo");
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }
    @Test
    public void DeleteRegistro(){

        Cliente cliente = new Cliente();
        cliente.setId(2);
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNull(clienteVerificacao);
    }
}
