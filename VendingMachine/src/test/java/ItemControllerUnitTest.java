/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.consoleio.ConsoleIO;
import com.mycompany.vendingmachine.controller.ItemController;
import com.mycompany.vendingmachine.dao.ItemDaoInMemoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class ItemControllerUnitTest {
    
    ItemDaoInMemoryImpl dao = new ItemDaoInMemoryImpl();
    ConsoleIO io = new ConsoleIO();
    
    ItemController ic = new ItemController(dao, io);
    
    
    
    public ItemControllerUnitTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetAndSetAmount(){
        
        
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
