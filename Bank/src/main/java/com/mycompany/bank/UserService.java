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
public interface UserService{
    public User findUserByEmail(String email);
    public void saveUser(User u);
    public void updateUser(User u);

}
