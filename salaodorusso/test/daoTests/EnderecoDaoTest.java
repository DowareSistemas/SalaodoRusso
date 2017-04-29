/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTests;

import dao.EnderecoDao;
import model.Endereco;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Marcos Vinícius
 */
public class EnderecoDaoTest
{

    private Endereco getEndereco()
    {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua vogue");
        endereco.setNumero(106);
        endereco.setBairro("Retiro");
        endereco.setMunicipio("Volta Redonda");
        endereco.setCep("27281-440");
        endereco.setComplemento("Perto da IE MG");

        return endereco;
    }

    @Test
    public void DEVE_SALVAR_UM_ENDERECO()
    {
        Endereco endereco = getEndereco();
        EnderecoDao dao = new EnderecoDao(false);
        boolean result =dao.save(endereco);
        dao.remove(endereco);
        dao.commit(true);
        
        assertTrue(result);
    }

    @Test
    public void DEVE_BUSCAR_UM_ENDERECO_PELA_ID()
    {
        Endereco endereco = getEndereco();
        EnderecoDao dao = new EnderecoDao();
        dao.save(endereco);
        dao.commit(false);
        
        endereco = dao.find(endereco.getId());
        dao.remove(endereco);
        dao.commit(true);
        
        boolean result = (endereco.getId() > 0);
        assertTrue(result);
    }
    
    @Test
    public void DEVE_REMOVER_UM_ENDERECO()
    {
        Endereco endereco = getEndereco();
        EnderecoDao dao = new EnderecoDao();
        dao.save(endereco);
        dao.commit(false);
        
        boolean result = dao.remove(endereco);
        dao.commit(true);
        
        assertTrue(result);
    }
}
