/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.controller;

import com.mycompany.consoleio.ConsoleIO;
import com.mycompany.floormaster.dao.OrderDao;
import com.mycompany.floormaster.dao.ProductsDao;
import com.mycompany.floormaster.dao.TaxDao;
import com.mycompany.floormaster.dto.Audit;
import com.mycompany.floormaster.dto.Order;
import com.mycompany.floormaster.dto.Products;
import com.mycompany.floormaster.dto.Tax;
import java.io.File;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FloorMasterController {

    private ConsoleIO io;

    private OrderDao oDao;

    private TaxDao tDao;

    private ProductsDao pDao;

    private Map<String, Integer> productMap = new HashMap();

    private Map<String, Integer> stateMap = new HashMap();

    public FloorMasterController(OrderDao oDao, ConsoleIO io, TaxDao tDao, ProductsDao pDao) {

        this.oDao = oDao;

        this.tDao = tDao;

        this.pDao = pDao;

        this.io = io;

    }

    public void run() {

        boolean playAgain = true;

        while (playAgain) {

            io.printString("\n" + "Please choose from the following options");
            io.printString("1. Display Orders");
            io.printString("2. Add Order");
            io.printString("3. Edit Order");
            io.printString("4. Remove Order");
            io.printString("5. Exit" + "\n");

            int choice = io.getIntInRange("", 5, 1);

            switch (choice) {

                case 1:
                    displayOrder();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    playAgain = false;
                    break;
            }
        }
    }

    private void displayOrder() {

        String date = askDate();

        List<Order> list = oDao.decode(date);

        printOrders(list);

    }

    String askDate() {

        boolean valid = true;
        String date = null;
        int count = 0;

        while (valid) {
            date = io.getDate("\n" + "Enter the date the order was placed (MMDDYYYY)");
            date = "Orders_".concat(date).concat(".txt");

            File file = new File(date);

            if (!file.exists()) {
                io.printString("\n" + "No orders for this date exist");
            } else {
                valid = false;
            }

        }

        return date;

    }

    void printOrders(List<Order> list) {

        for (Order a : list) {

            String taxRate = roundDouble(a.getTaxRate());
            String area = roundDouble(a.getArea());
            String matCost = roundDouble(a.getMaterialCost());
            String labCost = roundDouble(a.getLaborCost());
            String taxAmt = roundDouble(a.getTax());
            String totalCost = roundDouble(a.getTotal());

            io.printString("    +---------------------------+");
            io.printString("    Order #:       " + a.getOrderNumber());
            io.printString("    +---------------------------+");
            io.printString("    Name:          " + a.getCustomerName());
            io.printString("    +---------------------------+");
            io.printString("    State:         " + a.getState());
            io.printString("    +---------------------------+");
            io.printString("    Tax Rate:      " + taxRate + "%");
            io.printString("    +---------------------------+");
            io.printString("    Product Type:  " + a.getpType());
            io.printString("    +---------------------------+");
            io.printString("    Area:          " + area + " sq ft");
            io.printString("    +---------------------------+");
            io.printString("    Material Cost: $" + matCost);
            io.printString("    +---------------------------+");
            io.printString("    Labor Cost:    $" + labCost);
            io.printString("    +---------------------------+");
            io.printString("    Tax:           $" + taxAmt);
            io.printString("    +---------------------------+");
            io.printString("    Total Cost:    $" + totalCost);
            io.printString("    +---------------------------+");
            io.printString("");

        }
    }

    String roundDouble(double x) {
        double y = Math.round(x * 100.0) / 100.0;

        DecimalFormat df = new DecimalFormat("0.00");

        String s = df.format(y);

        return s;

    }

    private void addOrder() {

        String name = io.getString("What is your name?");

        printStates();
        int state = getState();

        printProducts();
        int product = getProduct();

        double area = io.getDouble("What is the area in square ft?");

        printUserInfo(name, product, state, area);

        int answer = confirmAnswers();

        if (answer == 1) {
            
            Order order = setNewOrder(state, product, name, area);

            oDao.add(order);

        }

    }

    private void printStates() {

        int count = 1;

        List<Tax> list = tDao.decode();

        for (Tax t : list) {

            io.printString(count + ". " + t.getState());

            count++;

        }

    }

    private int getState() {

        List<Tax> list = tDao.decode();

        int choice = io.getIntInRange("Choose state number", list.size(), 1);
        return choice;
    }

    private void printProducts() {

        int count = 1;

        List<Products> list = pDao.decode();

        for (Products p : list) {

            io.printString(count + ". " + p.getProductType());

            count++;

        }

    }

    private int getProduct() {

        List<Products> list = pDao.decode();

        int choice = io.getIntInRange("Choose product number", list.size(), 1);
        return choice;

    }

    Order setNewOrder(int state, int product, String name, double area) {

        Order newOrder = new Order();

        Tax tax = tDao.read(state);

        Products products = pDao.read(product);

        double matSqFt = products.getCostPerSquareFoot();

        double labSqFt = products.getLaborCostPerSquareFoot();

        double matCost = matSqFt * area;

        double labCost = labSqFt * area;

        double taxRate = tax.getTaxRate();

        newOrder.setCustomerName(name);
        newOrder.setState(tax.getState());
        newOrder.setArea(area);
        newOrder.setTaxRate(tax.getTaxRate());
        newOrder.setpType(products.getProductType());
        newOrder.setCostPerSqFt(matSqFt);
        newOrder.setLabCostPerSqFt(labSqFt);
        newOrder.setMaterialCost(matCost);
        newOrder.setLaborCost(labCost);

        double totalCost = calculateTotalCost(newOrder);
        double taxAmt = totalCost * (taxRate / 100.0);

        newOrder.setTax(taxAmt);
        newOrder.setTotal(totalCost + taxAmt);

        return newOrder;
    }

    private double calculateTotalCost(Order newOrder) {

        double area = newOrder.getArea();

        double matCost = newOrder.getCostPerSqFt();

        double laborCost = newOrder.getLabCostPerSqFt();

        double totalCost = (area * matCost) + (area * laborCost);

        return totalCost;

    }

    private int confirmAnswers() {
        int answer = io.getIntInRange("\n" + "Is this correct? \n"
                + "1. Yes \n"
                + "2. No \n", 2, 1);
        return answer;
    }

    void printUserInfo(String name, int product, int state, double area) {

        Products p = pDao.read(product);

        Tax t = tDao.read(state);

        io.printString("\n"
                + "Name:    " + name + "\n"
                + "State:   " + t.getState() + "\n"
                + "Product: " + p.getProductType() + "\n"
                + "Area:    " + area + "\n");
    }

    private void removeOrder() {

        String date = askDate();

        printCustomerNames(date);

        List<Order> list = oDao.decode(date);

        int choice = io.getIntInRange("Enter the number of the order you would like to delete", list.size(), 1);

        Order o = oDao.read(choice, date);
        
        int answer = confirmAnswers();
        
        if(answer == 1){
        
        oDao.delete(o, choice, date);
        }

    }

    void printCustomerNames(String date) {

        List<Order> list = oDao.decode(date);

        list.stream().forEach(o -> io.printString(o.getOrderNumber() + " " + o.getCustomerName()));
    }

    private void editOrder() {

        String date = askDate();

        List<Order> list = oDao.decode(date);

        printCustomerNames(date);

        int choice = io.getIntInRange("Enter the number of the order you would like to edit", list.size(), 1);

        Order o = oDao.read(choice, date);

        io.printString("Press Enter to keep field the same \n");

        String name = io.getEditString("Enter customer name (" + o.getCustomerName() + ")", o.getCustomerName());

        List<String> states = createStateList();
        String state = io.getEditState("\n" + "Enter state (" + o.getState() + ")", o.getState(), states);

        List<String> products = createProductList();
        String product = io.getEditProduct("\n" + "Enter product (" + o.getpType() + ")", o.getpType(), products);

        double area = io.getFloorArea("\n" + "Enter area (" + o.getArea() + ")", o.getArea());

        int stateNum = stateMap.get(state);

        int productNum = productMap.get(product);

        Order order = setNewOrder(stateNum, productNum, name, area);
        order.setOrderNumber(choice);

        oDao.update(order,choice, date);

    }

    List<String> createProductList() {
        List<Products> list = pDao.decode();
        List<String> products = new ArrayList();

        for (Products x : list) {

            products.add(x.getProductType());
            productMap.put(x.getProductType(), x.getProdId());
            io.printString(x.getProductType() + "\n");

        }
        return products;
    }

    List<String> createStateList() {
        List<Tax> list = tDao.decode();
        List<String> states = new ArrayList();

        for (Tax x : list) {

            states.add(x.getState());
            stateMap.put(x.getState(), x.getTaxId());
            io.printString(x.getState() + "\n");

        }
        return states;
    }

}
