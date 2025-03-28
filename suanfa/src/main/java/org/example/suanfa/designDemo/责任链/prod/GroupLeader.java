package org.example.suanfa.designDemo.责任链.prod;

public class GroupLeader extends Approver {
    @Override
    public void processRequest(LeaveRequest request) {
        if (request.getDays() <= 1) {
            System.out.println("组长批准了 " + request.getName() + " 的 " + request.getDays() + " 天请假");
        } else if (nextApprover != null) {
            nextApprover.processRequest(request);  // 传递给下一级
        }
    }
}