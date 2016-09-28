/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dao;

import com.mycompany.floormaster.dto.Tax;
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
public class TaxDaoImpl implements TaxDao {

    private static final String TOKEN = ",";
    private List<Tax> taxes = null;

    public TaxDaoImpl() {

        taxes = decode();

    }

    @Override
    public Tax add(Tax tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax read(int id) {
        
       for(Tax t : taxes){
           
           if(t.getTaxId() == id)
               return t;
           
       }
       return null;
        
    }

    @Override
    public void update(Tax tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Tax tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Tax> decode() {

        List<Tax> tempTaxList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("tax.txt")));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Tax myTax = new Tax();

                int taxId = Integer.parseInt(stringParts[0]);

                double taxRate = Double.parseDouble(stringParts[2]);

                myTax.setTaxId(taxId);
                myTax.setState(stringParts[1]);
                myTax.setTaxRate(taxRate);

                tempTaxList.add(myTax);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaxDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempTaxList;
    }

}
