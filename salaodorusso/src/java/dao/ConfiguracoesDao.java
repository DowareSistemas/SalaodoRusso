/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.persistor.enums.FILTER_TYPE;
import br.com.persistor.enums.RESULT_TYPE;
import br.com.persistor.generalClasses.Restrictions;
import interfaces.IConfiguracoes;
import java.util.ArrayList;
import java.util.List;
import model.Configuracoes;
import repository.ConfiguracoesRepository;

/**
 *
 * @author Marcos Vinícius
 */
public class ConfiguracoesDao
{

    private List<Configuracoes> list = null;

    private ConfiguracoesDao()
    {
        list = new ArrayList<Configuracoes>();
    }

    private static ConfiguracoesDao instance = null;

    public synchronized static ConfiguracoesDao getInstance()
    {
        if (instance == null)
            instance = new ConfiguracoesDao();

        return instance;
    }

    public Configuracoes find(String configId)
    {
        for (Configuracoes conf : list)
            if (conf.getConfig().equals(configId))
                return conf;

        IConfiguracoes db = new ConfiguracoesRepository();
        db.setAutoCommitOrClose(true);

        Configuracoes config = new Configuracoes();
        db.createCriteria(config, RESULT_TYPE.UNIQUE)
                .add(Restrictions.eq(FILTER_TYPE.WHERE, "config", configId))
                .execute();

        if (config.getConfig() != null)
            if (!config.getConfig().isEmpty())
                list.add(config);

        return config;
    }

}
