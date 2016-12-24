
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

/**
 *
 * @author Persistor4J
 */
public class Servicos extends Entity
{

    private int id;
    private Date data;
    private String descricao;
    private double valor;
    private int usuario_cliente_id;
    private Usuarios usuarios;
    private int usuario_profissional_id;
    private int forma_pagamento_id;
    private Formas_pagamento formas_pagamento;

    public void setId(int id)
    {
        this.id = id;
    }

    @PrimaryKey(increment = INCREMENT.AUTO)
    public int getId()
    {
        return id;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public Date getData()
    {
        return data;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public double getValor()
    {
        return valor;
    }

    public void setUsuarios(Usuarios usuarios)
    {
        this.usuarios = usuarios;
    }

    public void setUsuario_cliente_id(int usuario_cliente_id)
    {
        this.usuario_cliente_id = usuario_cliente_id;
    }

    public int getUsuario_cliente_id()
    {
        return usuario_cliente_id;
    }

    @OneToOne(source = "usuario_cliente_id", target = "id", load = LOAD.AUTO, join_type = JOIN_TYPE.INNER)
    public Usuarios getUsuarios()
    {
        return usuarios;
    }

    public void setUsuario_profissional_id(int usuario_profissional_id)
    {
        this.usuario_profissional_id = usuario_profissional_id;
    }

    public int getUsuario_profissional_id()
    {
        return usuario_profissional_id;
    }

    public void setFormas_pagamento(Formas_pagamento formas_pagamento)
    {
        this.formas_pagamento = formas_pagamento;
    }

    public void setForma_pagamento_id(int forma_pagamento_id)
    {
        this.forma_pagamento_id = forma_pagamento_id;
    }

    public int getForma_pagamento_id()
    {
        return forma_pagamento_id;
    }

    @OneToOne(source = "forma_pagamento_id", target = "id", load = LOAD.AUTO, join_type = JOIN_TYPE.INNER)
    public Formas_pagamento getFormas_pagamento()
    {
        return formas_pagamento;
    }
}
