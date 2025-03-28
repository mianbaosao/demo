package org.example.suanfa.project.kuaikan.idgenerator.bufferedgen;

import java.util.ArrayList;
import java.util.List;

import lombok.ToString;

/**
 * @author chenkai_1
 * @time 2019-08-27 14:52
 */
@ToString(exclude = "ids")
public class Segment {

    private volatile int pos;
    private volatile List<Long> ids = new ArrayList<>();
    private SegmentBuffer segmentBuffer;

    public Segment(SegmentBuffer segmentBuffer) {
        this.pos = 0;
        this.segmentBuffer = segmentBuffer;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public long getMaxPos() {
        return ids.size();
    }

    public synchronized long getValue(int step) {
        if (pos < getMaxPos()) {
            long id = ids.get(pos);
            pos += step;
            return id;
        } else {
            return -1L;
        }
    }

    public long getIdle() {
        return this.getMaxPos() - pos;
    }

    public SegmentBuffer getSegmentBuffer() {
        return segmentBuffer;
    }

    public void initPos() {
        this.pos = 0;
    }

}
