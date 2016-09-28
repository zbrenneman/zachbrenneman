/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dao;

import com.mycompany.floormaster.dto.Tax;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface TaxDao {
    
    public Tax add(Tax tax);

    public Tax read(int id);

    public void update(Tax tax);

    public void delete(Tax tax);
    
    public List<Tax> decode();
    
}
