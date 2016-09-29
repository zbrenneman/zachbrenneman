/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.app;

import com.mycompany.consoleio.ConsoleIO;
import com.mycompany.vendingmachine.controller.ItemController;
import com.mycompany.vendingmachine.dao.ItemDao;
import com.mycompany.vendingmachine.dao.ItemDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {
    
    public static void main(String[] args){
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        ItemController ic = (ItemController) ctx.getBean("itemController");
        
        ic.run();
        
    }
    
}
