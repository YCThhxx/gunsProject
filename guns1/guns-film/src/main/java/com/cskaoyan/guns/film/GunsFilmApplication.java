package com.cskaoyan.guns.film;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.cskaoyan.guns"})
@EnableDubboConfiguration
public class GunsFilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunsFilmApplication.class, args);
    }
}
