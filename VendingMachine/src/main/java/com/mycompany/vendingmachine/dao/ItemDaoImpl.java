/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class ItemDaoImpl implements ItemDao {

    private static String FILENAME = "items.txt";
    private static final String TOKEN = "::";

    private List<Item> items = null;
    private int nextId = 1;

    public ItemDaoImpl() {

        items = decode();

        for (Item i : items) {

            if (i.getId() >= nextId) {

                nextId = i.getId() + 1;
            }
        }

    }

    public Item add(Item item) {
        item.setId(nextId);
        
        items.add(item);
        
        nextId ++;
        
        encode();
        
        return item;
    }

    public Item read(int id) {
        
        for(Item i : items){
            
            if(i.getId() == id)
                return i;
        }
        return null;
    }

    public void update(Item item) {

        for(Item i : items){
            
            if(i.getId() == item.getId())
                i = item;
            
        }
        
        encode();

    }

    public void delete(Item item) {

        for(Item i : items){
            
            if(i.getId() == item.getId())
                items.remove(i);
            break;
            
        }
        
        encode();
    }

    public List<Item> list() {

        return new ArrayList(items);

    }

    public void encode() {

        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(FILENAME));

            for (Item i : items) {

                out.print(i.getId());
                out.print(TOKEN);

                out.print(i.getCost());
                out.print(TOKEN);

                out.print(i.getName());
                out.print(TOKEN);

                out.print(i.getNumInInventory());
                out.println("");

            }

            out.flush();

        } catch (IOException ex) {

        } finally {
            out.close();

        }

    }

    public List<Item> decode() {

        List<Item> tempItemList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Item myItem = new Item();

                int itemId = Integer.parseInt(stringParts[0]);
                double itemCost = Double.parseDouble(stringParts[1]);
                int numInventory = Integer.parseInt(stringParts[3]);

                myItem.setId(itemId);
                myItem.setCost(itemCost);
                myItem.setName(stringParts[2]);
                myItem.setNumInInventory(numInventory);

                tempItemList.add(myItem);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempItemList;
    }
}
