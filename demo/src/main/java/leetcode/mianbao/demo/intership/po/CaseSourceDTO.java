package leetcode.mianbao.demo.intership.po;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Description:
 * @Author:bread
 * @Date: 2024-11-04 15:50
 */
@Data
public class CaseSourceDTO {
    private int id;                        // 序号，对应案件的唯一标识
    private String unitName;               // 单位名称，表示发起案件的单位或机构名称
    private String caseSourceId;           // 案源登记号，案件来源的唯一登记编号
    private String defendantName;          // 当事人名称，对应 `CaseSource` 中的 `partyName`
    private String registrant;             // 登记人，负责案件登记的工作人员名称
    private String registrationDate;       // 登记日期，记录案件登记的日期，字符串类型
    private String assignee;               // 流转人，负责案件下一步处理的人员名称
    private String category;               // 分类，案件类型或分类标签
    private String sourceMethod;           // 来源方式，案件来源方式（如手工录入、系统生成等）

}
