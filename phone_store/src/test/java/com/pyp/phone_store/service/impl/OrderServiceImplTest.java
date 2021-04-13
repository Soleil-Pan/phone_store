package com.pyp.phone_store.service.impl;

import com.pyp.phone_store.dto.OrderDTO;
import com.pyp.phone_store.service.OrderService;
import com.pyp.phone_store.vo.OrderDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerPhone("17343401441");
        orderDTO.setBuyerAddress("浙江省杭州市江干区科技路");
        orderDTO.setSpecsId(1);
        orderDTO.setPhoneQuantity(1);

        OrderDTO result = orderService.create(orderDTO);
        System.out.println(result);
    }

    @Test
    void findOrderDetailByOrderId() {
        OrderDetailVO orderDetailVO = orderService.findOrderDetailByOrderId("1");
        int id = 0;
    }

    @Test
    void pay() {
        System.out.println(orderService.pay("1613983992025491220"));
    }
}