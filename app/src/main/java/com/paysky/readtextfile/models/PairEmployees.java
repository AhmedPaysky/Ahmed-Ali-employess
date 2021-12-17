package com.paysky.readtextfile.models;

public class PairEmployees {
    private String empId1;
    private String empId2;
    private String projId;
    private String workDuration;

    public PairEmployees(String empId1, String empId2, String projId, String  workDuration) {
        this.empId1 = empId1;
        this.empId2 = empId2;
        this.projId = projId;
        this.workDuration = workDuration;
    }

    public String getEmpId1() {
        return empId1;
    }

    public void setEmpId1(String empId1) {
        this.empId1 = empId1;
    }

    public String getEmpId2() {
        return empId2;
    }

    public void setEmpId2(String empId2) {
        this.empId2 = empId2;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(String workDuration) {
        this.workDuration = workDuration;
    }

    @Override
    public String toString() {
        return "PairEmployees{" +
                "empId1='" + empId1 + '\'' +
                ", empId2='" + empId2 + '\'' +
                ", projId='" + projId + '\'' +
                ", workDuration='" + workDuration + '\'' +
                '}';
    }
}
