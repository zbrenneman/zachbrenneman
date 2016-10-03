/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.controllers;

import com.mycompany.addressbookweb.dao.AddressBookDao;
import com.mycompany.addressbookweb.dto.Address;
import com.mycompany.addressbookweb.dto.SearchObject;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

    private AddressBookDao dao;

    @Inject
    public SearchController(AddressBookDao addressDao) {
        this.dao = addressDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String searchHome(Map model) {

        model.put("searchObject", new SearchObject());

        return "search";

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public SearchObject getSearch(@RequestBody SearchObject so) {

        String keyword = so.getKeyword();

        List<Address> addresses;

        switch (keyword) {

            case "firstName":

                addresses = dao.searchByFirstName(so.getSearchTerm());

                so.setAddresses(addresses);

                break;

            case "lastName":

                addresses = dao.searchByLastName(so.getSearchTerm());

                so.setAddresses(addresses);

                break;
                
            case "street":
                
                addresses = dao.searchByStreet(so.getSearchTerm());

                so.setAddresses(addresses);

                break;
                
            case "city":
                
                addresses = dao.searchByCity(so.getSearchTerm());

                so.setAddresses(addresses);

                break;
                
            case "state":
                
                addresses = dao.searchByState(so.getSearchTerm());

                so.setAddresses(addresses);

                break;
                
            case "zip":
                
                addresses = dao.searchByZip(so.getSearchTerm());

                so.setAddresses(addresses);

                break;

        }

        return so;

    }

}
