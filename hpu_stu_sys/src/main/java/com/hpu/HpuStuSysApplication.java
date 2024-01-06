package com.hpu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
// mapper扫描
@MapperScan("com.hpu.mapper")
public class HpuStuSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(HpuStuSysApplication.class, args);
    }

}
