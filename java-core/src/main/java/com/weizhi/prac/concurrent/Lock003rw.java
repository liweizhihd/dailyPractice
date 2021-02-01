package com.weizhi.prac.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther: liweizhi
 * Date: 2019/3/5 10:33
 * Description:
 */
@Slf4j
public class Lock003rw {
    private ReentrantReadWriteLock lock;
    private int count = 0;

    public Lock003rw(boolean fair) {
        this.lock = new ReentrantReadWriteLock(fair);
    }

    public void read() {
        try {
            lock.readLock().lock();
            log.info("reading in {},count={}, {}", Thread.currentThread().getName(), count, Instant.now().toEpochMilli());
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        try {
            lock.writeLock().lock();
            count++;
            log.info("writing in {},count={}, {}", Thread.currentThread().getName(), count, Instant.now().toEpochMilli());
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
