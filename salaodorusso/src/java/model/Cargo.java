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
public class Cargo extends Entity
{

    private int id;

    @NotNull(message = "A descrição do cargo é obrigatória")
    @NotEmpty(message = "A descrição do cargo é obrigatoria")
    @NotBlank(message = "A descrição do cargo é obrigatória")
    private String descricao;

    private Funcionario funcionario;

    @OneToMany(source = "id", target = "cargo_id", join_type = JOIN_TYPE.LEFT, load = LOAD.MANUAL)
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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

}
