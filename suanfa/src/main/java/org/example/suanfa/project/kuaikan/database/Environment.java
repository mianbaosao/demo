package org.example.suanfa.project.kuaikan.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by yushixiang on 9/6/16.
 */
@Getter
@AllArgsConstructor
public enum Environment {
    LOCAL(false, 10, "本地开发环境"),
    DEV(false, 20, "联调环境"),
    STAG(false, 30, "测试环境"),
    PERF(false, 40, "压测环境"),
    PREVIEW(true, 50, "预发布正式环境"),
    PROD(true, 60, "正式环境"),
    PRODB(true, 60, "正式B集群环境"),
    PRODC(true, 60, "正式C集群环境"),;

    private boolean isProd;
    private Integer level;
    private String desc;

    public boolean isProd() {
        return this.isProd;
    }

    public boolean lt(Environment env) {
        return this.getLevel() < env.getLevel();
    }

    public boolean le(Environment env) {
        return this.getLevel() <= env.getLevel();
    }

    public boolean gt(Environment env) {
        return this.getLevel() > env.getLevel();
    }

    public boolean ge(Environment env) {
        return this.getLevel() >= env.getLevel();
    }
}
