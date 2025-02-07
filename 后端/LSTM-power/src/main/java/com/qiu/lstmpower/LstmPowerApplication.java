package com.qiu.lstmpower;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qiu.lstmpower.Dao")
public class LstmPowerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LstmPowerApplication.class, args);
    }

}
