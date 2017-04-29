/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.com.persistor.abstractClasses.Entity;
import br.com.persistor.annotations.OneToOne;
import br.com.persistor.annotations.PrimaryKey;
import br.com.persistor.enums.INCREMENT;
import br.com.persistor.enums.JOIN_TYPE;
import br.com.persistor.enums.LOAD;

/**
 *
 * @author Marcos Vinícius
 */
public class FuncionarioServicos extends Entity
{
    private int id;
    private int funcionario_id;
    private int tiposervico_id;

    private Funcionario funcionario;
    private TipoServico tipoServico;

    @OneToOne(source = "funcionario_id", target = "id", join_type = JOIN_TYPE.INNER, load = LOAD.AUTO)
    public Funcionario getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario)
    {
        this.funcionario = funcionario;
    }

    @OneToOne(source = "tipoServico_id", target = "id", join_type = JOIN_TYPE.INNER, load = LOAD.AUTO)
    public TipoServico getTipoServico()
    {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico)
    {
        this.tipoServico = tipoServico;
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

    public int getFuncionario_id()
    {
        return funcionario_id;
    }

    public void setFuncionario_id(int funcionario_id)
    {
        this.funcionario_id = funcionario_id;
    }

    public int getTiposervico_id()
    {
        return tiposervico_id;
    }

    public void setTiposervico_id(int tiposervico_id)
    {
        this.tiposervico_id = tiposervico_id;
    }

}
