package com.singularity.flowapi.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * @author : singularity
 * @date : 2023/12/12 19:48
 * @description : description
 */
@Data
@TableName("bill_base_message")
public class BillEntity implements Serializable {
    @TableId(value = "bill_id")
    private String billId;
    private String billTitle;
    private String billStatus;
    private String flowId;
    private String nodeId;
    private String opinion;
}
