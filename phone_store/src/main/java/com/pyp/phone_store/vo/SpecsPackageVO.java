package com.pyp.phone_store.vo;

import lombok.Data;

import java.util.Map;

@Data
public class SpecsPackageVO {
    private Map<String,String> goods;
    private SkuVO sku;
}
