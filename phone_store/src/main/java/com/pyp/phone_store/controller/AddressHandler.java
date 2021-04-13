package com.pyp.phone_store.controller;

import com.pyp.phone_store.exception.PhoneException;
import com.pyp.phone_store.from.AddressFrom;
import com.pyp.phone_store.service.AddressService;
import com.pyp.phone_store.util.ResultVOUtil;
import com.pyp.phone_store.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/address")
public class AddressHandler {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public ResultVO list(){
        return ResultVOUtil.success(addressService.findAll());
    }

    @PostMapping("/create")
    //@RequestBody 将前端传来的JSON解析成JAVA对象
    //@Valid 进行数据校验,验证失败后错误信息存在bindingResult
    public ResultVO create(@Valid @RequestBody AddressFrom addressFrom,BindingResult bindingResult){
        if(bindingResult.hasGlobalErrors()){
            log.error("【添加地址】 参数错误 ,addressFrom={}",addressFrom);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressFrom);
        return ResultVOUtil.success(null);
    }

    @CrossOrigin
    @PutMapping("/update")
    public ResultVO update(@Valid @RequestBody AddressFrom addressFrom,BindingResult bindingResult){
        if(bindingResult.hasGlobalErrors()){
            log.error("【修改地址】 参数错误 ,addressFrom={}",addressFrom);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressFrom);
        return ResultVOUtil.success(null);
    }
}
