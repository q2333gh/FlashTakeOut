package com.q2333.flashTakeOut.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author q2333
 * @date 2022/06/14 14:45
 **/
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor=
                new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor
                (new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
