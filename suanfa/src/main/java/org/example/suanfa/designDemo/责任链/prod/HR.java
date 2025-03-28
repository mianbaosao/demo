package org.example.suanfa.designDemo.责任链.prod;

public class HR extends Approver {
    @Override
    public void processRequest(LeaveRequest request) {
        if (request.getDays() <= 7) {
            System.out.println("HR批准了 " + request.getName() + " 的 " + request.getDays() + " 天请假");
        } else {
            System.out.println("请假天数过长，无人能处理！");
        }
    }
}