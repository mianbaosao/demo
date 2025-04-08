package org.example.suanfa.project.codeVerse.excel;

import java.io.Serializable;

import lombok.Data;

import com.alibaba.excel.annotation.ExcelProperty;

@Data
public class PracticeBattleStageConfExcelDto implements Serializable {

    private static final long serialVersionUID = -3795683091561058021L;

    // 卡牌id
    @ExcelProperty(value = { "伤害值", "整数", "damage" }, index = 0)
    private Integer damage;

    // 卡牌id
    @ExcelProperty(value = { "奖励数量", "整数", "prizeCnt" }, index = 1)
    private Integer prizeCnt;
}
