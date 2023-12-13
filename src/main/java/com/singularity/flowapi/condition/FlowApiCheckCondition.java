package com.singularity.flowapi.condition;

import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.singularity.flowapi.bean.billenum.BillSateEvent;
import com.singularity.flowapi.bean.billenum.BillState;
import com.singularity.flowapi.bean.entity.BillEntity;
import com.singularity.flowapi.bean.entity.FlowMessage;
import com.singularity.flowapi.mapper.BillStateMapper;
import com.singularity.flowapi.service.FlowMessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author : singularity
 * @date : 2023/12/12 19:59
 * @description : description
 */
@Repository
public class FlowApiCheckCondition {

    @Resource
    FlowMessageService flowMessageService;

    public Condition<BillEntity> isPassAndFindNextNode(){
        return new Condition<BillEntity>() {
            @Override
            public boolean isSatisfied(BillEntity bill) {
                return  BillSateEvent.TJ.name().equals(bill.getOpinion());
            }
        };
    }
    public Condition<BillEntity> isPassAndDone(){
        return new Condition<BillEntity>() {
            @Override
            public boolean isSatisfied(BillEntity bill) {
                List<FlowMessage> flowMessages = flowMessageService.queryAllFlowMessageById(bill.getFlowId());
                return  BillSateEvent.TJ.name().equals(bill.getOpinion()) && Objects.equals(bill.getNodeId(), flowMessages.get(flowMessages.size() - 1).getFlowNode());
            }
        };
    }

    public Condition<BillEntity> isAllowReturnLastNode(){
        return new Condition<BillEntity>() {
            @Override
            public boolean isSatisfied(BillEntity bill) {
                return  BillSateEvent.THSYB.name().equals(bill.getOpinion());
            }
        };
    }

    public Condition<BillEntity> isAllowReturn(){
        return new Condition<BillEntity>() {
            @Override
            public boolean isSatisfied(BillEntity bill) {
                return  BillSateEvent.TH.name().equals(bill.getOpinion());
            }
        };
    }

    public Condition<BillEntity> isAllowHangup(){
        return new Condition<BillEntity>() {
            @Override
            public boolean isSatisfied(BillEntity bill) {
                return  BillSateEvent.GQ.name().equals(bill.getOpinion());
            }
        };
    }

    public Condition<BillEntity> isAllowCancelHangup(){
        return new Condition<BillEntity>() {
            @Override
            public boolean isSatisfied(BillEntity bill) {
                return  BillSateEvent.QXGQ.name().equals(bill.getOpinion());
            }
        };
    }

    public  Condition<BillEntity> isAllowCreateBill() {
        return new Condition<BillEntity>() {
            @Override
            public boolean isSatisfied(BillEntity bill) {
                List<FlowMessage> flowMessages = flowMessageService.queryAllFlowMessageById(bill.getFlowId());
                return !flowMessages.isEmpty();
            }
        };
    }
}

