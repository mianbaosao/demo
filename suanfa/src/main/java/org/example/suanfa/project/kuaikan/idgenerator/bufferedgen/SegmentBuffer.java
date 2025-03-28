package org.example.suanfa.project.kuaikan.idgenerator.bufferedgen;

import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenkai_1
 * @time 2019-08-27 14:39
 */
@Getter
@ToString(exclude = "segments")
public class SegmentBuffer {

    private Segment[] segments;

    private volatile int currentPos;
    private volatile boolean nextReady;
    private volatile boolean initOk;
    private final ReadWriteLock lock;
    private final AtomicBoolean threadRunning;
    private volatile long updateTimestamp;

    private int step;

    private int minStep = 100;

    public SegmentBuffer() {
        segments = new Segment[]{new Segment(this), new Segment(this)};
        currentPos = 0;
        initOk = false;
        lock = new ReentrantReadWriteLock();
        threadRunning = new AtomicBoolean(false);
        step = minStep;
    }

    public Segment[] getSegments() {
        return segments;
    }

    public Segment getCurrent() {
        return segments[currentPos];
    }

    public int nextPos() {
        return (currentPos + 1) % 2;
    }

    public void switchPos() {
        currentPos = nextPos();
    }

    public Lock rLock() {
        return lock.readLock();
    }

    public Lock wLock() {
        return lock.writeLock();
    }

    public void setNextReady(boolean nextReady) {
        this.nextReady = nextReady;
    }

    public void setInitOk(boolean initOk) {
        this.initOk = initOk;
    }

    public void setUpdateTimestamp(long updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
