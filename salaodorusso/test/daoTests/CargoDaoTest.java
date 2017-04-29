/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTests;

import dao.CargoDao;
import java.util.List;
import model.Cargo;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Marcos Vinícius
 */
public class CargoDaoTest
{

    private Cargo getCargo()
    {
        Cargo cargo = new Cargo();
        cargo.setDescricao("Descrição do cargo");

        return cargo;
    }
    
    @Test
    public void DEVE_SALVAR_UM_CARGO()
    {
        Cargo cargo = getCargo();
        CargoDao dao = new CargoDao(false);
        boolean result = dao.save(cargo);
        dao.remove(cargo);
        dao.commit(true);
        
        assertTrue(result);
    }
    
    @Test
    public void DEVE_BUSCAR_UM_CARGO_PELA_ID()
    {
        Cargo cargo = getCargo();
        CargoDao dao = new CargoDao(false);
        dao.save(cargo);
        dao.commit(false);
        
        cargo = dao.find(cargo.getId());
        boolean result = (cargo.getId() > 0);
        
        dao.remove(cargo);
        dao.commit(true);
        
        assertTrue(result);
    }
    
    @Test
    public void DEVE_EXCLUIR_UM_CARGO()
    {
        Cargo cargo = getCargo();
        CargoDao dao = new CargoDao(false);
        dao.save(cargo);
        dao.commit(false);
        
        boolean result = dao.remove(cargo);
        dao.commit(true);
        
        assertTrue(result);
    }
    
    @Test
    public void DEVE_PESQUISAR_CARGOS()
    {
        Cargo cargo = getCargo();
        CargoDao dao = new CargoDao(false);
        dao.save(cargo);
        dao.commit(false);
        
        List<Cargo> list = dao.search("ão");
        
        dao.remove(cargo);
        dao.commit(true);
        
        boolean result = (list.size() > 0 && list.get(0).getDescricao().contains("ção"));
        assertTrue(result);
    }
}
