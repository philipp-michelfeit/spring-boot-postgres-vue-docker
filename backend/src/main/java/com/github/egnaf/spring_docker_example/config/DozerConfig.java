package com.github.egnaf.spring_docker_example.config;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper dozer() {
        return (DozerBeanMapper)DozerBeanMapperBuilder.create().build();
    }
}
