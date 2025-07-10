package org.example.suanfa.project.HBike.agent;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EnhancedAgent {
    // 使用Map存储更复杂的知识库
    private static final Map<String, String> KNOWLEDGE = new HashMap<>();
    private static final Map<String, Runnable> ACTIONS = new HashMap<>();
    
    static {
        // 初始化知识库
        KNOWLEDGE.put("你好", "你好！我是增强版Java Agent。");
        KNOWLEDGE.put("你叫什么", "我是EnhancedAgent，比SimpleAgent更聪明哦！");
        KNOWLEDGE.put("再见", "再见！希望很快再见到你。");
        KNOWLEDGE.put("时间", "当前系统时间是: " + new java.util.Date());
        
        // 初始化动作
        ACTIONS.put("计算", () -> calculateNumbers());
        ACTIONS.put("笑话", () -> tellJoke());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("EnhancedAgent已启动，输入'再见'结束对话");
        
        while (true) {
            System.out.print("你: ");
            String input = scanner.nextLine().trim();
            
            if ("再见".equalsIgnoreCase(input)) {
                System.out.println("Agent: " + KNOWLEDGE.get("再见"));
                break;
            }
            
            processInput(input);
        }
        
        scanner.close();
    }

    private static void processInput(String input) {
        // 检查是否是动作指令
        if (ACTIONS.containsKey(input)) {
            ACTIONS.get(input).run();
            return;
        }
        
        // 检查知识库
        if (KNOWLEDGE.containsKey(input)) {
            System.out.println("Agent: " + KNOWLEDGE.get(input));
        } else {
            System.out.println("Agent: 我不确定我理解正确。我能做这些事: " + 
                             String.join(", ", KNOWLEDGE.keySet()) + 
                             " 或者执行这些动作: " + 
                             String.join(", ", ACTIONS.keySet()));
        }
    }
    
    private static void calculateNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Agent: 请输入两个数字，用空格分隔:");
        try {
            double num1 = scanner.nextDouble();
            double num2 = scanner.nextDouble();
            System.out.printf("Agent: 计算结果: 和=%.2f, 差=%.2f, 积=%.2f, 商=%.2f%n",
                            num1 + num2, num1 - num2, num1 * num2, num1 / num2);
        } catch (Exception e) {
            System.out.println("Agent: 输入无效，请输入两个数字");
        }
    }
    
    private static void tellJoke() {
        String[] jokes = {
            "为什么Java程序员总是戴眼镜？因为他们不会C#！",
            "我写代码的时候最喜欢听什么音乐？当然是Algorithm Blues！",
            "两个字节在酒吧相遇，一个说: '嗨，你还好吗？'另一个说: '抱歉，我有点走神了(bit)。'"
        };
        System.out.println("Agent: " + jokes[(int)(Math.random() * jokes.length)]);
    }
}