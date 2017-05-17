/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.com.persistor.abstractClasses.Entity;
import br.com.persistor.annotations.OneToMany;
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
public class Expediente extends Entity implements Serializable
{

    private int id;
    private String titulo;
    private String segunda;
    private String terca;
    private String quarta;
    private String quinta;
    private String sexta;
    private String sabado;

    private Funcionario funcionario;

    @OneToMany(source = "id", target = "expediente_id", join_type = JOIN_TYPE.LEFT, load = LOAD.MANUAL)
    public Funcionario getFuncionario()
    {
        if (funcionario == null)
            funcionario = (Funcionario) FieldHandled.readObject(this, "funcionario");
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario)
    {
        this.funcionario = funcionario;
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

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getSegunda()
    {
        return segunda;
    }

    public void setSegunda(String segunda)
    {
        this.segunda = segunda;
    }

    public String getTerca()
    {
        return terca;
    }

    public void setTerca(String terca)
    {
        this.terca = terca;
    }

    public String getQuarta()
    {
        return quarta;
    }

    public void setQuarta(String quarta)
    {
        this.quarta = quarta;
    }

    public String getQuinta()
    {
        return quinta;
    }

    public void setQuinta(String quinta)
    {
        this.quinta = quinta;
    }

    public String getSexta()
    {
        return sexta;
    }

    public void setSexta(String sexta)
    {
        this.sexta = sexta;
    }

    public String getSabado()
    {
        return sabado;
    }

    public void setSabado(String sabado)
    {
        this.sabado = sabado;
    }

}
