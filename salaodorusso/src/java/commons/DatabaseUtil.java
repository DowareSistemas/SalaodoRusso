/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import br.com.persistor.generalClasses.CodeFirstDatabase;
import br.com.persistor.generalClasses.CodeFirstTableDomain;
import com.sun.corba.se.impl.orbutil.concurrent.CondVar;
import dao.ConfiguracoesDao;
import model.*;

/**
 *
 * @author Marcos Vinícius
 */
public class DatabaseUtil
{

    public void createIfNotExists() throws Exception
    {
        CodeFirstDatabase database = new CodeFirstDatabase(true);

        database.addTableDomain(getConfiguracoes());
        database.addTableDomain(getCargo());
        database.addTableDomain(getTipoServico());
        database.addTableDomain(getEndereco());
        database.addTableDomain(getExpediente());
        database.addTableDomain(getFuncionario());
        database.addTableDomain(getFuncionarioServicos());
        database.addTableDomain(getUsuario());
        database.addTableDomain(getDica());
        database.addTableDomain(getProduto());
        
        database.createTables(SessionProvider.getConfig());
    }

    /**
     * configuracoes
     * @return 
     */
    public CodeFirstTableDomain getConfiguracoes()
    {
        return new CodeFirstTableDomain(Configuracoes.class);
    }
    
    /**
     * cargo
     *
     * @return
     */
    private CodeFirstTableDomain getCargo()
    {
        return new CodeFirstTableDomain(Cargo.class)
                .setColumnProperty("id", false, 0, 0, null)
                .setColumnProperty("nome", false, 50, 0, null);
    }

    /**
     * tiposervico
     *
     * @return
     */
    private CodeFirstTableDomain getTipoServico()
    {
        return new CodeFirstTableDomain(TipoServico.class)
                .setColumnProperty("id", false, 0, 0, null)
                .setColumnProperty("descricao", false, 50, 0, null);
    }

    /**
     * expediente
     *
     * @return
     */
    private CodeFirstTableDomain getExpediente()
    {
        return new CodeFirstTableDomain(Expediente.class)
                .setColumnProperty("id", false, 0, 0, null)
                .setColumnProperty("titulo", false, 50, 0, null)
                .setColumnProperty("segunda", true, 11, 0, null)
                .setColumnProperty("terca", true, 11, 0, null)
                .setColumnProperty("quarta", true, 11, 0, null)
                .setColumnProperty("quinta", true, 11, 0, null)
                .setColumnProperty("sexta", true, 11, 0, null)
                .setColumnProperty("sabado", true, 11, 0, null);
    }

    /**
     * endereco
     *
     * @return
     */
    private CodeFirstTableDomain getEndereco()
    {
        return new CodeFirstTableDomain(Endereco.class)
                .setColumnProperty("id", false, 0, 0, null)
                .setColumnProperty("logradoudo", false, 100, 0, null)
                .setColumnProperty("numero", false, 0, 0, null)
                .setColumnProperty("bairro", false, 50, 0, null)
                .setColumnProperty("municipio", false, 50, 0, false)
                .setColumnProperty("cep", false, 20, 0, null);
    }

    /**
     * funcionario
     *
     * @return
     */
    private CodeFirstTableDomain getFuncionario()
    {
        return new CodeFirstTableDomain(Funcionario.class)
                .setColumnProperty("id", false, 0, 0, null)
                .setColumnProperty("nome", false, 100, 0, null)
                .setColumnProperty("email", true, 100, 0, null)
                .setColumnProperty("telefone", true, 20, 0, null)
                .setColumnProperty("celular", true, 20, 0, null)
                .setColumnProperty("endereco_id", false, 0, 0, 0)
                .setColumnProperty("cargo_id", false, 0, 0, 0)
                .setColumnProperty("expediente_id", false, 0, 0, null);
    }

    /**
     * funcionarioservicos
     *
     * @return
     */
    private CodeFirstTableDomain getFuncionarioServicos()
    {
        return new CodeFirstTableDomain(FuncionarioServicos.class)
                .setColumnProperty("id", false, 0, 0, null)
                .setColumnProperty("funcionario_id", false, 0, 0, null)
                .setColumnProperty("tiposervico_id", false, 0, 0, null);
    }

    /**
     * usuario
     *
     * @return
     */
    private CodeFirstTableDomain getUsuario()
    {
        return new CodeFirstTableDomain(Usuario.class)
                .setColumnProperty("id", false, 0, 0, null)
                .setColumnProperty("nome", false, 100, 0, null)
                .setColumnProperty("email", false, 100, 0, null)
                .setColumnProperty("senha", false, 50, 0, null)
                .setColumnProperty("telefone", true, 20, 0, null)
                .setColumnProperty("celular", true, 20, 0, null);
    }

    /**
     * dica
     *
     * @return
     */
    private CodeFirstTableDomain getDica()
    {
        return new CodeFirstTableDomain(Dica.class)
                .setColumnProperty("id", false, 0, 0, null)
                .setColumnProperty("titulo", false, 100, 0, null)
                .setColumnProperty("descricao", false, 1000, 0, null)
                .setColumnProperty("foto", false, 100, 0, null);
    }

    /**
     * produto
     *
     * @return
     */
    private CodeFirstTableDomain getProduto()
    {
        return new CodeFirstTableDomain(Produto.class)
                .setColumnProperty("id", false, 0, 0, null)
                .setColumnProperty("nome", false, 100, 0, null)
                .setColumnProperty("descricao", false, 1000, 0, null)
                .setColumnProperty("valor", false, 10, 2, null);
    }
}
