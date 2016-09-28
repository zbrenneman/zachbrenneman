/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class DvdDaoInMemoryImpl implements DvdDao {

    private List<Dvd> dvds = new ArrayList();
    private int nextId = 1;

    
    public DvdDaoInMemoryImpl() {

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
    }

    public void delete(Dvd dvd) {

        ListIterator litr = dvds.listIterator();

        while (litr.hasNext()) {

            Dvd c = (Dvd) litr.next();

            if (c.getId() == dvd.getId()) {

                litr.remove();

            }
        }

    }

    public List<Dvd> all() {

        return new ArrayList(dvds);

    }

   
}
