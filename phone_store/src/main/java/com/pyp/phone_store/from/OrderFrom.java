package com.pyp.phone_store.from;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderFrom {
    @NotEmpty(message = "姓名不能为空") //字符串，集合
    private String name;
    @NotEmpty(message = "电话不能为空")
    private String tel;
    @NotEmpty(message = "地址不能为空")
    private String address;
    @NotNull(message = "规格不能为空")  //基本数据类型
    private Integer specsId;
    @NotNull(message = "数量不能为空")
    private Integer quantity;
}
