package com.pyp.phone_store.vo;

import lombok.Data;

import java.util.List;

@Data
public class SkuVO {
    private List<TreeVO> tree;
    private List<PhoneSpecsCasVO> list;
    private String price;
    private Integer stock_num;
    private boolean none_sku = false;
    private boolean hide_stock = false;
}
