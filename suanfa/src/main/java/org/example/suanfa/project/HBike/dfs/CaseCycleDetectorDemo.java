package org.example.suanfa.project.HBike.dfs;

import java.util.*;

public class CaseCycleDetectorDemo {

    /**
     * 用例 1 → 用例 2 → 用例 3 → 用例 1 （形成环）
     * 用例 4 → 用例 5 （无环）
     * @param args
     */
    public static void main(String[] args) {
        // 模拟用例调用关系图（caseId → 被它引用的 caseId 列表）
        Map<Integer, List<Integer>> caseGraph = new HashMap<>();
        caseGraph.put(1, Arrays.asList(2));
        caseGraph.put(2, Arrays.asList(3));
        caseGraph.put(3, Arrays.asList(1)); // 环
        caseGraph.put(4, Arrays.asList(5));
        caseGraph.put(5, Collections.emptyList());

        // 依次检查所有 caseId 是否存在环
        for (Integer caseId : caseGraph.keySet()) {
            Set<Integer> visited = new HashSet<>();
            Set<Integer> recStack = new HashSet<>();
            List<Integer> path = new ArrayList<>();
            boolean hasCycle = detectCycle(caseGraph, caseId, visited, recStack, path);
            if (hasCycle) {
                System.out.println("❗ 检测到环，起点用例: " + caseId + "，路径: " + path);
            } else {
                System.out.println("✅ 用例 " + caseId + " 无环");
            }
        }
    }

    /**
     * DFS 检测环 + 路径记录
     */
    public static boolean detectCycle(Map<Integer, List<Integer>> graph,
                                      Integer current,
                                      Set<Integer> visited,
                                      Set<Integer> recStack,
                                      List<Integer> path) {
        if (recStack.contains(current)) {
            path.add(current); // 回到环起点
            return true;
        }
        if (visited.contains(current)) {
            return false;
        }

        visited.add(current);
        recStack.add(current);
        path.add(current);

        for (Integer next : graph.getOrDefault(current, Collections.emptyList())) {
            if (detectCycle(graph, next, visited, recStack, path)) {
                return true;
            }
        }

        recStack.remove(current);
        path.remove(path.size() - 1);
        return false;
    }
}
