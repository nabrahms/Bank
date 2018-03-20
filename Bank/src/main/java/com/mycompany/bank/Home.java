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
public class Home {

    private double downPayment;
    private double mortgage;
    private double lightBill;
    private double gasBill;
    private double utiltiesBill;
    private String homeName;
    private Bills addToBill;

    public Home(int choice) {
        setValues(choice);
    }

    private void setValues(int choice) {
        switch (choice) {
        //set low tier house
            case 1:
                setHomeName("Low tier apartment");
                break;
        //set mid tier house
            case 2:
                setHomeName("Mid tier house");
                break;
        //set high tier house
            case 3:
                setHomeName("High tier mansion");
                break;
            default:
                break;
        }
        addToBill = new Bills(homeName, mortgage+lightBill+utiltiesBill+gasBill);
    }

    /**
     * @return the downPayment
     */
    public double getDownPayment() {
        return downPayment;
    }

    /**
     * @param downPayment the downPayment to set
     */
    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    /**
     * @return the mortgage
     */
    public double getMortgage() {
        return mortgage;
    }

    /**
     * @param mortgage the mortgage to set
     */
    public void setMortgage(double mortgage) {
        this.mortgage = mortgage;
    }

    /**
     * @return the lightBill
     */
    public double getLightBill() {
        return lightBill;
    }

    /**
     * @param lightBill the lightBill to set
     */
    public void setLightBill(double lightBill) {
        this.lightBill = lightBill;
    }

    /**
     * @return the gasBill
     */
    public double getGasBill() {
        return gasBill;
    }

    /**
     * @param gasBill the gasBill to set
     */
    public void setGasBill(double gasBill) {
        this.gasBill = gasBill;
    }

    /**
     * @return the utiltiesBill
     */
    public double getUtiltiesBill() {
        return utiltiesBill;
    }

    /**
     * @param utiltiesBill the utiltiesBill to set
     */
    public void setUtiltiesBill(double utiltiesBill) {
        this.utiltiesBill = utiltiesBill;
    }

    /**
     * @return the homeName
     */
    public String getHomeName() {
        return homeName;
    }

    /**
     * @param homeName the homeName to set
     */
    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    /**
     * @return the addToBill
     */
    public Bills getAddToBill() {
        return addToBill;
    }

    /**
     * @param addToBill the addToBill to set
     */
    public void setAddToBill(Bills addToBill) {
        this.addToBill = addToBill;
    }
}
