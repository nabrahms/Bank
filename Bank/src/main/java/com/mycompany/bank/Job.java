/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Nick-PC
 */
@Entity
@Table(name = "job")
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "jobID")
    private long jobID;
    @Column(name = "jobName")
    private String jobName;
    @Column(name = "jobIncome")
    private double jobIncome;

    protected Job() {
    }

    public Job(String name, double income) {
        this.jobName = name;
        this.jobIncome = income;
    }

    public Job(String jobName) {
        
    }

    /**
     * @return the jobID
     */
    public long getJobID() {
        return jobID;
    }

    /**
     * @param jobID the jobID to set
     */
    public void setJobID(long jobID) {
        this.jobID = jobID;
    }

    /**
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * @return the jobIncome
     */
    public double getJobIncome() {
        return jobIncome;
    }

    /**
     * @param jobIncome the jobIncome to set
     */
    public void setJobIncome(double jobIncome) {
        this.jobIncome = jobIncome;
    }

    int getChoice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
