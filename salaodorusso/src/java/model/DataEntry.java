/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Marcos Vinícius
 */
public class DataEntry implements Serializable
{

    private String dataKey;
    private Object dataValue;

    public String getDataKey()
    {
        return dataKey;
    }

    public void setDataKey(String dataKey)
    {
        this.dataKey = dataKey;
    }

    public Object getDataValue()
    {
        return dataValue;
    }

    public void setDataValue(Object dataValue)
    {
        this.dataValue = dataValue;
    }

}
