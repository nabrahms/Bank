/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

/**
 *
 * @author Nick-PC
 */
public class Bills {
    private long billID;
    private String billName;
    private double billCost;
    
    protected Bills(){}
    
    public Bills(String name, double cost){
        this.billName = name;
        this.billCost = cost;
    }

    /**
     * @return the billID
     */
    public long getBillID() {
        return billID;
    }

    /**
     * @param billID the billID to set
     */
    public void setBillID(long billID) {
        this.billID = billID;
    }

    /**
     * @return the billName
     */
    public String getBillName() {
        return billName;
    }

    /**
     * @param billName the billName to set
     */
    public void setBillName(String billName) {
        this.billName = billName;
    }

    /**
     * @return the billCost
     */
    public double getBillCost() {
        return billCost;
    }

    /**
     * @param billCost the billCost to set
     */
    public void setBillCost(double billCost) {
        this.billCost = billCost;
    }
}
