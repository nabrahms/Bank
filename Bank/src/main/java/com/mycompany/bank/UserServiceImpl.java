/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nick-PC
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    private Bank bank;

    @Override
    public User findUserByEmail(String email) {
        
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User u) {
        
        u.setPassword(encoder.encode(u.getPassword()));
        u.setActive(1);
        u.setCreditScore(650);
        u.setMoney(0);

        Role role = roleRepository.findByRole("ADMIN");
        u.setRoles(new HashSet<>(Arrays.asList(role)));
        try(Connection conn = bank.connect();
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO"
                        + " user (active, bill, credit_score, email, job, money, name, password) values (?, ?, ?, ?, ?, ?, ?, ?)")){
            
            pstmt.setInt(1, 1);
            pstmt.setDouble(2, 0);
            pstmt.setInt(3, 650);
            pstmt.setString(4, u.getEmail());
            pstmt.setDouble(5, 0);
            pstmt.setDouble(6, 0);
            pstmt.setString(7, u.getName());
            pstmt.setString(8, u.getPassword());
            pstmt.executeUpdate();
            conn.commit();
            userRepository.save(u);
            
        }catch(SQLException e){
             System.out.println(e.getMessage());
        }
        
    }

}
