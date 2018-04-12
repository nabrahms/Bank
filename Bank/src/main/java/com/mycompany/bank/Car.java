/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.io.Serializable;

/**
 *
 * @author Nick-PC
 */
public class Car {

    private double lumpSum;
    private double carBill;
    private double carInsurance;
    private double totalBill;
    private Bills addToBill;
    private String carName;
    private int choice;

    protected Car() {
    }

    public Car(int choice) {
        this.choice = choice;
        setCar(choice);
    }
    
    public Car(String carName){
        switch (carName) {
            case "Low tier car":
                setCar(1);
                break;
            case "Mid tier car":
                setCar(2);
                break;
            case "High tier car":
                setCar(3);
                break;
            default:
                break;
        }
    }

    private void setCar(int choice) {

        switch (choice) {
            case 1:
                setCarInsurance(30);
                setLumpSum(2000);
                setCarName("Low tier car");
                setCarBill(lumpSum + 100);
                break;
            case 2:
                setCarInsurance(80);
                setLumpSum(5000);
                setCarName("Mid tier car");
                setCarBill(lumpSum + 500);
                break;
            case 3:
                setCarInsurance(200);
                setLumpSum(10000);
                setCarName("High tier car");
                setCarBill(lumpSum + 1000);
                break;
            default:
                break;
        }
        totalBill = carBill + carInsurance;
        setAddToBill(new Bills(getCarName(), totalBill));
    }

    /**
     * @return the lumpSum
     */
    public double getLumpSum() {
        return lumpSum;
    }

    /**
     * @param lumpSum the lumpSum to set
     */
    public void setLumpSum(double lumpSum) {
        this.lumpSum = lumpSum;
    }

    /**
     * @return the carBill
     */
    public double getCarBill() {
        return carBill;
    }

    /**
     * @param carBill the carBill to set
     */
    public void setCarBill(double carBill) {
        this.carBill = carBill;
    }

    /**
     * @return the carInsurance
     */
    public double getCarInsurance() {
        return carInsurance;
    }

    /**
     * @param carInsurance the carInsurance to set
     */
    public void setCarInsurance(double carInsurance) {
        this.carInsurance = carInsurance;
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

    /**
     * @return the carName
     */
    public String getCarName() {
        return carName;
    }

    /**
     * @param carName the carName to set
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }
    
    public double getTotalBill(){
        return this.totalBill;
    }
    
    public void setTotalBill(double b){
        this.totalBill = b;
    }

    /**
     * @return the choice
     */
    public int getChoice() {
        return choice;
    }

    /**
     * @param choice the choice to set
     */
    public void setChoice(int choice) {
        this.choice = choice;
    }

}
