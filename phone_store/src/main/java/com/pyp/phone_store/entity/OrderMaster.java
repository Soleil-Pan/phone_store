package com.pyp.phone_store.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private Integer phoneId;
    private String phoneName;
    private Integer phoneQuantity;
    private String phoneIcon;
    private Integer specsId;
    private String specsName;
    private BigDecimal specsPrice;
    private BigDecimal orderAmount;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
}
