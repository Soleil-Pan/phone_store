package com.pyp.phone_store.service;

import com.pyp.phone_store.vo.DataVO;
import com.pyp.phone_store.vo.PhoneInfoVO;
import com.pyp.phone_store.vo.SpecsPackageVO;

import java.util.List;

public interface PhoneService {
    public DataVO findDataVO();
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType);
    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId);
    public void subStock(Integer specsId,Integer quantity);
}
