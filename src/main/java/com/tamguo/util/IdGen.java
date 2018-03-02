package com.tamguo.util;

public class IdGen
{
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long twepoch = 1288834974657L;
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceBits = 12L;
    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long lastTimestamp = -1L;
    private String suffix;
    private boolean flag;

    private static class IdGenHolder {
        private static final IdGen instance = new IdGen();
    }

    public static IdGen get(){
        return IdGenHolder.instance;
    }

    /**
     * 获取字符串拼接id
     * @param suffix    字符串
     * @param flag      true:前缀 false:后缀
     * @return
     */
    public static IdGen get(String suffix,boolean flag){
        if(suffix == null || suffix.trim().length() == 0)
            return IdGenHolder.instance;
        else{
            return new IdGen(suffix,flag);
        }
    }


    public IdGen() {
        this(0L, 0L);
    }

    public IdGen(String suffix,boolean flag){
        this.suffix = suffix;
        this.flag = flag;
    }

    public IdGen(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized String nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        long serialNumber = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;

        return (suffix == null || suffix.trim().length() == 0) ? serialNumber+"" : (flag ? (new StringBuffer()).append(suffix).append(serialNumber).toString() : (new StringBuffer()).append(serialNumber).append(suffix).toString());
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

}
