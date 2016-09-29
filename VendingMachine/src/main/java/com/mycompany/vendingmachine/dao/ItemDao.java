/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ItemDao {
    
    public Item add(Item item);
    
    public Item read(int id);
    
    public void update(Item item);
    
    public void delete(Item item);
    
    public List<Item> list();
    

    
    
    
    
    
}
