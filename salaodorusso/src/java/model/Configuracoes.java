/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.com.persistor.abstractClasses.Entity;
import br.com.persistor.annotations.NamedQuery;
import br.com.persistor.annotations.PrimaryKey;
import java.io.Serializable;

/**
 *
 * @author Marcos Vinícius
 */

@NamedQuery(queryName = "createConfig", queryValue = "insert into configuracoes values (?, ?, ?)")
public class Configuracoes extends Entity implements Serializable
{

    private String config;
    private String descricao;
    private String valor;

    public String getConfig()
    {
        return config;
    }

    public void setConfig(String config)
    {
        this.config = config;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getValor()
    {
        return valor;
    }

    public void setValor(String valor)
    {
        this.valor = valor;
    }

}
