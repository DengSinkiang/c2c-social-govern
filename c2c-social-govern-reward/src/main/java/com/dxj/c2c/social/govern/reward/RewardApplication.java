package com.dxj.c2c.social.govern.reward;

import com.dxj.c2c.social.govern.reward.db.DruidDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DruidDataSourceConfig.class)
public class RewardApplication {

    public static void main(String[] args) {
        SpringApplication.run(RewardApplication.class, args);
    }

}
