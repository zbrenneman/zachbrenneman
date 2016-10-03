/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.dao;

import com.mycompany.addressbookweb.dto.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class AddressBookDbDaoImpl implements AddressBookDao {

    private static final String SQL_INSERT_ADDRESS = "INSERT INTO address(`first_name`, `last_name`, `street`, `city`, `state`, `zip`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ADDRESS = "DELETE FROM address WHERE id = ?";
    private static final String SQL_SELECT_ADDRESS = "SELECT * FROM address WHERE id= ?";
    private static final String SQL_UPDATE_ADDRESS = "UPDATE `address` SET first_name= ?,last_name = ?, street = ?, city = ?, state = ?, zip = ? WHERE id = ?";
    private static final String SQL_SELECT_ALL_ADDRESSS = "SELECT * FROM address";
    private static final String SQL_SELECT_BY_FIRST_NAME = "SELECT * FROM address WHERE first_name = ?";
    private static final String SQL_SELECT_BY_LAST_NAME = "SELECT * FROM address WHERE last_name = ?";
    private static final String SQL_SELECT_BY_STREET = "SELECT * FROM address WHERE street = ?";
    private static final String SQL_SELECT_BY_CITY = "SELECT * FROM address WHERE city = ?";
    private static final String SQL_SELECT_BY_STATE = "SELECT * FROM address WHERE state = ?";
    private static final String SQL_SELECT_BY_ZIP = "SELECT * FROM address WHERE zip = ?";

    private JdbcTemplate jdbcTemplate;

    public AddressBookDbDaoImpl(JdbcTemplate j) {
        this.jdbcTemplate = j;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Address create(Address address) {

        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip());

        Integer newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        address.setId(newId);

        return address;
    }

    @Override
    public void delete(Address address) {

        jdbcTemplate.update(SQL_DELETE_ADDRESS, address.getId());

    }

    @Override
    public void update(Address address) {

        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip(),
                address.getId());

    }

    @Override
    public Address read(Integer id) {

        Address c = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS, new AddressMapper(), id);

        return c;
    }

    @Override
    public List<Address> all() {

        List<Address> addresses = jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSS, new AddressMapper());

        return addresses;

    }

    public List<Address> searchByFirstName(String searchTerm) {

        List<Address> addresses = jdbcTemplate.query(SQL_SELECT_BY_FIRST_NAME, new AddressMapper(), searchTerm);

        return addresses;
    }

    public List<Address> searchByLastName(String searchTerm) {

        List<Address> addresses = jdbcTemplate.query(SQL_SELECT_BY_LAST_NAME, new AddressMapper(), searchTerm);

        return addresses;
    }

    public List<Address> searchByStreet(String searchTerm) {

        List<Address> addresses = jdbcTemplate.query(SQL_SELECT_BY_STREET, new AddressMapper(), searchTerm);

        return addresses;
    }
    
    public List<Address> searchByCity(String searchTerm) {

        List<Address> addresses = jdbcTemplate.query(SQL_SELECT_BY_CITY, new AddressMapper(), searchTerm);

        return addresses;
    }
    
    public List<Address> searchByState(String searchTerm) {

        List<Address> addresses = jdbcTemplate.query(SQL_SELECT_BY_STATE, new AddressMapper(), searchTerm);

        return addresses;
    }
    
    public List<Address> searchByZip(String searchTerm) {

        List<Address> addresses = jdbcTemplate.query(SQL_SELECT_BY_ZIP, new AddressMapper(), searchTerm);

        return addresses;
    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {

            Address address = new Address();
            address.setId(rs.getInt("id"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setFirstName(rs.getString("first_name"));
            address.setLastName(rs.getString("last_name"));
            address.setStreet(rs.getString("street"));
            address.setZip(rs.getString("zip"));

            return address;

        }

    }

}
