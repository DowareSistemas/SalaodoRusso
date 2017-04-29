/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTests;

import dao.ProdutoDao;
import java.util.List;
import model.Produto;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Marcos Vinícius
 */
public class ProdutoDaoTest
{

    private Produto getProduto()
    {
        Produto produto = new Produto();
        produto.setDescricao("Pretos resistences e com brilho intenso");
        produto.setNome("Shamppoo Pamolive Pretos Brilhantes");
        produto.setFoto("");
        produto.setValor(12.99);

        return produto;
    }

    @Test
    public void DEVE_SALVAR_UM_PRODUTO()
    {
        Produto produto = getProduto();
        ProdutoDao dao = new ProdutoDao();

        boolean result = dao.save(produto);
        dao.remove(produto);
        dao.commit(true);

        assertTrue(result);
    }

    @Test
    public void DEVE_BUSAR_UM_PRODUTO_PELA_ID()
    {
        Produto produto = getProduto();
        ProdutoDao dao = new ProdutoDao(false);
        dao.save(produto);
        dao.commit(false);

        produto = dao.find(produto.getId());

        dao.remove(produto);
        dao.commit(true);

        boolean result = (produto.getId() > 0);
        assertTrue(result);
    }
    
    @Test
    public void DEVE_REMOVER_UM_PRODUTO()
    {
        Produto produto = getProduto();
        ProdutoDao dao = new ProdutoDao(false);
        dao.save(produto);
        dao.commit(false);
        
        boolean result = dao.remove(produto);
        dao.commit(true);
        
        assertTrue(result);
    }
    
    @Test
    public void DEVE_PESQUISAR_PRODUTOS()
    {
        Produto produto = getProduto();
        ProdutoDao dao = new ProdutoDao(false);
        dao.save(produto);
        dao.commit(false);
        
        List<Produto> list = dao.search("pamolive");
        
        dao.remove(produto);
        dao.commit(true);
        
        boolean result = (list.size() > 0 && list.get(0).getNome().contains("amolive"));
        assertTrue(result);
    }
}
