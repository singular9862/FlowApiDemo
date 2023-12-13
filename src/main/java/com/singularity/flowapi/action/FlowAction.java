package com.singularity.flowapi.action;

import com.alibaba.cola.statemachine.Action;
import com.singularity.flowapi.bean.billenum.BillSateEvent;
import com.singularity.flowapi.bean.billenum.BillState;
import com.singularity.flowapi.bean.entity.BillEntity;
import org.springframework.core.annotation.Order;

/**
 * @author : singularity
 * @date : 2023/12/12 19:57
 * @description : 暂时废弃该写法
 */
public interface FlowAction<BillState, BillSateEvent, BillEntity> {
    Action<com.singularity.flowapi.bean.billenum.BillState, com.singularity.flowapi.bean.billenum.BillSateEvent, com.singularity.flowapi.bean.entity.BillEntity> doAction();
}
