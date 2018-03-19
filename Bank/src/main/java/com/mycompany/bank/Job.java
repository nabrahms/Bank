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
public class Job {
    private long jobID;
    private String jobName;
    private double jobIncome;
    
    protected Job(){}
    
    public Job(String name, double income){
        this.jobName = name;
        this.jobIncome = income;
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
    
}
