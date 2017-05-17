/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import br.com.persistor.enums.FILTER_TYPE;
import br.com.persistor.enums.MATCH_MODE;
import br.com.persistor.enums.RESULT_TYPE;
import br.com.persistor.generalClasses.Limit;
import br.com.persistor.generalClasses.Restrictions;
import interfaces.IProduto;
import java.util.List;
import model.Produto;

/**
 *
 * @author Marcos Vinícius
 */
public class ProdutoRepository extends RepositoryImpl<Produto> implements IProduto
{
    @Override
    public List<Produto> search(String search)
    {
        Produto produtos = new Produto();
        createCriteria(produtos, RESULT_TYPE.MULTIPLE)
                .beginPrecedence()
                .add(Restrictions.like(FILTER_TYPE.WHERE, "descricao", search, MATCH_MODE.ANYWHERE))
                .add(Restrictions.like(FILTER_TYPE.OR, "nome", search, MATCH_MODE.ANYWHERE))
                .endPrecedence()
                .execute();
        return produtos.toList();
    }

    @Override
    public List<Produto> top8()
    {
        Produto produtos = new Produto();
        createCriteria(produtos, RESULT_TYPE.MULTIPLE)
                .addLimit(Limit.simpleLimit(8))
                .execute();
        return produtos.toList();
    }
}
