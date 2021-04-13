package com.pyp.phone_store.repository;

import com.pyp.phone_store.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BuyerAddressRepositoryTest {

    @Autowired
    private BuyerAddressRepository repository;

    @Test
    void findAll(){
        List<BuyerAddress> list = repository.findAll();
        for (BuyerAddress buyerAddress : list) {
            System.out.println(buyerAddress);
        }
    }

    @Test
    void save(){
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setAreaCode("330104");
        buyerAddress.setBuyerAddress("浙江省杭州市江干区科技路");
        buyerAddress.setBuyerName("小红");
        buyerAddress.setBuyerPhone("17343401441");
        repository.save(buyerAddress);
    }

    @Test
    void update() {
        BuyerAddress buyerAddress = repository.findById(2).get();
        buyerAddress.setBuyerName("李四");
        repository.save(buyerAddress);
    }
}