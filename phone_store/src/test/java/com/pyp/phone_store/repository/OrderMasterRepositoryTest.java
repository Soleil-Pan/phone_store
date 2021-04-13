package com.pyp.phone_store.repository;

import com.pyp.phone_store.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    @Test
    void findAll(){
        List<OrderMaster> list = repository.findAll();
        for (OrderMaster orderMaster : list) {
            System.out.println(orderMaster);
        }
    }

    @Test
    void findById(){
        OrderMaster orderMaster = repository.findById("1").get();
        System.out.println(orderMaster);
    }

    @Test
    void pay(){
        OrderMaster orderMaster = repository.findById("1").get();
        orderMaster.setPayStatus(1);
        repository.save(orderMaster);
    }
}