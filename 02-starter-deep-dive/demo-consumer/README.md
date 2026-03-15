# Demo Consumer 使用说明

这是一个演示如何消费 `sms-spring-boot-starter` 的示例应用。

## 快速开始

### 1. 运行方式

**方式一：使用脚本（推荐）**
直接双击运行根目录下的 `run-demo.cmd`。

**方式二：Maven 命令行**
在项目根目录执行：
```bash
mvnw clean install -DskipTests
mvnw -pl demo-consumer spring-boot:run
```

**方式三：IDE**
在 IntelliJ IDEA 中打开 `demo-consumer` 模块下的 `src/main/java/com/example/democonsumer/DemoConsumerApplication.java`，直接运行 `main` 方法。

---

## 2. API 测试

启动成功后，访问以下接口进行测试（默认端口 8080）：

### 发送普通短信
- **GET** `http://localhost:8080/api/sms/send?phone=13800138000&message=HelloFromStarter`
- **预期结果**：返回 JSON 格式的发送结果，控制台打印发送日志。

### 发送模板短信（验证码）
- **GET** `http://localhost:8080/api/sms/template/verify?phone=13800138000&code=8888`
- **预期结果**：发送包含验证码的模板短信。

### 获取可用模板列表
- **GET** `http://localhost:8080/api/sms/templates`
- **预期结果**：返回如 `["verify_code", "notify"]` 等默认模板名称。

## 3. 配置说明
可以在 `src/main/resources/application.properties` 中修改短信配置（如 `sms.provider` 等），查看启动日志的变化。

