package com.singularity.flowapi.bean.billenum;

/**
 * @author : singularity
 * @date : 2023/12/12 19:39
 * @description : 状态事件
 */
public enum BillSateEvent {

    CJ("创建"),
    TJ("提交"),
    TJJS("提交结束"),
    TH("退回"),
    GQ("挂起"),
    CXFQ("重新发起"),
    QXGQ("取消挂起"),
    THSYB("退回上一步")
    ;
    private String name;
    BillSateEvent(String name){
        this.name = name;
    }
}
