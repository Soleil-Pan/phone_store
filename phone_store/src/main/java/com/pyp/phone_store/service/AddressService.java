package com.pyp.phone_store.service;

import com.pyp.phone_store.from.AddressFrom;
import com.pyp.phone_store.vo.AddressVO;

import java.util.List;

public interface AddressService {
    public List<AddressVO> findAll();
    public void saveOrUpdate(AddressFrom addressFrom);
}
