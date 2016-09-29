/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ItemDaoInMemoryImpl implements ItemDao {

    private List<Item> items = new ArrayList();
    private int nextId = 1;

    public ItemDaoInMemoryImpl() {

        for (Item i : items) {

            if (i.getId() >= nextId) {

                nextId = i.getId() + 1;
            }
        }

    }

    @Override
    public Item add(Item item) {
        item.setId(nextId);

        items.add(item);

        nextId++;

        return item;
    }

    @Override
    public Item read(int id) {

        for (Item i : items) {

            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void update(Item item) {

        for (Item i : items) {

            if (i.getId() == item.getId()) {
                i = item;
            }

        }

    }

    @Override
    public void delete(Item item) {

        for (Item i : items) {

            if (i.getId() == item.getId()) {
                items.remove(i);
            }
            break;

        }

    }

    @Override
    public List<Item> list() {

        return new ArrayList(items);

    }

}
