/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.dao;

import com.mycompany.addressbookweb.dto.Address;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AddressBookDao {
    
     public Address create(Address address);
    public void delete(Address address);
    public void update(Address address);
    public Address read(Integer id);
    public List<Address> all();
    public List<Address> decode();
    public void encode();
    
}
