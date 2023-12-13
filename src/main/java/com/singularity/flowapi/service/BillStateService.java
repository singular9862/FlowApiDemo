package com.singularity.flowapi.service;

import com.singularity.flowapi.bean.entity.BillEntity;

/**
 * @author : singularity
 * @date : 2023/12/12 19:07
 * @description : description
 */
public interface BillStateService {
    BillEntity getBill(String id);
}
