package com.itheima.lock.zk.zkclient;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class HighLock extends AbstractLock {

    private static String PARENT_NODE_PATH = "";

    public HighLock(String parentNodePath) {
        PARENT_NODE_PATH = parentNodePath;
    }

    //当前节点路径
    private String currentNodePath;

    //前一个节点的路径
    private String preNodePath;

    private CountDownLatch countDownLatch;

    @Override
    public boolean tryLock() {

        //判断父节点是否存在
        if (!zkClient.exists(PARENT_NODE_PATH)) {
            //父节点不存在，创建持久节点
            try {
                zkClient.createPersistent(PARENT_NODE_PATH);
            } catch (Exception e) {
            }
        }
        //创建第一个临时有序节点
        if (currentNodePath == null || "".equals(currentNodePath)) {
            //在父节点下创建临时有序节点
            currentNodePath = zkClient.createEphemeralSequential(PARENT_NODE_PATH + "/", "lock");
        }

        //不是第一个临时有序节点
        //获取父节点下的所有子节点列表
        List<String> childrenNodeList = zkClient.getChildren(PARENT_NODE_PATH);

        //因为有序号，所以进行升序排序
        Collections.sort(childrenNodeList);

        //判断是否加锁成功，当前节点是否为父节点下序号最小的节点
        if (currentNodePath.equals(PARENT_NODE_PATH + "/" + childrenNodeList.get(0))) {
            //当前节点是序号最小的节点
            return true;
        } else {
            //当前节点不是序号最小的节点，获取其前置节点，并赋值
            int length = PARENT_NODE_PATH.length();
            int currentNodeNumber = Collections.binarySearch(childrenNodeList, currentNodePath.substring(length + 1));
            preNodePath = PARENT_NODE_PATH + "/" + childrenNodeList.get(currentNodeNumber - 1);
        }

        return false;
    }

    @Override
    public void waitLock() {
        //注册监听
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(preNodePath, listener);

        //判断前置节点是否存在，存在则阻塞
        if (zkClient.exists(preNodePath)) {

            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            //删除监听
            zkClient.unsubscribeDataChanges(preNodePath, listener);
        }


    }


    @Override
    public void releaseLock() {
        zkClient.delete(currentNodePath);
        zkClient.close();
    }
}