package com.singularity.flowapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.singularity.flowapi.mapper"})
public class FlowApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowApiApplication.class, args);
    }

}
