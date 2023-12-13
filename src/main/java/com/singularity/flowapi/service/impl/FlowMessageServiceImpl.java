package com.singularity.flowapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.singularity.flowapi.bean.entity.FlowMessage;
import com.singularity.flowapi.mapper.FlowMessageMapper;
import com.singularity.flowapi.service.FlowMessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : singularity
 * @date : 2023/12/12 19:09
 * @description : description
 */
@Service
public class FlowMessageServiceImpl  implements FlowMessageService {
    @Resource
    FlowMessageMapper flowMessageMapper;
    @Override
    public List<FlowMessage> queryAllFlowMessageById(String flowId) {
        QueryWrapper<FlowMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flow_id",flowId).orderByAsc("flow_node");
        List<FlowMessage> flowMessages = flowMessageMapper.selectList(queryWrapper);
        if (flowMessages.isEmpty()) {
            return  new ArrayList<FlowMessage>();
        }
        return flowMessages;
    }
}
