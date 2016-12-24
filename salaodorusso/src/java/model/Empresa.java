
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
public class Empresa extends Entity
{

    private int id;

    @NotNull(message = "O nome é obrigatório")
    @NotEmpty(message = "O nome é obrigatório")
    @Size(message = "O nome não pode conter mais de 100 caracteres")
    private String nome;

    @NotNull(message = "O CNPJ é obrigatório")
    @NotEmpty(message = "O CNPJ é obrigatório")
    @Size(max = 20, message = "O CNPJ não pode conter mais de 20 caracteres")
    private String cnpj;

    @NotNull(message = "O email é obrigatório")
    @NotEmpty(message = "O email é obrigatório")
    @Size(max = 100, message = "O email não pode conter mais de 100 caracteres")
    private String email;
    private String telefone;
    private String celular;
    private String logradouro;
    private String bairro;
    private int numero;
    private String municipio;
    private String uf;

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

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public String getCnpj()
    {
        return cnpj;
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
}
