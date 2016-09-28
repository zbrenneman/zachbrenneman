/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.floormaster.dao.OrderDao;
import com.mycompany.floormaster.dto.Order;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class OrderDaoTests {

    Order order1 = new Order();
    Order order2 = new Order();

    OrderDao oDao;

    public OrderDaoTests() {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        oDao = ctx.getBean("oDao", OrderDao.class);

    }

    @Before
    public void setUp() {
        
        order1.setCustomerName("Richard");
        order1.setState("PA");
        order1.setArea(300);
        order1.setTaxRate(6.75);
        order1.setpType("Tile");
        
        order2.setCustomerName("Carl");
        order2.setState("OH");
        order2.setArea(500);
        order2.setTaxRate(6.25);
        order2.setpType("Carpet");
        
    }
    

    @After
    public void tearDown() {
    }
    
    
    @Test
    public void testAdd(){
       
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
