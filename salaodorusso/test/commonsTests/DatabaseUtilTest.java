/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonsTests;

import commons.DatabaseUtil;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Marcos Vinícius
 */
public class DatabaseUtilTest
{
    
    @Test
    public void DEVE_CRIAR_O_BANCO_DE_DADOS()
    {
        try
        {
            DatabaseUtil databaseUtil = new DatabaseUtil();
            databaseUtil.createIfNotExists();
            
            assertTrue(true);
        }
        catch (Exception ex)
        {
            assertTrue(false);
            System.err.println(ex.getMessage());
        }
    }
    
}
