/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.IProduto;
import java.util.List;
import model.Produto;
import repository.ProdutoRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class ProdutoDao
{
    private IProduto db = null;
    
    public ProdutoDao(boolean... autoCommit)
    {
        db = new ProdutoRepository();
        if(autoCommit.length > 0)
            db.setAutoCommitOrClose(autoCommit[0]);
    }
    
    public boolean save(Produto produto)
    {
        if(db.exists(Produto.class, produto.getId()))
            db.update(produto);
        else
            db.save(produto);
        
        return (produto.saved || produto.updated);
    }
    
    public Produto find(int id)
    {
        return db.find(Produto.class, id);
    }
    
    public boolean remove(Produto produto)
    {
        db.remove(produto);
        return produto.deleted;
    }
    
    public List<Produto> search(String search)
    {
        return db.search(search);
    }

    public void commit(boolean close)
    {
        db.commit(close);
    }

    public List<Produto> top8()
    {
        return db.top8();
    }
}
