/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.com.persistor.abstractClasses.Entity;
import br.com.persistor.annotations.PrimaryKey;
import br.com.persistor.enums.INCREMENT;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Marcos Vinícius
 */
public class Dica extends Entity
{

    private int id;
    
    @NotNull(message = "O título da dica é obrigatório")
    @NotEmpty(message = "O título da dica é obrigatório")
    @NotBlank(message = "O título da dica é obrigatório")
    private String titulo;
    
    @NotNull(message = "A descrição da dica é obrigatória")
    @NotEmpty(message = "A descrição da dica é obrigatória")
    @NotBlank(message = "A descrição da dica é obrigatória")
    private String descricao;
    private String foto;

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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

}
