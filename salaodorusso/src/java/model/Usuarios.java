
/* AUTO-GENERATED CLASS */
 /* DOES NOT ADD ACCESSOR METHODS */
 /* DOES NOT CHANGE NAME OF ACCESSOR METHODS */
package model;

import br.com.persistor.abstractClasses.Entity;
import br.com.persistor.abstractClasses.Entity;
import br.com.persistor.annotations.PrimaryKey;
import br.com.persistor.enums.INCREMENT;
import br.com.persistor.annotations.OneToOne;
import br.com.persistor.annotations.OneToMany;
import br.com.persistor.enums.JOIN_TYPE;
import br.com.persistor.enums.LOAD;
import java.util.Date;
import java.io.InputStream;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Persistor4J
 */
public class Usuarios extends Entity
{

    private int id;
    
    @NotNull(message = "O nome do usuário é obrigatório")
    @NotEmpty(message = "O nome do usuário é obrigatório")
    @Size(max = 50, message = "O nome não pode conter mais de 50 caracteres")
    private String nome;
    
    @NotNull(message = "A senha é obrigatória")
    @NotEmpty(message = "A senha é obrigatória")
    @Size(max = 50, message = "A senhã não pode conter mais de 50 caracteres")
    private String senha;
    
    @NotNull(message = "Informe se o usuário está inativo")
    private boolean inativo;
    private String email;
    private String telefone;
    private String celular;
    private String logradouro;
    private String bairro;
    private int numero;
    private String municipio;
    
    @Size(max = 2, message = "A UF não pode conter mais de 2 caracteres")
    private String uf;
    private int tipo_usuario;

    public void setId(int id)
    {
        this.id = id;
    }

    @PrimaryKey(increment = INCREMENT.AUTO)
    public int getId()
    {
        return id;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return nome;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setInativo(boolean inativo)
    {
        this.inativo = inativo;
    }

    public boolean isInativo()
    {
        return inativo;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setCelular(String celular)
    {
        this.celular = celular;
    }

    public String getCelular()
    {
        return celular;
    }

    public void setLogradouro(String logradouro)
    {
        this.logradouro = logradouro;
    }

    public String getLogradouro()
    {
        return logradouro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public int getNumero()
    {
        return numero;
    }

    public void setMunicipio(String municipio)
    {
        this.municipio = municipio;
    }

    public String getMunicipio()
    {
        return municipio;
    }

    public void setUf(String uf)
    {
        this.uf = uf;
    }

    public String getUf()
    {
        return uf;
    }

    public void setTipo_usuario(int tipo_usuario)
    {
        this.tipo_usuario = tipo_usuario;
    }

    public int getTipo_usuario()
    {
        return tipo_usuario;
    }
}
