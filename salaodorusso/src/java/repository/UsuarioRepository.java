/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import br.com.persistor.enums.RESULT_TYPE;
import interfaces.IUsuario;
import java.util.List;
import model.Usuario;

/**
 *
 * @author Marcos Vinícius
 */
public class UsuarioRepository extends RepositoryImpl<Usuario> implements IUsuario
{

    @Override
    public List<Usuario> listAll()
    {
        Usuario usuarios = new Usuario();
        createCriteria(usuarios, RESULT_TYPE.MULTIPLE)
                .execute();
        return usuarios.toList();
    }

    @Override
    public Usuario efetuaLogin(String email, String senha)
    {
        Usuario usuario = new Usuario();
        createQuery(usuario, "@loginUsuario")
                .setResult_type(RESULT_TYPE.UNIQUE)
                .setParameter(1, email)
                .setParameter(2, senha)
                .execute();
        return (usuario.getId() == 0
                ? null
                : usuario);
    }
}
