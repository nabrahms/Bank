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

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User u) {
        System.out.println(u.getEmail() + " " + u.getPassword());
        u.setPassword(encoder.encode(u.getPassword()));
        u.setActive(1);
        u.setCreditScore(650);
        u.setMoney(0);

        Role role = roleRepository.findByRole("ADMIN");
        u.setRoles(new HashSet<>(Arrays.asList(role)));

        userRepository.save(u);

    }

}
