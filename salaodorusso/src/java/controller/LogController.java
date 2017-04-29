/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ConfiguracoesDao;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Marcos Vinícius
 */
public class LogController
{

    public static boolean write(String className, String method, String text)
    {
        try
        {
            String PATH = ConfiguracoesDao.getInstance().find("LOG_PATH").getValor();
            String fileName = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            fileName = PATH + "VIVEIRO " + fileName + ".log";

            File file = new File(fileName);
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("Data/hora.....:  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            pw.println("Classe........:  " + className);
            pw.println("Metodo........:  " + method);
            pw.println("Message.......:  " + text);
            pw.println("\n");

            pw.close();
            bw.close();
            fw.close();

            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
