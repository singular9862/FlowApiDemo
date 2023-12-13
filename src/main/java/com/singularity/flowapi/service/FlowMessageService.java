package com.singularity.flowapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.singularity.flowapi.bean.entity.BillEntity;
import com.singularity.flowapi.bean.entity.FlowMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : singularity
 * @date : 2023/12/12 19:08
 * @description : description
 */
public interface FlowMessageService {
    List<FlowMessage> queryAllFlowMessageById(String flowId);
}
