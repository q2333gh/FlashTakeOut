package com.q2333.flashTakeOut;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author q2333
 * @date 2022/06/13 09:20
 **/

@Slf4j//log注解类
@SpringBootApplication
@ServletComponentScan//扫描WebFilter.创建过滤器
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        log.info("spring boot start success");//slf4j
    }
}
