/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dao;

import com.mycompany.floormaster.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class OrderDaoImpl implements OrderDao {

    private static final String TOKEN = ",";
    private static String FILENAME = "";

    private List<Order> orders = null;
    private int nextId = 1;

    public OrderDaoImpl() {

        Date date = Calendar.getInstance().getTime();

        DateFormat formatter = new SimpleDateFormat("MMddYYYY");
        String today = formatter.format(date);

        today = "Orders_".concat(today).concat(".txt");

        File newFile = new File(today);

        if (!newFile.exists()) {

            try {
                newFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        orders = decode(today);

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

        Date date = Calendar.getInstance().getTime();

        DateFormat formatter = new SimpleDateFormat("MMddYYYY");
        String today = formatter.format(date);

        today = "Orders_".concat(today).concat(".txt");

        encode(today, orders);

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

        encode(date, list);

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

        encode(date, list);

    }

    @Override
    public void encode(String fileName, List<Order> list) {

        OrderDaoImpl.FILENAME = fileName;

        File x = new File(FILENAME);

        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(FILENAME));

            for (Order a : list) {

                out.print(a.getOrderNumber());
                out.print(TOKEN);

                out.print(a.getCustomerName());
                out.print(TOKEN);

                out.print(a.getState());
                out.print(TOKEN);

                out.print(a.getTaxRate());
                out.print(TOKEN);

                out.print(a.getpType());
                out.print(TOKEN);

                out.print(a.getArea());
                out.print(TOKEN);

                out.print(a.getCostPerSqFt());
                out.print(TOKEN);

                out.print(a.getLabCostPerSqFt());
                out.print(TOKEN);

                out.print(a.getMaterialCost());
                out.print(TOKEN);

                out.print(a.getLaborCost());
                out.print(TOKEN);

                out.print(a.getTax());
                out.print(TOKEN);

                out.print(a.getTotal());
                out.println("");

            }

            out.flush();

        } catch (IOException ex) {

        } finally {
            out.close();

        }

    }

    public List<Order> decode(String fileName) {

        this.FILENAME = fileName;

        List<Order> tempOrderList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Order myOrder = new Order();

                int orderId = Integer.parseInt(stringParts[0]);
                double taxRate = Double.parseDouble(stringParts[3]);
                double area = Double.parseDouble(stringParts[5]);
                double matCostSqFt = Double.parseDouble(stringParts[6]);
                double labCostSqFt = Double.parseDouble(stringParts[7]);
                double matCost = Double.parseDouble(stringParts[8]);
                double labCost = Double.parseDouble(stringParts[9]);
                double taxAmt = Double.parseDouble(stringParts[10]);
                double total = Double.parseDouble(stringParts[11]);

                myOrder.setOrderNumber(orderId);

                myOrder.setCustomerName(stringParts[1]);
                myOrder.setState(stringParts[2]);
                myOrder.setTaxRate(taxRate);
                myOrder.setpType(stringParts[4]);
                myOrder.setArea(area);
                myOrder.setCostPerSqFt(matCostSqFt);
                myOrder.setLabCostPerSqFt(labCostSqFt);
                myOrder.setMaterialCost(matCost);
                myOrder.setLaborCost(labCost);
                myOrder.setTax(taxAmt);
                myOrder.setTotal(total);

                tempOrderList.add(myOrder);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempOrderList;
    }

}
