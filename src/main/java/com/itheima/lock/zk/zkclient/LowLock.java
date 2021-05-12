package com.itheima.lock.zk.zkclient;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

public class LowLock extends AbstractLock {

    private static final String LOCK_NODE="/lock_node";

    private CountDownLatch countDownLatch;

    @Override
    public boolean tryLock() {
        if (zkClient == null){
            return false;
        }

        try {
            zkClient.createEphemeral(LOCK_NODE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void waitLock() {

        //注册监听
        IZkDataListener listener = new IZkDataListener() {

            //节点数据改变触发
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            //节点删除触发
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                if (countDownLatch != null){
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(LOCK_NODE,listener);

        //如果节点存在，则线程阻塞等待
        if (zkClient.exists(LOCK_NODE)){
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName()+":  等待获取锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //节点不存在，删除监听
        zkClient.unsubscribeDataChanges(LOCK_NODE,listener);

    }

    @Override
    public void releaseLock() {
        System.out.println(Thread.currentThread().getName()+":    释放锁");
        zkClient.delete(LOCK_NODE);
        zkClient.close();

    }
}