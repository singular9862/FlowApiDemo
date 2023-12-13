package com.singularity.flowapi.service.impl;

import com.singularity.flowapi.bean.entity.BillEntity;
import com.singularity.flowapi.mapper.BillStateMapper;
import com.singularity.flowapi.service.BillStateService;
import jakarta.annotation.Resource;

/**
 * @author : singularity
 * @date : 2023/12/12 19:08
 * @description : description
 */
public class BillStateServiceImpl implements BillStateService {
    @Resource
    BillStateMapper billStateMapper;
    @Override
    public BillEntity getBill(String id) {
        return billStateMapper.selectById(id);
    }
}
