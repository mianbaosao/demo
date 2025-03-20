package leetcode.mianbao.demo.intership.test;

import leetcode.mianbao.demo.intership.convert.CaseSourceConvert;
import leetcode.mianbao.demo.intership.po.CaseSource;
import leetcode.mianbao.demo.intership.po.CaseSourceDTO;
import leetcode.mianbao.demo.intership.po.CaseSourceOther;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransTestApplication {
    public static void main(String[] args) throws Exception {
        // 创建一个 CaseSource 对象
        CaseSource caseSource = new CaseSource();
        CaseSourceOther caseSourceOther = new CaseSourceOther();
        caseSource.setId(1);
        caseSource.setUnitName("内蒙古自治区市场监督管理局");
        caseSource.setCaseSourceId("2024年52号");
        caseSource.setRegistrant("办案人1");
        caseSource.setRegistrationDate("20240901");
        caseSource.setDefendantName("测试案件");
        caseSourceOther.setAssignee("办案人2");
        caseSourceOther.setCategory("监查");
        caseSourceOther.setSourceMethod("手工录入");

        int iterations = 1_000_000; // 迭代次数，用于测试性能

        // 手动映射时间测试
        long startManual = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            CaseSourceDTO dto = map(caseSource, caseSourceOther);
        }
        long endManual = System.currentTimeMillis();

        // MapStruct 映射时间测试
        long startMapStruct = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            CaseSourceDTO dto = CaseSourceConvert.INSTANCE.toDTO(caseSource);
            dto.setAssignee(caseSourceOther.getAssignee());
            dto.setCategory(caseSourceOther.getCategory());
            dto.setSourceMethod(caseSourceOther.getSourceMethod());
        }
        long endMapStruct = System.currentTimeMillis();

        // BeanUtils 映射时间测试
        long startBeanUtils = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            CaseSourceDTO dto = new CaseSourceDTO();
            BeanUtils.copyProperties(dto, caseSource); // Copy properties from CaseSource to DTO
            BeanUtils.copyProperties(dto, caseSourceOther); // Copy properties from CaseSourceOther to DTO
            dto.setDefendantName(caseSource.getDefendantName()); // Manually set defendantName
        }
        long endBeanUtils = System.currentTimeMillis();

        // 输出结果
        System.out.println("手动映射时间: " + (endManual - startManual) + " 毫秒");
        System.out.println("MapStruct 映射时间: " + (endMapStruct - startMapStruct) + " 毫秒");
        System.out.println("BeanUtils 映射时间: " + (endBeanUtils - startBeanUtils) + " 毫秒");
    }

    // 手动注入
    public static CaseSourceDTO map(CaseSource caseSource, CaseSourceOther caseSourceOther) {
        CaseSourceDTO dto = new CaseSourceDTO();
        dto.setId(caseSource.getId());
        dto.setUnitName(caseSource.getUnitName());
        dto.setCaseSourceId(caseSource.getCaseSourceId());
        dto.setDefendantName(caseSource.getDefendantName());
        dto.setRegistrant(caseSource.getRegistrant());
        dto.setRegistrationDate(caseSource.getRegistrationDate());
        dto.setAssignee(caseSourceOther.getAssignee());
        dto.setCategory(caseSourceOther.getCategory());
        dto.setSourceMethod(caseSourceOther.getSourceMethod());
        return dto;
    }
}
