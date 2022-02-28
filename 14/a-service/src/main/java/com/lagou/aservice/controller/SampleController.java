package com.lagou.aservice.controller;

import com.lagou.aservice.feignclient.BServiceFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@RestController
public class SampleController {
    @Resource
    private BServiceFeignClient bService;

    @GetMapping("/a")
    public String methodA(){
        String result = bService.methodB();
        result = "-> Service A" + result;
        return result;
    }
}
