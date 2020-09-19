package com.dxj.c2c.social.govern.reviewer;

import com.dxj.c2c.social.govern.reviewer.db.DruidDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DruidDataSourceConfig.class)
public class ReviewerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewerApplication.class, args);
    }

}
