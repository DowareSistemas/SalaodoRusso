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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Marcos Vinícius
 */
public class TipoServico extends Entity
{

    private int id;

    @NotNull(message = "Informe a descrição")
    @NotEmpty(message = "Informe a descrição")
    @NotBlank(message = "Informe a descrição")
    private String descricao;

    private FuncionarioServicos funcionarioServicos;

    @OneToMany(source = "id", target = "tiposervico_id", join_type = JOIN_TYPE.LEFT, load = LOAD.MANUAL)
    public FuncionarioServicos getFuncionarioServicos()
    {
        if(funcionarioServicos == null)
            funcionarioServicos = (FuncionarioServicos) FieldHandled.readObject(this, "funcionarioServicos");
        return funcionarioServicos;
    }

    public void setFuncionarioServicos(FuncionarioServicos funcionarioServicos)
    {
        this.funcionarioServicos = funcionarioServicos;
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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

}
