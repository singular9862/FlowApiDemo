package com.singularity.flowapi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.singularity.flowapi.bean.billenum.BillSateEvent;
import com.singularity.flowapi.bean.entity.BillEntity;
import com.singularity.flowapi.bean.entity.FlowMessage;
import com.singularity.flowapi.mapper.BillStateMapper;
import com.singularity.flowapi.service.FlowMessageService;
import com.singularity.flowapi.service.impl.FlowServiceImpl;
import com.singularity.flowapi.statemachine.MyFlowApiStateMachineConfig;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FlowApiApplicationTests {
    @Resource
    FlowServiceImpl flowService;
    @Resource
    BillStateMapper billStateMapper;
    @Test
    void contextLoads() {
        BillEntity billEntity = billStateMapper.selectById("B0000202312120001");
        billEntity.setOpinion(BillSateEvent.GQ.name());
        flowService.hangUp(billEntity);
    }

}
