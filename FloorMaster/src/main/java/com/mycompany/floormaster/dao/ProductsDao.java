/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dao;

import com.mycompany.floormaster.dto.Products;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ProductsDao {
    
    public Products add(Products products);

    public Products read(int id);

    public void update(Products products);

    public void delete (Products products);
    
    public List<Products> decode();
    
}
