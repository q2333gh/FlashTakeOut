package com.q2333.flashTakeOut.config;

import com.q2333.flashTakeOut.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

//ctrl shift - 全部折叠

/**
 * @author q2333
 * @date 2022/06/13 09:29
 **/
@Slf4j
@Configuration
public class WebMVCconfig extends WebMvcConfigurationSupport {
    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers
    (ResourceHandlerRegistry registry) {
        log.info("Begin static resource mapping");
        registry.addResourceHandler
                        ("/backend/**").
                addResourceLocations("classpath:/backend/");
        registry.addResourceHandler
                        ("/front/**").
                addResourceLocations("classpath:/front/");
    }

    /**
     * 扩展SpringMVC框架的消息转换器
     * 项目启动就会调用
     *
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //new消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter =
                new MappingJackson2HttpMessageConverter();
        //设置对象装换器,底层使用common-jackson
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将转换器添加到mvc框架转换器集合
        //0 表示最先执行
        converters.add(0, messageConverter);
        super.extendMessageConverters(converters);
    }
}
