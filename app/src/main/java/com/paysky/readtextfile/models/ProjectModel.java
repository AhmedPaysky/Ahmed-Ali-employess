package com.paysky.readtextfile.models;

public class ProjectModel {
    private String empId;
    private String projId;
    private String startDate;
    private String endDate;
    private long workDuration;

    public ProjectModel(String empId, String projId, String startDate, String endDate, long workDuration) {
        this.empId = empId;
        this.projId = projId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workDuration = workDuration;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(long workDuration) {
        this.workDuration = workDuration;
    }

    @Override
    public String toString() {
        return "ProjectModel{" +
                "empId='" + empId + '\'' +
                ", projId='" + projId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", workDuration='" + workDuration + '\'' +
                '}';
    }

}
