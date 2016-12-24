/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;

/**
 *
 * @author Marcos Vinícius
 */
public class OperationResult
{
    private int status;
    private String msg;
    private Object object;

    public OperationResult(int status, String msg, Object object)
    {
       this.status = status;
       this.msg = msg;
       this.object = object;
    }
    
    public String toJson()
    {
        return new Gson().toJson(this);
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
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }
    
}
