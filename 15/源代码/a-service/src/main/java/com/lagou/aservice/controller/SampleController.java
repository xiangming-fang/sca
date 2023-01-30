package com.lagou.aservice.controller;

import com.lagou.aservice.feignclient.BServiceFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@Slf4j
public class SampleController {
    @Resource
    private BServiceFeignClient bService;

    @GetMapping("/a")
    public String methodA() {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = new Random().nextInt(100);
        if (i == 1) {
            throw new RuntimeException("Nothing!");
        }
        log.error("this is error");
        String result = bService.methodB();
        result = "-> Service A" + result;
        return result;
    }
}
