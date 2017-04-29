/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTests;

import dao.DicaDao;
import java.util.List;
import model.Dica;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Marcos Vinícius
 */
public class DicaDaoTest
{

    @Test
    public void DEVE_SALVAR_DICA()
    {
        Dica dica = getDica();
        DicaDao dao = new DicaDao(false);
        
        boolean result = dao.save(dica);
        dao.commit(true);
        
        dao.remove(dica);
        dao.commit(true);
        
        assertTrue(result);
    }

    @Test
    public void DEVE_BUSCAR_UMA_DICA_POR_ID()
    {
        Dica dica = getDica();
        DicaDao dao = new DicaDao(false);
        
        dao.save(dica);
        dao.commit(true);
        
        boolean result = dao.find(dica.getId()).getId() != 0;
        
        dao.remove(dica);
        dao.commit(true);
        
        assertTrue(result);
    }
    
    @Test
    public void DEVE_REMOVER_UMA_DICA()
    {
        Dica dica = getDica();
        DicaDao dao = new DicaDao(false);
        
        dao.save(dica);
        dao.commit(false);
        
        boolean result = dao.remove(dica);
        dao.commit(true);
        
        assertTrue(result);
    }
    
    @Test
    public void DEVE_PESQUISAR_DICA()
    {
        Dica dica = getDica();
        DicaDao dao = new DicaDao(false);
        
        dao.save(dica);
        dao.commit(false);
        
        List<Dica> dicas = dao.search("tulo", 0, 15);
        
        dao.remove(dica);
        dao.commit(true);
        
        boolean result = (dicas.size() > 0 && dicas.get(0).getDescricao().startsWith("Desc"));
        
        assertTrue(result);
    }
    
    private Dica getDica()
    {
        Dica dica = new Dica();
        dica.setDescricao("Descricao da dica");
        dica.setFoto("arquivo da foto da dica");
        dica.setTitulo("Titulo da dica");

        return dica;
    }

}
