/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.vendingmachine.dao.ItemDao;
import com.mycompany.vendingmachine.dao.ItemDaoInMemoryImpl;
import com.mycompany.vendingmachine.dto.Item;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class DaoTest {

    ItemDao dao;

    public DaoTest() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        dao = ctx.getBean("itemDao", ItemDao.class);
        
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAdd() {

        Item item = new Item();

        item.setName("Grapes");

        Item newItem = dao.add(item);

        String name = item.getName();

        Assert.assertEquals("Grapes", name);

    }

    @Test
    public void testAdd2() {

        Item item = new Item();

        item.setNumInInventory(23);

        Item newItem = dao.add(item);

        int inventory = item.getNumInInventory();

        Assert.assertEquals(23, inventory);

    }

    @Test
    public void testAdd3() {

        Item item = new Item();

        item.setName("Beans");

        Item newItem = dao.add(item);

        int id = newItem.getId();

        Assert.assertNotNull(id);

    }

    @Test
    public void testRead() {

        Item newItem = dao.read(8);

        Assert.assertNull(newItem);

    }

    @Test
    public void testRead2() {

        Item newItem = dao.read(1);

        Assert.assertNull(newItem);

    }
    
    @Test
    public void testRead3() {

        Item item = new Item();
        
        item.setName("Chips");
        
        item = dao.add(item);

        Item newItem = dao.read(1);
        
        String name = newItem.getName();
        
        Assert.assertEquals("Chips", name);

    }
    
    @Test
    public void testUpdate(){
        
        Item item = new Item();
        
        item.setName("Burger");
        
        Item newItem = dao.add(item);
        
        newItem.setName("Fries");
        
        dao.update(newItem);
        
        Item update = dao.read(1);
        
        Assert.assertEquals("Fries", update.getName());
        
    }
    
    @Test
    public void testUpdate2(){
        
        Item item = new Item();
        
        item.setNumInInventory(19);
        
        Item newItem = dao.add(item);
        
        newItem.setNumInInventory(5);
        
        dao.update(newItem);
        
        Item update = dao.read(1);
        
        Assert.assertEquals(5, update.getNumInInventory());
        
    }
    
    @Test
    public void testUpdate3(){
        
        Item item = new Item();
        
        item.setCost(2);
        
        Item newItem = dao.add(item);
        
        newItem.setCost(6);
        
        dao.update(newItem);
        
        Item update = dao.read(1);
        
        Assert.assertEquals(6, (int)update.getCost());
        
    }
    
    @Test
    public void testDelete(){
        
        Item item = new Item();
        
        item = dao.add(item);
        
        dao.delete(item);
        
        Item newItem = dao.read(1);
        
        Assert.assertNull(newItem);
        
    }
    
        @Test
    public void testDelete2(){
        
        Item item = new Item();
        
        item = dao.add(item);
        
        dao.delete(item);
        
        Item newItem = dao.read(0);
        
        Assert.assertNull(newItem);
        
    }
    
        @Test
    public void testDelete3(){
        
        Item item = new Item();
        
        item = dao.add(item);
        
        dao.delete(item);
        
        Item newItem = dao.read(89);
        
        Assert.assertNull(newItem);
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
