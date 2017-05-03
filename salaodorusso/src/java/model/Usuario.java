/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.com.persistor.abstractClasses.Entity;
import br.com.persistor.annotations.NamedQuery;
import br.com.persistor.annotations.OneToOne;
import br.com.persistor.annotations.PrimaryKey;
import br.com.persistor.enums.INCREMENT;
import br.com.persistor.enums.JOIN_TYPE;
import br.com.persistor.enums.LOAD;
import br.com.persistor.sessionManager.FieldHandled;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Marcos Vinícius
 */
@NamedQuery(queryName = "loginUsuario",
        queryValue = "select * from usuario where email = ? and senha = ?")
public class Usuario extends Entity implements Serializable
{

    private int id;

    @NotNull(message = "O nome é obrigatório")
    @NotEmpty(message = "O nome é obrigatório")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O email é obrigatório")
    @NotEmpty(message = "O email é obrigatório")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotNull(message = "A senha é obrigatória")
    @NotEmpty(message = "A senha é obrigatória")
    @NotBlank(message = "A senha é obrigatória")
    private String senha;
    private String telefone;
    private String celular;
    private int endereco_id;

    private Endereco endereco;

    @OneToOne(source = "endereco_id", target = "id", join_type = JOIN_TYPE.LEFT, load = LOAD.AUTO)
    public Endereco getEndereco()
    {
        if (endereco == null)
            endereco = (Endereco) FieldHandled.readObject(this, "endereco");
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public int getEndereco_id()
    {
        return endereco_id;
    }

    public void setEndereco_id(int endereco_id)
    {
        this.endereco_id = endereco_id;
    }

    @PrimaryKey(increment = INCREMENT.AUTO)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCelular()
    {
        return celular;
    }

    public void setCelular(String celular)
    {
        this.celular = celular;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

}
