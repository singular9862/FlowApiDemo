package com.singularity.flowapi.statemachine;

import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import com.singularity.flowapi.action.*;
import com.singularity.flowapi.bean.billenum.BillSateEvent;
import com.singularity.flowapi.bean.billenum.BillState;
import com.singularity.flowapi.bean.entity.BillEntity;
import com.singularity.flowapi.condition.FlowApiCheckCondition;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : singularity
 * @date : 2023/12/12 19:34
 * @description : 状态机配置类
 */
@Configuration
public class MyFlowApiStateMachineConfig {
    @Resource
    FlowApiCheckCondition flowApiCheckCondition;
    @Resource
    FindFirstNodeAction findFirstNodeAction;
    @Resource
    PassAndFindNextNodeAction passAndFindNextNodeAction;
    @Resource
    PassAndDoneAction passAndDoneAction;
    @Resource
    PassAndBackAction passAndBackAction;
    @Resource
    PassAndReturnAction passAndReturnAction;
    @Resource
    PassAndHangupAction passAndHangupAction;
    @Resource
    PassAndCancelHangupAction passAndCancelHangupAction;

    @Bean
    public StateMachine<BillState, BillSateEvent, BillEntity> billEntityFlowApiStateMachine(){
        String machineId = "FlowApiStateMachine_0001";
        //参照test的样例实例化一个状态机
        StateMachineBuilder<BillState,BillSateEvent,BillEntity> builder = StateMachineBuilderFactory.create();
        //创建
        builder.externalTransition().from(BillState.CS).to(BillState.CL).on(BillSateEvent.CJ).when(flowApiCheckCondition.isAllowCreateBill()).perform(findFirstNodeAction.doAction());
        //通过
        builder.internalTransition().within(BillState.CL).on(BillSateEvent.TJ).when(flowApiCheckCondition.isPassAndFindNextNode()).perform(passAndFindNextNodeAction.doAction());
        //结束
        builder.externalTransition().from(BillState.CL).to(BillState.JS).on(BillSateEvent.TJJS).when(flowApiCheckCondition.isPassAndDone()).perform(passAndDoneAction.doAction());
        //退回
        builder.externalTransition().from(BillState.CL).to(BillState.TH).on(BillSateEvent.TH).when(flowApiCheckCondition.isAllowReturn()).perform(passAndBackAction.doAction());
        //退回上一步
        builder.internalTransition().within(BillState.CL).on(BillSateEvent.THSYB).when(flowApiCheckCondition.isAllowReturnLastNode()).perform(passAndReturnAction.doAction());
        //挂起
        builder.externalTransition().from(BillState.CL).to(BillState.GQ).on(BillSateEvent.GQ).when(flowApiCheckCondition.isAllowHangup()).perform(passAndHangupAction.doAction());
        //取消挂起
        builder.externalTransition().from(BillState.GQ).to(BillState.CL).on(BillSateEvent.QXGQ).when(flowApiCheckCondition.isAllowCancelHangup()).perform(passAndCancelHangupAction.doAction());
        //重新提起
        builder.externalTransition().from(BillState.TH).to(BillState.CL).on(BillSateEvent.CJ).when(flowApiCheckCondition.isAllowCreateBill()).perform(findFirstNodeAction.doAction());

        StateMachine<BillState, BillSateEvent, BillEntity> stateMachine = builder.build(machineId);
//        System.out.println(stateMachine.generatePlantUML());
        return stateMachine;
    }





}
