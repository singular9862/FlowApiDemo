package com.singularity.flowapi.action;

import com.alibaba.cola.statemachine.Action;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.singularity.flowapi.bean.billenum.BillSateEvent;
import com.singularity.flowapi.bean.billenum.BillState;
import com.singularity.flowapi.bean.entity.BillEntity;
import com.singularity.flowapi.mapper.BillStateMapper;
import jakarta.annotation.Resource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * @author : singularity
 * @date : 2023/12/12 19:58
 * @description : description
 */
@Repository
public class PassAndBackAction{
    @Resource
    BillStateMapper billStateMapper;
    public Action<BillState, BillSateEvent, BillEntity> doAction() {
        return (from, to, event, ctx) -> {
            UpdateWrapper<BillEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("node_id",null).set("bill_status",BillState.TH.name()).eq("bill_id",ctx.getBillId());
            billStateMapper.update(ctx, updateWrapper);
            System.out.println("doAction");
        };
    }
}
