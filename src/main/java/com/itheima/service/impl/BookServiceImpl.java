package com.itheima.service.impl;

import com.itheima.lock.zk.zkclient.AbstractLock;
import com.itheima.lock.zk.zkclient.HighLock;
import com.itheima.lock.redisson.RedissonLock;
import com.itheima.mapper.BookMapper;
import com.itheima.pojo.Book;
import com.itheima.service.BookService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ReentrantLock reentrantLock;

    @Value("${server.port}")
    private String port;

    @Override
    @Transactional
    public void updateStock(int id, int saleNum) {
        reentrantLock.lock();
        Book book = bookMapper.selectById(id);
        if (book.getStock() > 0) {
            bookMapper.updateNoLock(id, saleNum);
        } else {
            log.info(port+"没有库存了...");
        }
        reentrantLock.unlock();
    }

    @Override
    public void updateMysqlConditionsLock(int id, int saleNum) {
        log.info(port+"执行了...");
        bookMapper.updateMysqlConditionsLock(id, saleNum);
    }

    @Override
    public void updateByVersion(int id, int saleNum) {
        Book book = bookMapper.selectById(id);
        log.info(port+"执行了...pre"+book.getStock());
        if (book.getStock() > 0) {
            log.info(port+"执行了..."+book.getStock());
            bookMapper.updateByVersion(id, saleNum,book.getVersion());
        }
    }

    @Override
    public void updateByZk(int id, int saleNum) {
        String methodName = Thread.currentThread().getStackTrace()[0].getMethodName();
        AbstractLock lock = new HighLock("/"+methodName);
        lock.getLock();
        log.info(port+"执行了...");
        Book book = bookMapper.selectById(id);
        if (book.getStock() > 0) {
            bookMapper.updateByZk(id, saleNum);
        }
        lock.releaseLock();
    }

    @Autowired
    private InterProcessMutex lock;
    @Override
    public void updateByZkCuratorLock(int id, int saleNum) {
        try {
            lock.acquire();
            Book book = bookMapper.selectById(id);
            if (book.getStock() > 0) {
                bookMapper.updateByZkCuratorLock(id, saleNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    private RedissonLock redissonLock;
    @Override
    public void updateByRedisson(int id, int saleNum) {
        try {
            redissonLock.lock(id+"");
            log.info(port+"执行了...");
            Book book = bookMapper.selectById(id);
            if (book.getStock() > 0) {
                bookMapper.updateByRedisson(id, saleNum);
            }
            Thread.sleep(3000);
            redissonLock.unLock(id+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
