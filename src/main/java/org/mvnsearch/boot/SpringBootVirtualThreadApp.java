package org.mvnsearch.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootVirtualThreadApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVirtualThreadApp.class, args);
    }

}
