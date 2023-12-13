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
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : singularity
 * @date : 2023/12/12 19:58
 * @description : description
 */
@Repository
public class PassAndDoneAction {
    @Resource
    FlowMessageServiceImpl flowMessageService;
    @Resource
    BillStateMapper billStateMapper;
    public Action<BillState, BillSateEvent, BillEntity> doAction() {
        return (from, to, event, ctx) -> {
            UpdateWrapper<BillEntity> updateWrapper = new UpdateWrapper<>();
            List<FlowMessage> flowMessages = flowMessageService.queryAllFlowMessageById(ctx.getFlowId());
            updateWrapper.set("bill_status", BillState.JS.name()).eq("bill_id", ctx.getBillId());
            updateWrapper.set("node_id", flowMessages.get(flowMessages.size()-1).getFlowNode()).eq("bill_id", ctx.getBillId());
            billStateMapper.update(ctx, updateWrapper);
            System.out.println("doAction");
        };
    }
}
