package com.lagou.orderservice.controller;

import com.lagou.orderservice.dto.Stock;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class OrderController {


    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 创建订单业务逻辑
     * @param skuId 商品类别编号
     * @param salesQuantity 销售数量
     * @return
     */
    @GetMapping("/create_order")
    public Map createOrder(Long skuId , Long salesQuantity){
//        Map ret = createOrderByCode(skuId, salesQuantity);
        Map ret = createOrderByAnnotation(skuId, salesQuantity);
        return ret;
    }

    // ribbon 通过代码实现负载均衡
    private Map createOrderByCode(Long skuId,Long salesQuantity){
        Map result = new LinkedHashMap();
        ServiceInstance serviceInstance = loadBalancerClient.choose("warehouse-service");
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        Stock restTemplateForObject = restTemplate.getForObject("http://" + host + ":" + port + "/stock?skuId=" + skuId, Stock.class);
        System.out.println("ribbon 代码模式负载均衡通过restTemplate通信之后，返回结果：" + restTemplateForObject);
        if(salesQuantity <= restTemplateForObject.getQuantity()){
            //创建订单相关代码，此处省略
            //CODE=SUCCESS代表订单创建成功
            result.put("code" , "SUCCESS");
            result.put("skuId", skuId);
            result.put("message", "订单创建成功");
        }else{
            //code=NOT_ENOUGN_STOCK代表库存不足
            result.put("code", "NOT_ENOUGH_STOCK");
            result.put("skuId", skuId);
            result.put("message", "商品库存数量不足");
        }
        return result;
    }

    // ribbon 通过注解实现负载均衡
    private Map createOrderByAnnotation(Long skuId,Long salesQuantity){
        Map result = new LinkedHashMap();
        Stock restTemplateForObject = restTemplate.getForObject("http://warehouse-service/stock?skuId=" + skuId, Stock.class);
        System.out.println("ribbon 注解模式负载均衡通过restTemplate通信之后，返回结果：" + restTemplateForObject);
        if(salesQuantity <= restTemplateForObject.getQuantity()){
            //创建订单相关代码，此处省略
            //CODE=SUCCESS代表订单创建成功
            result.put("code" , "SUCCESS");
            result.put("skuId", skuId);
            result.put("message", "订单创建成功");
        }else{
            //code=NOT_ENOUGN_STOCK代表库存不足
            result.put("code", "NOT_ENOUGH_STOCK");
            result.put("skuId", skuId);
            result.put("message", "商品库存数量不足");
        }
        return result;
    }
}
