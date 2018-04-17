/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.beans.PropertyEditorSupport;

/**
 *
 * @author Nick-PC
 */
public class CarEditor extends PropertyEditorSupport{
    @Override
    public void setAsText(String id){
        Car c;
        switch(Integer.parseInt(id)){
            case 1: c = new Car(1); break;
            case 2: c = new Car(2); break;
            case 3: c = new Car(3); break;
            default: c = null;
        }
        this.setValue(c);
    }
}
