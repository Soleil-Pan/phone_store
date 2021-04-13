package com.pyp.phone_store.service.impl;

import com.pyp.phone_store.from.AddressFrom;
import com.pyp.phone_store.service.AddressService;
import com.pyp.phone_store.vo.AddressVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    @Test
    void findAll() {
        List<AddressVO> list = addressService.findAll();
        int id = 0;
    }

    @Test
    void saveOrUpdate(){
        AddressFrom addressFrom = new AddressFrom();
        /*保存信息*/
        addressService.saveOrUpdate(addressFrom);
    }


}