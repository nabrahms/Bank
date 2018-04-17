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
public class HomeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String id) {
        Home h;
        switch (Integer.parseInt(id)) {
            case 1:
                h = new Home(1);
                break;
            case 2:
                h = new Home(2);
                break;
            case 3:
                h = new Home(3);
                break;
            default:
                h = null;
        }
        this.setValue(h);
    }
}
