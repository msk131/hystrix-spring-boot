package com.email.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@SpringBootApplication
@EnableHystrix
@RestController
public class HystrixSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixSpringBootApplication.class, args);
	}
    @RequestMapping(value = "/")
    @HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String hello() throws InterruptedException {
       Thread.sleep(3000);
       return "Welcome Hystrix";
    }
    private String fallback_hello() {
       return "Request fails. It takes long time to response";
    }
}
