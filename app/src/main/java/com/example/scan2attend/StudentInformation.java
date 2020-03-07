package com.example.scan2attend;

public class StudentInformation {
    public String studentDBid;
    public String name;
    public String rollNo;
    public String branch;
    public String year;
    public String sem;
    public String group;

    StudentInformation()
    {

    }

    public StudentInformation(String DBid,String name, String rollNo, String branch, String year, String sem, String group) {
        this.studentDBid=DBid;
        this.name = name;
        this.rollNo= rollNo;
        this.branch = branch;
        this.year = year;
        this.sem = sem;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getBranch() {
        return branch;
    }
    public String getYear() {
        return year;
    }
    public String getSem() {
        return sem;
    }
    public String getGroup() {
        return group;
    }
}