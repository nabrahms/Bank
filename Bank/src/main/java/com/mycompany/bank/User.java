/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;

/**
 *
 * @author Nick-PC
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @Column(name = "name")
    @Length(min = 2, message = "*Your name must have at least 2 characters")
    @NotEmpty(message = "*Please provide a name")
    private String name;
    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet;
    @Column(name="money")
    private double money;
    @Column(name="creditScore")
    private int creditScore;
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="jobIncome")
    //@JoinTable(name= "bill", joinColumns = @JoinColumn(name = "bill_amount"))
   // private Set<Job> job = new HashSet<>();
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="billAmount")
    //private Set<Bills> bill = new HashSet<>();

    protected User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(String p, String e, String n, int a) {

        this.password = p;
        this.email = e;
        this.name = n;
        this.active = a;
        this.money = 0;
        this.creditScore = 650;
       // this.job = null;
        //this.bill = null;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return getRoleSet();
    }

    public void setRoles(Set<Role> roles) {
        this.setRoleSet(roles);
    }

    /**
     * @return the roleSet
     */
    public Set<Role> getRoleSet() {
        return roleSet;
    }

    /**
     * @param roleSet the roleSet to set
     */
    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    /**
     * @return the money
     */
    public double getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * @return the creditScore
     */
    public int getCreditScore() {
        return creditScore;
    }

    /**
     * @param creditScore the creditScore to set
     */
    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
    
   /* public Bills getBill(){
        return this.bill;
    }
    public Job getJob(){
        return this.job;
    }*/
}
