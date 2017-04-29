/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ConfiguracoesDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import model.Configuracoes;
import model.DataEntry;

/**
 *
 * @author Marcos Vinícius
 */
public class Database
{
    
    private static List<DataEntry> cachedEntries = null;
    
    public static DataEntry findInCache(String dataKey)
    {
        if (cachedEntries == null)
            cachedEntries = new ArrayList<DataEntry>();
        
        for (DataEntry de : cachedEntries)
            if (de.getDataKey().equals(dataKey))
                return de;
        
        return null;
    }
    
    public static void removeFromCache(String dataKey)
    {
        if (cachedEntries == null)
            cachedEntries = new ArrayList<DataEntry>();
        
        for (DataEntry de : cachedEntries)
            if (de.getDataKey().equals(dataKey))
            {
                cachedEntries.remove(de);
                break;
            }
    }
    
    public static void addToCache(DataEntry entry)
    {
        if (cachedEntries == null)
            cachedEntries = new ArrayList<DataEntry>();
        
        cachedEntries.add(entry);
    }
    
    public static void mergeToCache(DataEntry entry)
    {
        if (cachedEntries == null)
            cachedEntries = new ArrayList<DataEntry>();
        
        for (DataEntry de : cachedEntries)
            if (de.getDataKey().equals(entry.getDataKey()))
            {
                de = entry;
                break;
            }
    }
    
    public DataEntry read(String dataKey)
    {
        DataEntry cachedEntry = Database.findInCache(dataKey);
        if (cachedEntry != null)
            return cachedEntry;
        
        Configuracoes config = ConfiguracoesDao.getInstance().find("DATA_PATH");
        try
        {
            File file = new File(config.getValor() + dataKey);
            if (!file.exists())
                return null;
            
            FileInputStream fs = new FileInputStream(config.getValor() + dataKey);
            ObjectInputStream os = new ObjectInputStream(fs);
            Object object = os.readObject();
            os.close();
            fs.close();
            
            if (object != null)
                Database.addToCache((DataEntry) object);
            
            return (DataEntry) object;
        }
        catch (IOException ex)
        {
            LogController.write("Database", "read", ex.getMessage());
        }
        catch (ClassNotFoundException ex)
        {
            LogController.write("Database", "read", ex.getMessage());
        }
        
        return null;
    }
    
    public String generateDataKey()
    {
        Configuracoes config = ConfiguracoesDao.getInstance().find("DATA_PATH");
        SecureRandom random = new SecureRandom();
        String result = new BigInteger(130, random).toString(32);
        
        while (new File(config.getValor() + result).exists())
            result = new BigInteger(130, random).toString(32);
        
        return result;
    }
    
    public void saveChanges(DataEntry dataEntry)
    {
        Configuracoes config = ConfiguracoesDao.getInstance().find("DATA_PATH");
        try
        {
            FileOutputStream fs = new FileOutputStream(config.getValor() + dataEntry.getDataKey());
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(dataEntry);
            os.close();
            fs.close();
            
            DataEntry cachedEntry = Database.findInCache(dataEntry.getDataKey());
            if(cachedEntry == null)
                Database.addToCache(dataEntry);
            else
                Database.mergeToCache(dataEntry);
        }
        catch (Exception ex)
        {
            LogController.write("Database", "write", ex.getMessage());
        }
    }
    
    void drop(String dataKey)
    {
        Configuracoes config = ConfiguracoesDao.getInstance().find("DATA_PATH");
        File file = new File(config.getValor() + dataKey);
        if (file.exists())
            file.delete();
        
        Database.removeFromCache(dataKey);
    }
}
