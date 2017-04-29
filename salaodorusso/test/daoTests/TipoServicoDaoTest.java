/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTests;

import dao.TipoServicoDao;
import java.util.List;
import model.TipoServico;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Marcos Vinícius
 */
public class TipoServicoDaoTest
{

    private TipoServico getTipoServico()
    {
        TipoServico ts = new TipoServico();
        ts.setDescricao("Descrição do tipo de servico");
        
        return ts;
    }
    
    @Test
    public void DEVE_SALVAR_UM_TIPO_SERVICO()
    {
        TipoServico tipoServico = getTipoServico();
        TipoServicoDao dao = new TipoServicoDao(false);
        
        boolean result =dao.save(tipoServico);
        
        dao.remove(tipoServico);
        dao.commit(true);
        
        assertTrue(result);
    }
    
    @Test
    public void DEVE_BUSCAR_UM_TS_PELA_ID()
    {
        TipoServico servico = getTipoServico();
        TipoServicoDao dao = new TipoServicoDao(false);
        dao.save(servico);
        dao.commit(false);
        
        servico = dao.find(servico.getId());
        dao.remove(servico);
        dao.commit(true);
        
        boolean result = (servico.getId() > 0);
        assertTrue(result);
    }
    
    @Test
    public void DEVE_REMOVER_UM_TIPO_SERVICO()
    {
        TipoServico servico = getTipoServico();
        TipoServicoDao dao = new TipoServicoDao(false);
        dao.save(servico);
        dao.commit(false);
        
        boolean result = dao.remove(servico);
        dao.commit(true);
        
        assertTrue(result);
    }
    
    @Test
    public void DEVE_PESQUISAR_TIPOS_SERVICOS()
    {
        TipoServico servico = getTipoServico();
        TipoServicoDao dao = new TipoServicoDao(false);
        dao.save(servico);
        
        List<TipoServico> list = dao.search("ão");
        
        dao.remove(servico);
        dao.commit(true);
        
        boolean result = (list.size() > 0 && list.get(0).getDescricao().contains("ão"));
        assertTrue(result);
    }

}
