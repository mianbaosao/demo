package org.example.suanfa.project.HBike.dfs;

import java.util.*;

public class CycleDetection {

    // 对象引用关系
    private Map<Integer, List<Integer>> objectReferences;

    public CycleDetection() {
        objectReferences = new HashMap<>();
    }

    // 添加对象引用
    public void addObjectReference(int objectId, List<Integer> references) {
        objectReferences.put(objectId, references);
    }

    // 获取当前对象的所有引用字段
    public List<Integer> getReferences(int current) {
        return objectReferences.getOrDefault(current, Collections.emptyList());
    }

    // 检测环形引用
    private boolean detectCycle(Integer current, Set<Integer> visited) {
        if (current == null) {
            return false;
        }

        // 如果对象已被访问过，说明存在环形引用
        if (visited.contains(current)) {
            return true;
        }

        // 标记当前对象为已访问
        visited.add(current);

        // 获取当前对象的所有引用字段
        for (Integer reference : getReferences(current)) {
            if (detectCycle(reference, visited)) {
                return true;
            }
        }

        // 回溯（可选，取决于具体需求）
        visited.remove(current);

        return false;
    }

    // 检测是否存在环形引用
    public boolean hasCycle() {
        Set<Integer> allObjects = new HashSet<>(objectReferences.keySet());
        Set<Integer> visited = new HashSet<>();

        for (Integer objectId : allObjects) {
            if (!visited.contains(objectId) && detectCycle(objectId, visited)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CycleDetection cd = new CycleDetection();

        // 添加对象引用关系
        cd.addObjectReference(1, Arrays.asList(2));
        cd.addObjectReference(2, Arrays.asList(3));
        cd.addObjectReference(3, Arrays.asList(1)); // 形成环

        cd.addObjectReference(4, Arrays.asList(5));
        cd.addObjectReference(5, Arrays.asList(6));
        cd.addObjectReference(6, Collections.emptyList()); // 无环

        // 检测环形引用
        boolean hasCycle = cd.hasCycle();
        System.out.println("是否存在环形引用: " + hasCycle); // 输出：是否存在环形引用: true
    }
}