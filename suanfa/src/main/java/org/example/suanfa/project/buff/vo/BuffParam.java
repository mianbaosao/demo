package org.example.suanfa.project.buff.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BuffParam {

    private String icon;         // Buff图标URL
    private String title;        // Buff名称
    private Integer duration;    // 持续时间（秒）
    private BuffVO.BuffStageConf stage;


    public BuffVO initVO(BuffParam param) {
        BuffVO vo=new BuffVO();
        vo.setIcon(this.icon);
        vo.setTitle(this.title);
        vo.setDuration(this.duration);
        vo.setStageConf1(this.stage);
        return vo;
    }
}