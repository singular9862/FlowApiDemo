package com.singularity.flowapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.singularity.flowapi.bean.entity.FlowMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : singularity
 * @date : 2023/12/12 19:10
 * @description : description
 */
@Mapper
public interface FlowMessageMapper extends BaseMapper<FlowMessage> {
}
