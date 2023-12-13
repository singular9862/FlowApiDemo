package com.singularity.flowapi.service.impl;

import com.alibaba.cola.statemachine.StateMachine;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.singularity.flowapi.bean.billenum.BillSateEvent;
import com.singularity.flowapi.bean.billenum.BillState;
import com.singularity.flowapi.bean.entity.BillEntity;
import com.singularity.flowapi.bean.entity.FlowMessage;
import com.singularity.flowapi.mapper.BillStateMapper;
import com.singularity.flowapi.service.FlowMessageService;
import com.singularity.flowapi.service.FlowService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : singularity
 * @date : 2023/12/12 20:48
 * @description : description
 */
@Slf4j
@Service
public class FlowServiceImpl implements FlowService {
    @Resource
    StateMachine<BillState, BillSateEvent, BillEntity> flowApiStateMachine;
    @Resource
    FlowMessageServiceImpl flowMessageService;
    @Override
    public void createBill(BillEntity bill) {
         log.info("订单"+bill.getBillId()+"已成功更新状态为："+flowApiStateMachine.fireEvent(BillState.valueOf(bill.getBillStatus()), BillSateEvent.CJ, bill));
    }

    @Override
    public void submitBill(BillEntity bill) {
        List<FlowMessage> flowMessages = flowMessageService.queryAllFlowMessageById(bill.getFlowId());
        if (flowMessages.get(flowMessages.size()-1).getFlowNode().equals(bill.getNodeId())) {
            log.info("订单"+bill.getBillId()+"已成功更新状态为："+flowApiStateMachine.fireEvent(BillState.valueOf(bill.getBillStatus()), BillSateEvent.TJJS, bill));
        }else {
            log.info("订单"+bill.getBillId()+"已成功更新状态为："+flowApiStateMachine.fireEvent(BillState.valueOf(bill.getBillStatus()), BillSateEvent.TJ, bill));
        }

    }

    @Override
    public void backBill(BillEntity bill) {
        log.info("订单"+bill.getBillId()+"已成功更新状态为："+flowApiStateMachine.fireEvent(BillState.valueOf(bill.getBillStatus()), BillSateEvent.TH, bill));
    }

    @Override
    public void returnBill(BillEntity bill) {
        log.info("订单"+bill.getBillId()+"已成功更新状态为："+flowApiStateMachine.fireEvent(BillState.valueOf(bill.getBillStatus()), BillSateEvent.THSYB, bill));
    }

    @Override
    public void hangUp(BillEntity bill) {
        log.info("订单"+bill.getBillId()+"已成功更新状态为："+flowApiStateMachine.fireEvent(BillState.valueOf(bill.getBillStatus()), BillSateEvent.GQ, bill));
    }

    @Override
    public void cancelHangup(BillEntity bill) {
        log.info("订单"+bill.getBillId()+"已成功更新状态为："+flowApiStateMachine.fireEvent(BillState.valueOf(bill.getBillStatus()), BillSateEvent.QXGQ, bill));
    }


}
