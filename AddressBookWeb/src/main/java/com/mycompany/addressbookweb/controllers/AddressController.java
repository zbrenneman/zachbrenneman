/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.controllers;

import com.mycompany.addressbookweb.dao.AddressBookDao;
import com.mycompany.addressbookweb.dto.Address;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/address")
public class AddressController {

    private AddressBookDao addressDao;

    @Inject
    public AddressController(AddressBookDao addressDao) {
        this.addressDao = addressDao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Address show(@PathVariable("id") Integer addressId) {

        Address a = addressDao.read(addressId);

        return a;
    }

//    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
//    public String edit(@PathVariable("id") Integer addressId, Map model) {
//
//        Address c = addressDao.read(addressId);
//
//        model.put("address", c);
//
//        return "edit";
//    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer addressId, Map model) {

        Address c = addressDao.read(addressId);

        model.put("address", c);

        return "delete";

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Address add(@RequestBody Address address) {

        Address a = addressDao.create(address);

        return a;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteSubmit(@RequestBody Address address) {

        addressDao.delete(address);


    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Address editSubmit(@RequestBody Address address) {

        addressDao.update(address);

        return address;

    }

}
