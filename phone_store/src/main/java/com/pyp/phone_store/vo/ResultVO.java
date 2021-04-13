package com.pyp.phone_store.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    //使用泛型不需要转型，使用Object后取数据是需要转型
    private T data;
}
