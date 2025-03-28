package org.example.suanfa.designDemo.责任链.prod;

public class LeaveRequest {
    private String name;
    private int days;

    public LeaveRequest(String name, int days) {
        this.name = name;
        this.days = days;
    }

    // Getter方法
    public String getName() { return name; }
    public int getDays() { return days; }
}