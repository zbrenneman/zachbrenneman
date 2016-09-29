/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.consoleio.ConsoleIO;
import com.mycompany.vendingmachine.dao.ItemDao;
import com.mycompany.vendingmachine.dao.ItemDaoImpl;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ItemController {

    private ConsoleIO io;

    private ItemDao itemDao;

    Change change = new Change();

    double money;

    double roundedMoney;

    boolean playAgain = true;
    
    public ItemController(ItemDao dao, ConsoleIO console){
        
        this.itemDao = dao;
        
        this.io = console;
        
    }

    public void run() {

        printMenu();
        io.printString("");

        while (playAgain) {

            roundedMoney = Math.round(change.getAmount() * 100D) / 100D;
            io.printString("You have " + change.getQuarters() + " quarters available");
            io.printString("You have " + change.getDimes() + " dimes available");
            io.printString("You have " + change.getNickels() + " nickels available");
            io.printString("You have " + change.getPennies() + " pennies available");
            io.printString("Total money: " + roundedMoney);
            io.printString("");

            int quit = io.getIntInRange("Press 0 to quit, press 1 to continue", 1, 0);

            if (quit == 0) {
                playAgain = false;
            } else {
                continuePurchase();
            }
        }
    }

    void continuePurchase() {

        io.printString("");
        double newMoney = io.getDouble("How much money would you like to add?");

        io.printString("");
        printMenu();
        io.printString("");

        getAndSetAmount(newMoney);
        
        io.printString("You have " + (Math.round(change.getAmount() * 100D) / 100D) + " available");
        io.printString("");

        io.printString("Enter the number for the item you would like to purchase");
        int choice = io.getIntInRange("", 5, 1);
        
        buyOrDeny(choice);
    }

    void buyOrDeny(int choice) {
        Item item = itemDao.read(choice);
        
        double cost =item.getCost();

        if (money >= cost) {

            buyItem(choice);

        } else {
            addOrLeave();

        }
    }

    void addOrLeave() {
        io.printString("");
        io.printString("Insufficient funds");
        io.printString("");
        
        int yn = io.getIntInRange("Add money? (1 for y, 2 for n)", 2, 1);
        
        if (yn == 2) {
            playAgain = false;
        }
    }

    void getAndSetAmount(double newMoney) {
        money = change.getAmount();
        
        money += newMoney;
        
        change.setAmount(money);
    }

    private void printMenu() {

        List<Item> items = itemDao.list();
        for (Item i : items) {

            double cost = i.getCost();
            String name = i.getName();
            int id = i.getId();

            io.printString("+--Item--------Cost----+");
            io.printString(" " + id + " " + name + "----- " + cost);

        }
    }

    private void buyItem(int id) {

        Item item = itemDao.read(id);

        double cost = item.getCost();

        double money = change.getAmount();

        int invNum = item.getNumInInventory();

        boolean yesOrNo = checkInventory(invNum, money, item);

        if (yesOrNo == true) {

            updateItemInfo(money, cost, item, invNum);

        } else {
            calculateChange(money * 100);
            change.setAmount(money);
        }
    }

    void updateItemInfo(double money1, double cost, Item item, int invNum) {
        money1 -= cost;
        double pennies = money1 * 100;
        calculateChange(pennies);
        change.setAmount(money1);
        item.setNumInInventory(invNum - 1);
        itemDao.update(item);
    }

    private void calculateChange(double pennies) {

        int penny = (int) pennies;
        int nickel = 0;
        int dime = 0;
        int quarter = 0;
        int remainder = 0;

        remainder = getRemainder(penny, 25);
        penny -= remainder;
        quarter += (penny / 25);
        penny += remainder;
        penny -= (25 * quarter);

        remainder = getRemainder(penny, 10);
        penny -= remainder;
        dime += (penny / 10);
        penny += remainder;
        penny -= (10 * dime);

        remainder = penny % 5;
        penny -= remainder;
        nickel += (penny / 5);
        penny += remainder;
        penny -= (nickel * 5);

        change.setPennies(penny);
        change.setNickels(nickel);
        change.setDimes(dime);
        change.setQuarters(quarter);

    }

    int getRemainder(int penny, int x) {
        int remainder;
        remainder = penny % x;
        return remainder;
    }

    private boolean checkInventory(int invNum, double money1, Item item) {
        if (invNum <= 0) {
            io.printString("Sorry this item is out of inventory");
            io.printString("");
            return false;
        } else {
            return true;
        }
    }

}
