package com.singularity.flowapi.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : singularity
 * @date : 2023/12/12 19:55
 * @description : description
 */
@Data
@TableName("base_flow_message")
public class FlowMessage implements Serializable {
    @TableId(value = "flow_id")
    private String flowId;
    private String flowName;
    private String flowNode;
    private String nodeName;
}
