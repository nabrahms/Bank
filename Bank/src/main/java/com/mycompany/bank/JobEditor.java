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
public class JobEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String id) {
        Job j;
        switch (Integer.parseInt(id)) {
            case 1:
                j = new Job(1);
                break;
            case 2:
                j = new Job(2);
                break;
            case 3:
                j = new Job(3);
                break;
            case 4:
                j = new Job(4);
                break;
            default:
                j = null;
        }
        this.setValue(j);
    }
}
