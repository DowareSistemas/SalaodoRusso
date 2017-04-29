/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.GsonBuilder;
import enums.StatusRetorno;

/**
 *
 * @author Marcos Vinícius
 */
public class OperationResult
{

    private int status;
    private String msg;
    private Object entity;

    private OperationResult(StatusRetorno statusRetorno, String msg, Object object)
    {
        this.status = statusRetorno.valor;
        this.msg = msg;
        this.entity = object;
    }

    public static String toJson(StatusRetorno statusRetorno, String msg, Object object)
    {
        OperationResult or = new OperationResult(statusRetorno, msg, object);
        return new GsonBuilder().setDateFormat("dd/MM/yyyy").create().toJson(or);
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getObject()
    {
        return entity;
    }

    public void setObject(Object object)
    {
        this.entity = object;
    }

}
