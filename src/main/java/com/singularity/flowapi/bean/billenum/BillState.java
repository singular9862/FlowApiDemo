package com.singularity.flowapi.bean.billenum;

/**
 * @author : singularity
 * @date : 2023/12/12 19:37
 * @description : 状态
 */
public enum BillState {
    CS("CS"),
    CL("CL"),
    TH("TH"),
    GQ("GQ"),
    JS("JS");

    private String name;
    BillState(String name){
        this.name = name;
    }
}
