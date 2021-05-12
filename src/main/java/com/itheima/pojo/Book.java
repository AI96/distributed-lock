package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_book")
public class Book {

    // 图书ID
    @TableId
    private Integer id;
    // 图书名称
    private String name;

    //数量
    private Integer stock;

    private Integer version;
}
