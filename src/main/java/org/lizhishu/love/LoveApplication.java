package org.lizhishu.love;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSpringDataWebSupport
@MapperScan(basePackages = "org.lizhishu.love.dao")
public class LoveApplication {


    public static void main(String[] args) {
        SpringApplication.run(LoveApplication.class, args);
    }

}


