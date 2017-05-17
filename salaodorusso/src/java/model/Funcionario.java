/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.com.persistor.abstractClasses.Entity;
import br.com.persistor.annotations.OneToMany;
import br.com.persistor.annotations.OneToOne;
import br.com.persistor.annotations.PrimaryKey;
import br.com.persistor.enums.INCREMENT;
import br.com.persistor.enums.JOIN_TYPE;
import br.com.persistor.enums.LOAD;
import br.com.persistor.sessionManager.FieldHandled;
import java.io.Serializable;

/**
 *
 * @author Marcos Vinícius
 */
public class Funcionario extends Entity implements Serializable
{

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String celular;
    private int cargo_id;
    private int endereco_id;
    private int expediente_id;

    private Cargo cargo;
    private Endereco endereco;
    private FuncionarioServicos funcionarioServicos;
    private Expediente expediente;

    @OneToOne(source = "expediente_id", target = "id", join_type = JOIN_TYPE.INNER, load = LOAD.AUTO)
    public Expediente getExpediente()
    {
        return expediente;
    }

    public void setExpediente(Expediente expediente)
    {
        this.expediente = expediente;
    }

    @OneToMany(source = "id", target = "funcionario_id", join_type = JOIN_TYPE.LEFT, load = LOAD.MANUAL)
    public FuncionarioServicos getFuncionarioServicos()
    {
        if(funcionarioServicos == null)
            funcionarioServicos = (FuncionarioServicos)FieldHandled.readObject(this, "funcionarioServicos");
        return funcionarioServicos;
    }

    public void setFuncionarioServicos(FuncionarioServicos funcionarioServicos)
    {
        this.funcionarioServicos = funcionarioServicos;
    }

    @OneToOne(source = "endereco_id", target = "id", join_type = JOIN_TYPE.LEFT, load = LOAD.AUTO)
    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    @OneToOne(source = "cargo_id", target = "id", join_type = JOIN_TYPE.INNER, load = LOAD.AUTO)
    public Cargo getCargo()
    {
        return cargo;
    }

    public void setCargo(Cargo cargo)
    {
        this.cargo = cargo;
    }

    public int getExpediente_id()
    {
        return expediente_id;
    }

    public void setExpediente_id(int expediente_id)
    {
        this.expediente_id = expediente_id;
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

    public int getEndereco_id()
    {
        return endereco_id;
    }

    public void setEndereco_id(int endereco_id)
    {
        this.endereco_id = endereco_id;
    }

    public int getCargo_id()
    {
        return cargo_id;
    }

    public void setCargo_id(int cargo_id)
    {
        this.cargo_id = cargo_id;
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

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getCelular()
    {
        return celular;
    }

    public void setCelular(String celular)
    {
        this.celular = celular;
    }

}
