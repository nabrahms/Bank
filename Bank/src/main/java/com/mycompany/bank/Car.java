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
public class Car {
    private double lumpSum;
    private double carBill;
    private double carInsurance;
    private Bills addToBill;
    private String carName;
    public Car(int choice){
        setCar(choice);
    }
    
    private void setCar(int choice){
        
        switch(choice){
            case 1:
                //setLowCarRate
                setCarName("Low tier car");
                break;
            case 2:
                //setMidCarRate
                setCarName("Mid tier car");
                break;
            case 3:
                //setHighCarRate
                setCarName("High tier car");
                break;
            default:
                break;
        }
        setAddToBill(new Bills(getCarName(), carBill+carInsurance));
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
    
}
