package org.example.suanfa.project.codeVerse.judege0.mq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.annotations.Test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitMqTest {

    // 消息队列名称
    private final static String QUEUE_NAME = "hello";

    @Test
    public void sendMessages() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("admin");
        factory.setPassword("admin");

        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {

            // 声明一个持久化的队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            for (int i = 0; i < 10; i++) {
                String message = "消息-" + i + " 时间戳: " + System.currentTimeMillis();

                // 设置消息为持久化存储
                AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                        .deliveryMode(2)  // 2表示持久化
                        .build();

                channel.basicPublish("", QUEUE_NAME, props, message.getBytes());
                System.out.println(" [x] 已发送: " + message);

                Thread.sleep(500); // 每条消息间隔500毫秒
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void consumeMessages() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 确认队列存在
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        System.out.println(" [*] 等待消息中...");

        // 用计数器记录消费的消息数
        AtomicInteger messageCount = new AtomicInteger(0);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.printf(" [x] 收到消息: %s (投递标签: %d)%n", message, envelope.getDeliveryTag());

                // 模拟处理消息的业务逻辑
                try {
                    processMessage(message);
                } finally {
                    // 手动确认消息已处理
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    if (messageCount.incrementAndGet() >= 10) {
                        synchronized (messageCount) {
                            messageCount.notify(); // 通知主线程消费完成
                        }
                    }
                }
            }
        };

        // 禁用自动确认，改用手动确认
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 等收到10条消息才结束
        synchronized (messageCount) {
            while (messageCount.get() < 10) {
                try {
                    messageCount.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        channel.close();
        connection.close();
    }

    private void processMessage(String message) {
        // 模拟处理消息的逻辑
        System.out.println(" [√] 正在处理: " + message);
    }
}