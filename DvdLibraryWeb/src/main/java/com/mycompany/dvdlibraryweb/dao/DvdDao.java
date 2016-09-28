/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DvdDao {
    
    public Dvd create(Dvd dvd);

    public Dvd read(Integer id);

    public void update(Dvd dvd);

    public void delete(Dvd dvd);
    
    public List<Dvd> all();
    
}
