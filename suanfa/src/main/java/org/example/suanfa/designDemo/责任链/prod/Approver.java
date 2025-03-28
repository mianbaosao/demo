package org.example.suanfa.designDemo.责任链.prod;

public abstract class Approver {
    protected Approver nextApprover;  // 责任链中的下一个处理者

    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    // 处理请求的方法
    public abstract void processRequest(LeaveRequest request);
}