package org.example.suanfa.project.codeVerse.judege0.mq;


public class RabbitMQIntegrationTest {
    private static final String QUEUE_NAME = "test_queue";


    public void testSendAndConsume() throws Exception {
        // 先启动消费者线程
        Thread consumerThread = new Thread(() -> {
            try {
                new RabbitMqTest().consumeMessages();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        consumerThread.start();

        // 生产者发送消息
        new RabbitMqTest().sendMessages();

        // 等待消费者完成
        consumerThread.join(5000); // 超时5秒
    }
}