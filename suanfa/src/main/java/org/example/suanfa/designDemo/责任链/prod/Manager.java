package org.example.suanfa.designDemo.责任链.prod;

public class Manager extends Approver {
    @Override
    public void processRequest(LeaveRequest request) {
        if (request.getDays() <= 3) {
            System.out.println("经理批准了 " + request.getName() + " 的 " + request.getDays() + " 天请假");
        } else if (nextApprover != null) {
            nextApprover.processRequest(request);  // 传递给下一级
        }
    }
}