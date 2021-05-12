package com.itheima.service;

public interface BookService {

    /**
     * @param id
     * @param saleNum 销售数量
     */
    void updateStock(int id, int saleNum);

    void updateMysqlConditionsLock(int id, int saleNum);

    void updateByVersion(int id, int saleNum);


    void updateByZk(int id, int saleNum);

    void updateByZkCuratorLock(int id, int saleNum);

    void updateByRedisson(int id, int saleNum);

}
