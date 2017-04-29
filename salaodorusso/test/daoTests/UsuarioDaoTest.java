/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTests;

import dao.UsuarioDao;
import java.util.List;
import model.Usuario;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Marcos Vinícius
 */
public class UsuarioDaoTest
{

    private Usuario getUsuario()
    {
        Usuario u = new Usuario();
        u.setNome("Marcos");
        u.setSenha("81547686");
        u.setEmail("marcos8154@gmail.com");
        u.setCelular("999486444");
        u.setTelefone("33458585");

        return u;
    }

    @Test
    public void DEVE_SALVAR_UM_USUARIO()
    {
        Usuario usuario = getUsuario();
        UsuarioDao dao = new UsuarioDao();

        boolean result = dao.save(usuario);

        dao.remove(usuario);
        dao.commit(true);
        assertTrue(result);
    }

    @Test
    public void DEVE_BUSCAR_UM_USUARIO_PELA_ID()
    {
        Usuario usuario = getUsuario();
        UsuarioDao dao = new UsuarioDao();
        dao.save(usuario);
        dao.commit(false);

        usuario = dao.find(usuario.getId());
        dao.remove(usuario);
        dao.commit(true);

        boolean result = (usuario.getId() > 0);
        assertTrue(result);
    }

    @Test
    public void DEVE_REMOVER_UM_USUARIO()
    {
        Usuario usuario = getUsuario();
        UsuarioDao dao = new UsuarioDao();
        dao.save(usuario);
        dao.commit(false);

        boolean result = dao.remove(usuario);
        dao.commit(true);

        assertTrue(result);
    }

    @Test
    public void DEVE_LISTAR_TODOS_OS_USUARIOS()
    {
        Usuario usuario = getUsuario();
        UsuarioDao dao = new UsuarioDao();
        dao.save(usuario);
        dao.commit(false);
        
        List<Usuario> list = dao.listAll();
        
        dao.remove(usuario);
        dao.commit(true);
        
        boolean result = (list.size() > 0);
        assertTrue(result);
    }
}
