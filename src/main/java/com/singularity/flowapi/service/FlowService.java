package com.singularity.flowapi.service;


import com.singularity.flowapi.bean.entity.BillEntity;

/**
 * @author : singularity
 * @date : 2023/12/12 20:48
 * @description : description
 */
public interface FlowService {

     void createBill(BillEntity bill);
     void submitBill(BillEntity bill);
     void backBill(BillEntity bill);
     void returnBill(BillEntity bill);
     void hangUp(BillEntity bill);
     void cancelHangup(BillEntity bill);


}
