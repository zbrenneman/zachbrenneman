/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dao;

import com.mycompany.floormaster.dto.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author apprentice
 */
public class OrderDaoImplInMemory implements OrderDao {

    

    private List<Order> orders = new ArrayList();
    private int nextId = 1;

    public OrderDaoImplInMemory() {

        for (Order a : orders) {

            if (a.getOrderNumber() >= nextId) {

                nextId = a.getOrderNumber() + 1;
            }
        }
    }

    public Order add(Order order) {

        order.setOrderNumber(nextId);

        orders.add(order);

        nextId++;


        return order;
    }

    public Order read(int id, String date) {

        List<Order> list = decode(date);

        for (Order a : list) {

            if ((a.getOrderNumber() == id)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void update(Order order, int id, String date) {

        List<Order> list = decode(date);

        ListIterator litr = list.listIterator();

        while (litr.hasNext()) {

            Order o = (Order) litr.next();

            if (o.getOrderNumber() == id) {

                litr.set(order);

            }
        }


    }

    @Override
    public void delete(Order order, int choice, String date) {

        List<Order> list = decode(date);

        ListIterator litr = list.listIterator();

        while (litr.hasNext()) {

            Order o = (Order) litr.next();

            if (o.getOrderNumber() == choice) {

                litr.remove();

            }
        }


    }

    @Override
    public void encode(String fileName, List<Order> list) {

    }

    @Override
    public List<Order> decode(String fileName) {
        
        return orders;
    }

}
