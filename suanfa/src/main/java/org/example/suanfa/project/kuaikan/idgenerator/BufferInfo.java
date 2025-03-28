//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.suanfa.project.kuaikan.idgenerator;

import java.io.Serializable;

public class BufferInfo implements Serializable {
    private static final long serialVersionUID = -6231757670030641529L;
    private long startDeltaTime;
    private long endDeltaTime;
    private long startSequence;
    private long endSequence;
    private long workerId;

    BufferInfo(long startDeltaTime, long endDeltaTime, long startSequence, long endSequence, long workerId) {
        this.startDeltaTime = startDeltaTime;
        this.endDeltaTime = endDeltaTime;
        this.startSequence = startSequence;
        this.endSequence = endSequence;
        this.workerId = workerId;
    }

    public static BufferInfoBuilder builder() {
        return new BufferInfoBuilder();
    }

    public long getStartDeltaTime() {
        return this.startDeltaTime;
    }

    public long getEndDeltaTime() {
        return this.endDeltaTime;
    }

    public long getStartSequence() {
        return this.startSequence;
    }

    public long getEndSequence() {
        return this.endSequence;
    }

    public long getWorkerId() {
        return this.workerId;
    }

    public void setStartDeltaTime(long startDeltaTime) {
        this.startDeltaTime = startDeltaTime;
    }

    public void setEndDeltaTime(long endDeltaTime) {
        this.endDeltaTime = endDeltaTime;
    }

    public void setStartSequence(long startSequence) {
        this.startSequence = startSequence;
    }

    public void setEndSequence(long endSequence) {
        this.endSequence = endSequence;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BufferInfo)) {
            return false;
        } else {
            BufferInfo other = (BufferInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getStartDeltaTime() != other.getStartDeltaTime()) {
                return false;
            } else if (this.getEndDeltaTime() != other.getEndDeltaTime()) {
                return false;
            } else if (this.getStartSequence() != other.getStartSequence()) {
                return false;
            } else if (this.getEndSequence() != other.getEndSequence()) {
                return false;
            } else {
                return this.getWorkerId() == other.getWorkerId();
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BufferInfo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $startDeltaTime = this.getStartDeltaTime();
        result = result * 59 + (int)($startDeltaTime >>> 32 ^ $startDeltaTime);
        long $endDeltaTime = this.getEndDeltaTime();
        result = result * 59 + (int)($endDeltaTime >>> 32 ^ $endDeltaTime);
        long $startSequence = this.getStartSequence();
        result = result * 59 + (int)($startSequence >>> 32 ^ $startSequence);
        long $endSequence = this.getEndSequence();
        result = result * 59 + (int)($endSequence >>> 32 ^ $endSequence);
        long $workerId = this.getWorkerId();
        result = result * 59 + (int)($workerId >>> 32 ^ $workerId);
        return result;
    }

    public String toString() {
        return "BufferInfo(startDeltaTime=" + this.getStartDeltaTime() + ", endDeltaTime=" + this.getEndDeltaTime() + ", startSequence=" + this.getStartSequence() + ", endSequence=" + this.getEndSequence() + ", workerId=" + this.getWorkerId() + ")";
    }

    public static class BufferInfoBuilder {
        private long startDeltaTime;
        private long endDeltaTime;
        private long startSequence;
        private long endSequence;
        private long workerId;

        BufferInfoBuilder() {
        }

        public BufferInfoBuilder startDeltaTime(long startDeltaTime) {
            this.startDeltaTime = startDeltaTime;
            return this;
        }

        public BufferInfoBuilder endDeltaTime(long endDeltaTime) {
            this.endDeltaTime = endDeltaTime;
            return this;
        }

        public BufferInfoBuilder startSequence(long startSequence) {
            this.startSequence = startSequence;
            return this;
        }

        public BufferInfoBuilder endSequence(long endSequence) {
            this.endSequence = endSequence;
            return this;
        }

        public BufferInfoBuilder workerId(long workerId) {
            this.workerId = workerId;
            return this;
        }

        public BufferInfo build() {
            return new BufferInfo(this.startDeltaTime, this.endDeltaTime, this.startSequence, this.endSequence, this.workerId);
        }

        public String toString() {
            return "BufferInfo.BufferInfoBuilder(startDeltaTime=" + this.startDeltaTime + ", endDeltaTime=" + this.endDeltaTime + ", startSequence=" + this.startSequence + ", endSequence=" + this.endSequence + ", workerId=" + this.workerId + ")";
        }
    }
}
