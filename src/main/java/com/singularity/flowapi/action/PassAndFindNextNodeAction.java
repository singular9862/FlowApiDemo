package com.singularity.flowapi.action;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.StateMachine;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.singularity.flowapi.bean.entity.FlowMessage;
import com.singularity.flowapi.mapper.BillStateMapper;
import com.singularity.flowapi.service.FlowService;
import com.singularity.flowapi.service.impl.FlowMessageServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.singularity.flowapi.bean.billenum.BillSateEvent;
import com.singularity.flowapi.bean.billenum.BillState;
import com.singularity.flowapi.bean.entity.BillEntity;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : singularity
 * @date : 2023/12/12 19:58
 * @description : description
 */
@Repository
public class PassAndFindNextNodeAction {
    @Resource
    FlowMessageServiceImpl flowMessageService;
    @Resource
    BillStateMapper billStateMapper;

    public Action<BillState, BillSateEvent, BillEntity> doAction() {
        return (from, to, event, ctx) -> {
            UpdateWrapper<BillEntity> updateWrapper = new UpdateWrapper<>();
            List<FlowMessage> flowMessages = flowMessageService.queryAllFlowMessageById(ctx.getFlowId());
            if (BillState.CL.name().equals(ctx.getBillStatus())) {
                AtomicInteger i = new AtomicInteger();
                flowMessages.forEach(flowMessage -> {
                    if (flowMessage.getFlowNode().equals(ctx.getNodeId())) {
                        i.set(flowMessages.indexOf(flowMessage));
                    }
                });
                if (i.get()  < flowMessages.size() - 1) {
                    updateWrapper.set("node_id", flowMessages.get(i.get() + 1).getFlowNode()).eq("bill_id", ctx.getBillId());
                } else if (i.get()  == flowMessages.size() - 1) {
                    throw new RuntimeException("调用PassAndFindNextNodeAction出错,最大节点应调用PassAndDoneAction");
                }

            }
            billStateMapper.update(ctx, updateWrapper);
            System.out.println("doAction");
        };
    }
}
