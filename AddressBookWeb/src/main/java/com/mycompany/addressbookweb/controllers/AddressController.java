/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.controllers;

import com.mycompany.addressbookweb.dao.AddressBookDao;
import com.mycompany.addressbookweb.dto.Address;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Address add(@Valid@RequestBody Address address) {

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
