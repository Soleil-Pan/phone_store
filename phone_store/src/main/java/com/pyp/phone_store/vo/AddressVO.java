package com.pyp.phone_store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressVO {

    //使用Stream流的方式，完成从PO到VO的赋值，不需要和实体类名字对应
    //若使用BeanUtils的工具类，需要名字对应,再用@JsonProperty注释和前端对应
    private Integer id;
    private String areaCode;
    private String name;
    private String tel;
    private String address;
}
