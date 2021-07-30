package com.gtzn.digitcard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableCaching
@SpringBootApplication
@MapperScan("com.gtzn.digitcard.dao")//将项目中对应的mapper类的路径加进来就可以了
@EnableScheduling
public class DigitcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitcardApplication.class, args);
    }

}
