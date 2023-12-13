package com.singularity.flowapi.action;

import com.alibaba.cola.statemachine.Action;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.singularity.flowapi.bean.billenum.BillSateEvent;
import com.singularity.flowapi.bean.billenum.BillState;
import com.singularity.flowapi.bean.entity.BillEntity;
import com.singularity.flowapi.bean.entity.FlowMessage;
import com.singularity.flowapi.mapper.BillStateMapper;
import com.singularity.flowapi.service.impl.FlowMessageServiceImpl;
import jakarta.annotation.Resource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : singularity
 * @date : 2023/12/12 19:58
 * @description : description
 */
@Repository
public class PassAndReturnAction{
    @Resource
    FlowMessageServiceImpl flowMessageService;
    @Resource
    BillStateMapper billStateMapper;

    public Action<BillState, BillSateEvent, BillEntity> doAction() {
        return (from, to, event, ctx) -> {
            List<FlowMessage> flowMessages = flowMessageService.queryAllFlowMessageById(ctx.getFlowId());
            if ("01".equals(ctx.getNodeId()) || StringUtils.isBlank(ctx.getNodeId())) {
                throw new RuntimeException("PassAndReturnAction:[初始节点] 不允许退回上一步。");
            }
            AtomicInteger i = new AtomicInteger();
            flowMessages.forEach(flowMessage -> {
                if (flowMessage.getFlowNode().equals(ctx.getNodeId())) {
                    i.set(flowMessages.indexOf(flowMessage));
                }
            });
            UpdateWrapper<BillEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("node_id",flowMessages.get(i.get()-1)).set("bill_status",BillState.TH.name()).eq("bill_id",ctx.getBillId());
            billStateMapper.update(ctx, updateWrapper);
            System.out.println("doAction");
        };
    }
}
