package com.lagou.sentinelsample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SentinelSampleController {

    /**
     * 限流降级 - 流控效果：快速失败
     * @return
     */
    @GetMapping("/test_flow_rule_quick_fail")
    public String test_flow_rule_quick_fail(){
        return "流控效果：快速失败";
    }

    /**
     * 限流降级 - 流控效果：warm-up
     * @return
     */
    @GetMapping("/test_flow_rule_warm_up")
    public String test_flow_rule_warm_up(){
        return "流控效果：warm-up";
    }

    /**
     * 限流降级 - 流控效果：排队等待
     * @return
     */
    @GetMapping("/test_flow_rule_wait_in_line")
    public String test_flow_rule_wait_in_line(){
        return "流控效果：排队等待";
    }


    /**
     * 熔断策略：慢调用比例
     * @return
     */
    @GetMapping("/test_fuse_rule_slow_call")
    public String test_fuse_rule_slow_call(){
        try {
            Thread.sleep(120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "熔断策略：慢调用比例";
    }

    /**
     * 熔断策略：异常比例
     * @return
     */
    @GetMapping("/test_fuse_rule_exception_proportion")
    public String test_fuse_rule_exception_proportion(){
        Random random = new Random();
        boolean flag = random.nextBoolean();
        if (flag){
            throw new RuntimeException("抛出异常");
        }
        return "熔断策略：异常比例";
    }

    /**
     * 熔断策略：异常数
     * @return
     */
    @GetMapping("/test_fuse_rule_exception_num")
    public String test_fuse_rule_exception_num(){
        Random random = new Random();
        boolean flag = random.nextBoolean();
        if (flag){
            throw new RuntimeException("抛出异常");
        }
        return "熔断策略：异常数";
    }


}
