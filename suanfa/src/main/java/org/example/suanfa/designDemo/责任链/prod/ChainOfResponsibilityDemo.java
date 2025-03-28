package org.example.suanfa.designDemo.责任链.prod;

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // 1. 创建审批者
        Approver groupLeader = new GroupLeader();
        Approver manager = new Manager();
        Approver hr = new HR();

        // 2. 组装责任链
        groupLeader.setNextApprover(manager);
        manager.setNextApprover(hr);

        // 3. 提交请假请求
        LeaveRequest request1 = new LeaveRequest("张三", 1);
        LeaveRequest request2 = new LeaveRequest("李四", 4);
        LeaveRequest request3 = new LeaveRequest("王五", 10);

        // 4. 处理请求
        groupLeader.processRequest(request1);  // 组长处理
        groupLeader.processRequest(request2);  // 经理处理
        groupLeader.processRequest(request3);  // HR无法处理
    }
}