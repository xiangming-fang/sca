package com.itlaoqi.videoblog.videoservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class VideoController {
    @GetMapping("/test")
    public String findById(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // remotePort 这是客户端端口
//        int port = request.getRemotePort();
//        String remoteAddr = request.getRemoteAddr();
        // 这是服务端部署的ip地址和端口
        int serverPort = request.getServerPort();
        // 请求方式
        String method = request.getMethod();
        String localAddr = request.getLocalAddr();
        return "SUCCESS：" + method + " 请求\n 请求的服务器ip地址：" + localAddr + "\n 请求的端口号是:" + serverPort;
    }
}
