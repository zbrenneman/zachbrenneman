/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Dvd;
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
public class DvdDaoImpl implements DvdDao {

    private static final String TOKEN = "::";
    private static String FILENAME = "dvds.txt";

    private List<Dvd> dvds = null;
    private int nextId = 1;

    public DvdDaoImpl() {

        dvds = decode();

        for (Dvd a : dvds) {

            if (a.getId() >= nextId) {

                nextId = a.getId() + 1;
            }
        }
    }

    public Dvd create(Dvd dvd) {

        dvd.setId(nextId);

        dvds.add(dvd);

        nextId++;

        encode();

        return dvd;
    }

    public Dvd read(Integer id) {

        for (Dvd d : dvds) {

            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public void update(Dvd dvd) {

        ListIterator litr = dvds.listIterator();

        while (litr.hasNext()) {

            Dvd c = (Dvd) litr.next();

            if (c.getId() == dvd.getId()) {

                litr.set(dvd);

            }
        }

        encode();

    }

    public void delete(Dvd dvd) {

        ListIterator litr = dvds.listIterator();

        while (litr.hasNext()) {

            Dvd c = (Dvd) litr.next();

            if (c.getId() == dvd.getId()) {

                litr.remove();

            }
        }

        encode();
    }

    public List<Dvd> all() {

        return new ArrayList(dvds);

    }

    public void encode() {

        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(FILENAME));

            for (Dvd a : dvds) {

                out.print(a.getId());
                out.print(TOKEN);

                out.print(a.getDirector());
                out.print(TOKEN);

                out.print(a.getRating());
                out.print(TOKEN);

                out.print(a.getRelease());
                out.print(TOKEN);

                out.print(a.getStudio());
                out.print(TOKEN);

                out.print(a.getTitle());
                out.println("");
            }

            out.flush();

        } catch (IOException ex) {

        } finally {
            out.close();

        }

    }

    public List<Dvd> decode() {

        List<Dvd> tempDvdList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Dvd myDvd = new Dvd();

                int dvdId = Integer.parseInt(stringParts[0]);

                myDvd.setId(dvdId);
                myDvd.setDirector(stringParts[1]);
                myDvd.setRating(stringParts[2]);
                myDvd.setRelease(stringParts[3]);
                myDvd.setStudio(stringParts[4]);
                myDvd.setTitle(stringParts[5]);

                tempDvdList.add(myDvd);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DvdDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempDvdList;
    }

}
