/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dao;

import com.mycompany.floormaster.dto.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderDao {
    
    public Order add(Order order);

    public Order read(int id, String date);

    public void update(Order order, int id, String date);

    public void delete(Order order, int choice, String date);
    
    public void encode(String fileName, List<Order> list);
    
    public List<Order> decode(String fileName);
}
