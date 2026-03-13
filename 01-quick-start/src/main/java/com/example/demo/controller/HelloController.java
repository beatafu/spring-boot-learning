package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // = @Controller + @ResponseBody
public class HelloController {
    
    @GetMapping("/hello")  // 处理GET请求
    public String hello() {
        return "Hello, Spring Boot 3.x!";
    }
    
    @GetMapping("/api/user")
    public UserDTO getUser() {
        // Java 17 Record 新特性
        return new UserDTO("张三", 25);
    }
    
    // 使用Record定义DTO（Java 17+）
    public record UserDTO(String name, int age) {}
}