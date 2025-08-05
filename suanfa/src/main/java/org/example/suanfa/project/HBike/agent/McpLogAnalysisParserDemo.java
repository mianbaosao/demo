package org.example.suanfa.project.HBike.agent;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.regex.*;

public class McpLogAnalysisParserDemo {

    public static void main(String[] args) throws Exception {
        String response = "根据您提供的数据集内容，我注意到“超时未找到图标”是与超时失败相关的关键字。以下是知识库中与超时失败相关的解决措施：\n" +
                "\n" +
                "### 异常类型：元素定位失败\n" +
                "#### 解决建议：\n" +
                "1. 检查 UI 是否实际可见；\n" +
                "2. 提高等待时间或使用更稳定的定位方式；\n" +
                "3. 确认滑动逻辑是否存在死循环。\n" +
                "\n" +
                "根据这一建议，您可以尝试以下措施来解决超时失败的问题：\n" +
                "\n" +
                "1. **检查 UI 可见性**：\n" +
                "   - 确认要定位的元素在页面上是可见的。\n" +
                "   - 检查是否由于页面加载不完整或其它原因导致元素不可见。\n" +
                "\n" +
                "2. **提高等待时间**：\n" +
                "   - 增加元素定位时的等待时间。\n" +
                "   - 使用显式等待（Explicit Wait）策略，等待特定条件满足，例如元素可被点击或元素可见。\n" +
                "\n" +
                "3. **使用更稳定的定位方式**：\n" +
                "   - 使用唯一且稳定的定位器（例如ID、CSS选择器等）来确保元素定位的准确性。\n" +
                "\n" +
                "4. **检查滑动逻辑死循环**：\n" +
                "   - 确认在滑动查找元素时是否存在无限循环导致的超时。\n" +
                "   - 设置合理的滑动次数上限或检测条件。";

        Map<String, Object> parsed = parseLLMResponse(response);

        // ✅ 用 Jackson 输出 JSON 格式到控制台
        ObjectMapper mapper = new ObjectMapper();
        String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parsed);
        System.out.println(jsonOutput);
    }

    public static Map<String, Object> parseLLMResponse(String response) {
        Map<String, Object> result = new HashMap<>();

        // 1. 提取异常类型
        Pattern typePattern = Pattern.compile("### 异常类型：(.+)");
        Matcher typeMatcher = typePattern.matcher(response);
        if (typeMatcher.find()) {
            result.put("root_cause", typeMatcher.group(1).trim());
        }

        // 2. 提取建议
        Pattern suggestionPattern = Pattern.compile("#### 解决建议：\\n(.*?)\\n\\n", Pattern.DOTALL);
        Matcher suggestionMatcher = suggestionPattern.matcher(response);
        List<String> suggestions = new ArrayList<>();
        if (suggestionMatcher.find()) {
            String block = suggestionMatcher.group(1);
            for (String line : block.split("\\n")) {
                line = line.replaceAll("^\\d+\\.\\s*", "").replaceAll("；", "").trim();
                if (!line.isEmpty()) suggestions.add(line);
            }
        }
        result.put("suggestions", suggestions);

        // 3. 提取详细步骤
        Pattern detailPattern = Pattern.compile("\\*\\*(.*?)\\*\\*：\\n\\s*-\\s+(.*?)\\n(?:\\s*-\\s+(.*?))?(?=\\n\\n|\\n\\*\\*|$)", Pattern.DOTALL);
        Matcher detailMatcher = detailPattern.matcher(response);
        List<String> details = new ArrayList<>();
        while (detailMatcher.find()) {
            details.add(detailMatcher.group(2).trim());
            if (detailMatcher.group(3) != null) {
                details.add(detailMatcher.group(3).trim());
            }
        }
        result.put("details", details);

        return result;
    }
}
