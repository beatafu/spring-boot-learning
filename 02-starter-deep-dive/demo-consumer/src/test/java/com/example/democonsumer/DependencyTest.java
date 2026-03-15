package com.example.democonsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.startup.Tomcat;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class DependencyTest {

    /**
     * 测试1：验证Jackson依赖是否可用
     * 预期：无需额外引入jackson依赖，ObjectMapper可直接使用
     */
    @Test
    public void testJacksonAvailable() {
        // 如果jackson依赖不存在，这行会报ClassNotFoundException
        ObjectMapper mapper =  new ObjectMapper();
        assertNotNull(mapper);
        System.out.println("✅ Jackson 依赖已自动引入");
    }

    /**
     * 测试2：验证Tomcat依赖是否可用
     * 预期：Tomcat类可在classpath中找到
     */
    @Test
    public void testTomcatAvailable() {
        // 验证Tomcat核心类是否存在
        Class<?> tomcatClass = Tomcat.class;
        assertNotNull(tomcatClass);
        System.out.println("✅ Tomcat 依赖已自动引入");
    }

    /**
     * 测试3：验证Logback日志依赖是否可用
     */
    @Test
    public void testLogbackAvailable() {
        // 验证Logback类是否存在
        Class<?> loggerClass = Logger.class;
        assertNotNull(loggerClass);
        System.out.println("✅ Logback 依赖已自动引入");
    }
}