package leetcode.mianbao.demo.intership.po;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Description: 案源
 * @Author:bread
 * @Date: 2024-11-04 15:41
 */
@Data
public class CaseSource {
    private int id;                  // 序号
    private String unitName;         // 单位名称
    private String caseSourceId;     // 案源登记号
    private String defendantName;        // 当事人名称
    private String registrant;
    private String registrationDate;   // 改为 String 类型

}
