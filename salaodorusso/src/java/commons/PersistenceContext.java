/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import br.com.persistor.generalClasses.EntitySet;
import br.com.persistor.interfaces.IPersistenceContext;
import model.*;

/**
 *
 * @author Marcos Vinícius
 */
public class PersistenceContext implements IPersistenceContext
{

    private EntitySet<Dica> dicas;
    private EntitySet<Cargo> cargos;
    private EntitySet<Produto> produtos;
    private EntitySet<Funcionario> funcionarios;
    private EntitySet<TipoServico> tiposServicos;
    private EntitySet<Endereco> enderecos;
    private EntitySet<Expediente> expedientes;
    private EntitySet<FuncionarioServicos> funcionarioServicos;

    public EntitySet<Cargo> getCargos()
    {
        return cargos;
    }

    public void setCargos(EntitySet<Cargo> cargos)
    {
        this.cargos = cargos;
    }

    public EntitySet<Produto> getProdutos()
    {
        return produtos;
    }

    public void setProdutos(EntitySet<Produto> produtos)
    {
        this.produtos = produtos;
    }

    public EntitySet<Funcionario> getFuncionarios()
    {
        return funcionarios;
    }

    public void setFuncionarios(EntitySet<Funcionario> funcionarios)
    {
        this.funcionarios = funcionarios;
    }

    public EntitySet<TipoServico> getTiposServicos()
    {
        return tiposServicos;
    }

    public void setTiposServicos(EntitySet<TipoServico> tiposServicos)
    {
        this.tiposServicos = tiposServicos;
    }

    public EntitySet<Endereco> getEnderecos()
    {
        return enderecos;
    }

    public void setEnderecos(EntitySet<Endereco> enderecos)
    {
        this.enderecos = enderecos;
    }

    public EntitySet<Expediente> getExpedientes()
    {
        return expedientes;
    }

    public void setExpedientes(EntitySet<Expediente> expedientes)
    {
        this.expedientes = expedientes;
    }

    public EntitySet<FuncionarioServicos> getFuncionarioServicos()
    {
        return funcionarioServicos;
    }

    public void setFuncionarioServicos(EntitySet<FuncionarioServicos> funcionarioServicos)
    {
        this.funcionarioServicos = funcionarioServicos;
    }

    public EntitySet<Dica> getDicas()
    {
        return dicas;
    }

    public void setDicas(EntitySet<Dica> dicas)
    {
        this.dicas = dicas;
    }

}
