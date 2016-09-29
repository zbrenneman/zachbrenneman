/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.dao;

import com.mycompany.addressbookweb.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoImpl implements AddressBookDao{
    
     private static final String TOKEN = "::";
    private static String FILENAME = "addresses.txt";

    private List<Address> addresses = null;
    private int nextId = 1;

    public AddressBookDaoImpl() {

        addresses = decode();

        for (Address c : addresses) {

            if (c.getId() >= nextId) {

                nextId = c.getId() + 1;
            }
        }
    }

    public Address create(Address address) {

        address.setId(nextId);

        addresses.add(address);

        nextId++;

        encode();

        return address;

    }

    public void delete(Address address) {

        ListIterator litr = addresses.listIterator();

        while (litr.hasNext()) {

            Address c = (Address) litr.next();

            if (c.getId() == address.getId()) {

                litr.remove();

            }
        }

        encode();
    }

    public void update(Address address) {

        ListIterator litr = addresses.listIterator();

        while (litr.hasNext()) {

            Address c = (Address) litr.next();

            if (c.getId() == address.getId()) {

                litr.set(address);

            }
        }

        encode();
    }

    @Override
    public Address read(Integer id) {

        for (Address c : addresses) {

            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Address> all() {

        return new ArrayList(addresses);
    }


    public void encode() {

        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(FILENAME));

            for (Address a : addresses) {

                out.print(a.getId());
                out.print(TOKEN);

                out.print(a.getFirstName());
                out.print(TOKEN);

                out.print(a.getLastName());
                out.print(TOKEN);

                out.print(a.getStreet());
                out.print(TOKEN);

                out.print(a.getCity());
                out.print(TOKEN);

                out.print(a.getState());
                out.print(TOKEN);
                
                out.print(a.getZip());
                out.println("");
            }

            out.flush();

        } catch (IOException ex) {

        } finally {
            out.close();

        }
    }


    public List<Address> decode() {
        
        List<Address> tempAddressList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Address myAddress = new Address();

                int addressId = Integer.parseInt(stringParts[0]);

                myAddress.setId(addressId);
                myAddress.setFirstName(stringParts[1]);
                myAddress.setLastName(stringParts[2]);
                myAddress.setStreet(stringParts[3]);
                myAddress.setCity(stringParts[4]);
                myAddress.setState(stringParts[5]);
                myAddress.setZip(stringParts[6]);

                tempAddressList.add(myAddress);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressBookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempAddressList;

       
    }

    
    
}
