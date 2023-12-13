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
public class PassAndHangupAction{
    @Resource
    BillStateMapper billStateMapper;
    public Action<BillState, BillSateEvent, BillEntity> doAction() {
        return (from, to, event, ctx) -> {
            if ( StringUtils.isBlank(ctx.getNodeId())) {
                throw new RuntimeException("PassAndHangupAction:[未提交单据] 不允许挂起。");
            }
            UpdateWrapper<BillEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("bill_status",BillState.GQ.name()).eq("bill_id",ctx.getBillId());
            billStateMapper.update(ctx, updateWrapper);
            System.out.println("doAction");
        };
    }
}
