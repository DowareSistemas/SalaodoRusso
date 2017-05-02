/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import controller.Database;
import dao.ConfiguracoesDao;
import java.io.File;
import model.Configuracoes;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Marcos Vinícius
 */
public class Utility
{
    public static void removeImageInDisk(String fName)
    {
        if(fName == null)
            return;
        if(fName.isEmpty())
            return;
        try
        {
            Configuracoes config = ConfiguracoesDao.getInstance().find("FOTO_PATH");
            File file = new File(config.getValor() + fName);
            if(file.exists())
                file.delete();
        }
        catch(Exception ex)
        {
            
        }
    }

    public static String saveImageInDisk(MultipartFile mFile)
    {
        try
        {
            Configuracoes config = ConfiguracoesDao.getInstance().find("FOTO_PATH");

            String fileName = new Database().generateDataKey() + ".jpg";
            while (new File(config.getValor() + fileName).exists())
                fileName = new Database().generateDataKey() + ".jpg";

            mFile.transferTo(new File(config.getValor() + fileName));
            return fileName;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    public static String getBindingResultMessage(BindingResult bindingResult)
    {
        return bindingResult.getFieldErrors().get(0).getDefaultMessage();
    }

    public static final String DEFAULT_PRODUCES_VALUE = "application/json; charset=utf-8";
}
