/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dao;

import com.mycompany.floormaster.dto.Products;
import com.mycompany.floormaster.dto.Products;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class ProductsDaoImpl implements ProductsDao {

    private static final String TOKEN = ",";
    private List<Products> products = null;

    public ProductsDaoImpl() {

        products = decode();

    }

    @Override
    public Products add(Products products) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public Products read(int id) {

        for (Products p : products) {

            if (p.getProdId() == id) {
                return p;
            }

        }

        return null;
    }

    @Override
    public void update(Products products) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Products products) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Products> decode() {

        List<Products> tempProductsList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("products.txt")));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Products myProducts = new Products();

                int productId = Integer.parseInt(stringParts[0]);

                double matCost = Double.parseDouble(stringParts[2]);

                double laborCost = Double.parseDouble(stringParts[3]);

                myProducts.setProdId(productId);
                myProducts.setProductType(stringParts[1]);
                myProducts.setCostPerSquareFoot(matCost);
                myProducts.setLaborCostPerSquareFoot(laborCost);

                tempProductsList.add(myProducts);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempProductsList;
    }

}
