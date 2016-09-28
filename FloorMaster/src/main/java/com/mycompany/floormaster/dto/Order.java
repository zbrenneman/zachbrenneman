/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dto;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author apprentice
 */
public class Order {

    private int orderNumber;
    private String customerName;
    private String state;
    private double taxRate;
    private String pType;
    private double area;
    private double matCostPerSqFt;
    private double labCostPerSqFt;
    private double materialCost;
    private double laborCost;
    private double tax;
    private double total;

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getMatCostPerSqFt() {
        return matCostPerSqFt;
    }

    public void setMatCostPerSqFt(double matCostPerSqFt) {
        this.matCostPerSqFt = matCostPerSqFt;
    }

    public double getLabCostPerSqFt() {
        return labCostPerSqFt;
    }

    public void setLabCostPerSqFt(double labCostPerSqFt) {
        this.labCostPerSqFt = labCostPerSqFt;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getCostPerSqFt() {
        return matCostPerSqFt;
    }

    public void setCostPerSqFt(double costPerSqFt) {
        this.matCostPerSqFt = costPerSqFt;
    }

    public double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(double materialCost) {
        this.materialCost = materialCost;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
