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

    private Double jobIncome;
    private int choice;

    protected Job() {
    }

    public Job(String name, double income) {
        this.jobName = name;
        this.jobIncome = income;
    }

    public Job(String jobName) {

    }

    public Job(int choice) {
        this.choice = choice;
        setJobIncome(0.0);
        setValues(choice);
    }

    private void setValues(int choice) {
        switch (choice) {

            case 1:
                setJobName("Philosopher");
                setJobIncome(10000.0);

                break;

            case 2:
                setJobName("Electrician");
                setJobIncome(50000.0);

                break;

            case 3:
                setJobName("Programmer");
                setJobIncome(150000.0);

                break;
            case 4:
                setJobName("Astronaut");
                setJobIncome(300000.0);
                break;
            default:
                break;
        }

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
    public Double getJobIncome() {
        return jobIncome;
    }

    /**
     * @param jobIncome the jobIncome to set
     */
    public void setJobIncome(Double jobIncome) {
        this.jobIncome = jobIncome;
    }

    /**
     * @return the choice
     */
    public int getChoice() {
        return choice;
    }

    /**
     * @param choice the choice to set
     */
    public void setChoice(int choice) {
        this.choice = choice;
    }

}
