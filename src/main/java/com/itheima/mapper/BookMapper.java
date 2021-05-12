package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface BookMapper extends BaseMapper<Book> {

    @Update("update tb_book set stock=stock-#{saleNum} where id = #{id}")
    void updateNoLock(@Param("id") int id, @Param("saleNum") int saleNum);

    @Update("update tb_book set stock=stock-#{saleNum} where id = #{id} and stock-#{saleNum}>=0")
    void updateMysqlConditionsLock(@Param("id") int id, @Param("saleNum") int saleNum);

    @Update("update tb_book set stock=stock-#{saleNum}, version=version+1 where id = #{id} and version=#{version}")
    void updateByVersion(@Param("id") int id, @Param("saleNum") int saleNum,@Param("version") int version);

    @Update("update tb_book set stock=stock-#{saleNum} where id = #{id}")
    void updateByZk(@Param("id") int id, @Param("saleNum") int saleNum);

    @Update("update tb_book set stock=stock-#{saleNum} where id = #{id}")
    void updateByZkCuratorLock(@Param("id") int id, @Param("saleNum") int saleNum);

    @Update("update tb_book set stock=stock-#{saleNum} where id = #{id}")
    void updateByRedisson(@Param("id") int id, @Param("saleNum") int saleNum);


}
