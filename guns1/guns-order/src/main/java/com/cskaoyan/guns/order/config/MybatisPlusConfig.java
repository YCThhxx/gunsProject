package com.cskaoyan.guns.order.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author cskaoyan
 * @Date 2017年8月23日12:51:41
 */
@Configuration
@MapperScan(basePackages = {"com.cskaoyan.guns.order.*.dao", "com.cskaoyan.guns.order.common.persistence.dao"})
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
