package com.pyp.phone_store.service.impl;

import com.pyp.phone_store.entity.PhoneCategory;
import com.pyp.phone_store.entity.PhoneInfo;
import com.pyp.phone_store.entity.PhoneSpecs;
import com.pyp.phone_store.enums.ResultEnum;
import com.pyp.phone_store.exception.PhoneException;
import com.pyp.phone_store.repository.PhoneCategoryRepository;
import com.pyp.phone_store.repository.PhoneInfoRepository;
import com.pyp.phone_store.repository.PhoneSpecsRepository;
import com.pyp.phone_store.service.PhoneService;
import com.pyp.phone_store.util.PhoneUtil;
import com.pyp.phone_store.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;
    @Autowired
    private PhoneInfoRepository phoneInfoRepository;
    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Override
    public DataVO findDataVO() {
        DataVO dataVO = new DataVO();
        //类型
        List<PhoneCategory> phoneCategoryList = phoneCategoryRepository.findAll();

        //1.常规方法
//        List<PhoneCategoryVO> phoneCategoryVOList = new ArrayList<>();
//        for (PhoneCategory phoneCategory : phoneCategoryList) {
//            PhoneCategoryVO phoneCategoryVO = new PhoneCategoryVO();
//            phoneCategoryVO.setCategoryName(phoneCategory.getCategoryName());
//            phoneCategoryVO.setCategoryType(phoneCategory.getCategoryType());
//            phoneCategoryVOList.add(phoneCategoryVO);
//        }

        //2.stream流
        List<PhoneCategoryVO> phoneCategoryVOList = phoneCategoryList.stream()
                .map(e -> new PhoneCategoryVO(
                        e.getCategoryName(),
                        e.getCategoryType()
                )).collect(Collectors.toList());

        dataVO.setCategories(phoneCategoryVOList);

        //手机
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByCategoryType(phoneCategoryList.get(0).getCategoryType());
        //1.常规写法
//        List<PhoneInfoVO> phoneInfoVOList = new ArrayList<>();
//        for (PhoneInfo phoneInfo : phoneInfoList) {
//            PhoneInfoVO phoneInfoVO = new PhoneInfoVO();
//            //从PO到VO的赋值
//            BeanUtils.copyProperties(phoneInfo,phoneInfoVO);
//            phoneInfoVO.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
//            phoneInfoVOList.add(phoneInfoVO);
//        }
        //2.stream流
        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        dataVO.setPhones(phoneInfoVOList);
        return dataVO;
    }

    @Override
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType) {
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByCategoryType(categoryType);
        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        return phoneInfoVOList;
    }

    @Override
    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId) {

        SpecsPackageVO specsPackageVO = new SpecsPackageVO();
        SkuVO skuVO = new SkuVO();

        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneId).get();
        List<PhoneSpecs> phoneSpecsList = phoneSpecsRepository.findAllByPhoneId(phoneId);

        //tree,phoneSpecsCas
        List<PhoneSpecsVO> phoneSpecsVOList = new ArrayList<>();
        List<PhoneSpecsCasVO> phoneSpecsCasVOList = new ArrayList<>();
        PhoneSpecsVO phoneSpecsVO;
        PhoneSpecsCasVO phoneSpecsCasVO;
        for (PhoneSpecs phoneSpecs : phoneSpecsList) {
            phoneSpecsVO = new PhoneSpecsVO();
            phoneSpecsCasVO = new PhoneSpecsCasVO();
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsVO);
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsCasVO);
            phoneSpecsVOList.add(phoneSpecsVO);
            phoneSpecsCasVOList.add(phoneSpecsCasVO);
        }
        TreeVO treeVO= new TreeVO();
        treeVO.setV(phoneSpecsVOList);
        List<TreeVO> treeVOList = new ArrayList<>();
        treeVOList.add(treeVO);

        //从BigDecimal转到Integer，在转成String
        Integer price = phoneInfo.getPhonePrice().intValue();
        skuVO.setPrice(price+".00");
        skuVO.setStock_num(phoneInfo.getPhoneStock());
        skuVO.setTree(treeVOList);
        skuVO.setList(phoneSpecsCasVOList);
        specsPackageVO.setSku(skuVO);

        Map<String,String> goods = new HashMap<>();
        goods.put("picture",phoneInfo.getPhoneIcon());
        specsPackageVO.setGoods(goods);

        return specsPackageVO;
    }

    @Override
    public void subStock(Integer specsId, Integer quantity) {
        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(specsId).get();
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        Integer result = phoneSpecs.getSpecsStock() - quantity;
        if(result < 0){
            log.error("库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }

        //规格库存
        phoneSpecs.setSpecsStock(result);
        phoneSpecsRepository.save(phoneSpecs);

        result = phoneSpecs.getSpecsStock() - quantity;
        if(result < 0){
            log.error("库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }

        //总库存
        phoneInfo.setPhoneStock(result);
        phoneInfoRepository.save(phoneInfo);
    }
}
