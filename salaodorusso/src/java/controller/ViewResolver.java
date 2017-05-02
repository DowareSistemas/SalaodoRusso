/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Marcos Vinícius
 */
@Controller
@Scope(value = "request")
public class ViewResolver
{

    private String component(String name)
    {
        return ("/componentes/" + name);
    }
    
    @RequestMapping(value = "/home")
    public String home()
    {
        return "home";
    }
    
    @RequestMapping(value = "/dicas")
    public String dicas()
    {
        return "dicas";
    }

    @RequestMapping(value = "/produtos")
    public String produtos()
    {
        return "produtos";
    }
    
    @RequestMapping(value = "/admin")
    public String admin()
    {
        return "administrador";
    }
    
    @RequestMapping(value = "/getviewbase")
    public String getViewBase(@RequestParam(value = "viewName") String viewName)
    {
        return component(viewName);
    }
}
