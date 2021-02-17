package com.algaworks.ecommerce.iniciandocomJPA;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTeste extends EntityManagerTest {

    @Test
    public void impedirOpracaoComBancoDeDados(){

        Produto produto = entityManager.find(Produto.class, 1);
        entityManager.detach(produto); ///////// Impede a conexão com o banco

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2° Geração");
        produto.setDescricao("Muito bom!");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle",produtoVerificacao.getNome());
        System.out.println(">>>>>>>>>>>>>>>>"+ produto.getNome());
    }

    @Test
    public void inserirObjetoComMerge(){
        Produto produto = new Produto();
        produto.setId(4);
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();

        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        // System.out.println(produto.getId());
    }


    @Test
    public void atualizarObjetoGerenciado(){

        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2° Geração");
        produto.setDescricao("Muito bom!");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle Paperwhite 2° Geração",produtoVerificacao.getNome());
        System.out.println(">>>>>>>>>>>>>>>>"+ produto.getNome());
    }

    @Test
    public void atualizarObjeto(){

        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Kindle PaperWhite");
        produto.setDescricao("Conheça o novo Kindle");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        Assert.assertEquals("Kindle PaperWhite",produtoVerificacao.getNome());
        System.out.println(">>>>>>>>>>>>>>>>"+ produto.getNome());
    }



    @Test
    public void removerObjeto(){

        Produto produto = entityManager.find(Produto.class, 3);
       // produto.setId(3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void inserirOPrimeiroObjeto(){


        Produto produto = new Produto();
        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();

        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
       // System.out.println(produto.getId());
    }


    @Test
    public void abrirEFecharATransacao(){

        Produto produto = new Produto();

        entityManager.getTransaction().begin();

        entityManager.persist(produto);
        entityManager.merge(produto);
        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
}
