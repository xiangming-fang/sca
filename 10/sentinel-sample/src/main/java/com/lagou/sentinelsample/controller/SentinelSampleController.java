package com.lagou.sentinelsample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelSampleController {

    /**
     * 限流降级
     * @return
     */
    @GetMapping("/test_flow_rule")
    public String testFlowRule(){
        return "SUCCESS";
    }


    /**
     * 熔断降级：慢调用比例、异常数比例、异常数
     * @return
     */
    @GetMapping("/test_fuse_rule")
    public String testFuseRule(){
        throw new RuntimeException("异常数");
    }
}
