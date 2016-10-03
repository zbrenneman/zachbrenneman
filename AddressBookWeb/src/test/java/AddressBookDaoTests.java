/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.addressbookweb.dao.AddressBookDao;
import com.mycompany.addressbookweb.dto.Address;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoTests {
    
    AddressBookDao dao;
    Address a = new Address();
    Address a2 = new Address();
    
    public AddressBookDaoTests() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        dao = (AddressBookDao) ctx.getBean("addressDao");
        
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("DELETE FROM address");
        
    }
    
    @Before
    public void setUp() {
        
        a.setFirstName("Tim");
        a.setLastName("Brenneman");
        a.setCity("Orrville");
        a.setStreet("2380 Wayne St");
        a.setState("Ohio");
        a.setZip("44667");
        
        a2.setFirstName("Zach");
        a2.setLastName("Brenneman");
        a2.setCity("Alliance");
        a2.setStreet("1389 Broadway St");
        a2.setState("Ohio");
        a2.setZip("44601");
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreate() {
        
        Address fromDb = dao.create(a);
        
        Assert.assertEquals(fromDb.getCity(), "Orrville");
        
    }
    
    @Test
    public void testCreate2() {
        
        Address fromDb = dao.create(a);
        
        Assert.assertEquals(fromDb.getState(), "Ohio");
        
    }
    
    @Test
    public void testCreate3() {
        
        Address fromDb = dao.create(a);
        
        Assert.assertEquals(fromDb.getZip(), "44667");
        
    }
    
    @Test
    public void testRead() {
        
        Address fromDb = dao.create(a);
        
        Address newFromDb = dao.read(fromDb.getId());
        
        Assert.assertEquals(newFromDb.getFirstName(), "Tim");
        
    }
    
    @Test
    public void testUpdate() {
        
        Address fromDb = dao.create(a);
        
        fromDb.setCity("Wooster");
        
        dao.update(fromDb);
        
        Address newFromDb = dao.read(fromDb.getId());
        
        Assert.assertEquals(newFromDb.getCity(), "Wooster");
        
    }
    
    @Test
    public void testUpdate2() {
        
        Address fromDb = dao.create(a);
        
        fromDb.setZip("44677");
        
        dao.update(fromDb);
        
        Address newFromDb = dao.read(fromDb.getId());
        
        Assert.assertEquals(newFromDb.getZip(), "44677");
        
    }
    
    @Test
    public void testUpdate3() {
        
        Address fromDb = dao.create(a);
        
        fromDb.setLastName("Besancon");
        
        dao.update(fromDb);
        
        Address newFromDb = dao.read(fromDb.getId());
        
        Assert.assertEquals(newFromDb.getLastName(), "Besancon");
        
    }
    
    @Test
    public void testSearchByFirstName1(){
        
        Address fromDb = dao.create(a);
        Address fromDb2 = dao.create(a2);
        
        List<Address> list = dao.searchByFirstName("Zach");
        
        Assert.assertEquals(list.size(), 1);
        
    }
    
    @Test
    public void testSearchByFirstName2(){
        
        Address fromDb = dao.create(a);
        Address fromDb2 = dao.create(a2);
        
        List<Address> list = dao.searchByFirstName("Tim");
        
        Assert.assertEquals(list.size(), 1);
        
    }
    
    @Test
    public void testSearchByLastName(){
        
        Address fromDb = dao.create(a);
        Address fromDb2 = dao.create(a2);
        
        List<Address> list = dao.searchByLastName("Brenneman");
        
        Assert.assertEquals(list.size(), 2);
        
    }
    
    @Test
    public void testSearchByCity(){
        
        Address fromDb = dao.create(a);
        Address fromDb2 = dao.create(a2);
        
        List<Address> list = dao.searchByCity("Orrville");
        
        Assert.assertEquals(list.size(), 1);
        
    }
    
    @Test
    public void testSearchByState(){
        
        Address fromDb = dao.create(a);
        Address fromDb2 = dao.create(a2);
        
        List<Address> list = dao.searchByState("Ohio");
        
        Assert.assertEquals(list.size(), 2);
        
    }
    
    @Test
    public void testSearchByZip(){
        
        Address fromDb = dao.create(a);
        Address fromDb2 = dao.create(a2);
        
        List<Address> list = dao.searchByZip("44667");
        
        Assert.assertEquals(list.size(), 1);
        
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
