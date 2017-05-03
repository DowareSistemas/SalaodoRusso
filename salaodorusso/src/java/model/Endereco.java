/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.com.persistor.abstractClasses.Entity;
import br.com.persistor.annotations.PrimaryKey;
import br.com.persistor.enums.INCREMENT;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Marcos Vinícius
 */
public class Endereco extends Entity implements Serializable
{

    private int id;
    
    @NotNull(message = "Informe o logradouro")
    @NotEmpty(message = "Informe o logradouro")
    @NotBlank(message = "Informe o logradouro")
    private String logradouro;
    
    @Min(value = 1, message = "O número da residencia não pode ser inferior a 0 (zero)")
    private int numero;
    private String complemento;
    
    @NotNull(message = "Informe o bairro")
    @NotEmpty(message = "Informe o bairro")
    @NotBlank(message = "Informe o bairro")
    private String bairro;
    
    @NotNull(message = "Informe o municipio")
    @NotEmpty(message = "Informe o municipio")
    @NotBlank(message = "Informe o municipio")
    private String municipio;

    @NotNull(message = "Informe o CEP")
    @NotEmpty(message = "Informe o CEP")
    @NotBlank(message = "Informe o municipio")
    private String cep;

    @PrimaryKey(increment = INCREMENT.AUTO)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLogradouro()
    {
        return logradouro;
    }

    public void setLogradouro(String logradouro)
    {
        this.logradouro = logradouro;
    }

    public int getNumero()
    {
        return numero;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public String getComplemento()
    {
        return complemento;
    }

    public void setComplemento(String complemento)
    {
        this.complemento = complemento;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getMunicipio()
    {
        return municipio;
    }

    public void setMunicipio(String municipio)
    {
        this.municipio = municipio;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

}
