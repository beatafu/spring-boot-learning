# Spring Boot Config Demo

一个基于 **Spring Boot 4.0.3** 的配置管理演示项目，展示了在实际开发中常见的配置使用方式：

- `@Value` 读取配置
- `@ConfigurationProperties` 批量绑定
- `Environment` 动态读取
- 多环境 `Profile` 配置
- 配置校验（Jakarta Validation）
- Jasypt 加密配置
- Actuator 配置观测

## 1. 项目结构

```text
03-spring-boot-config-demo/
├─ pom.xml                        # 父项目（聚合）
├─ README.md
└─ config-app/
   ├─ pom.xml                     # 子模块：配置演示应用
   ├─ src/main/java/com/example/config/
   │  ├─ ConfigAppApplication.java
   │  ├─ controller/
   │  └─ properties/
   ├─ src/main/resources/
   │  ├─ application.yaml
   │  ├─ application-dev.yaml
   │  ├─ application-test.yaml
   │  ├─ application-prod.yaml
   │  └─ application-monitoring.yaml
   └─ src/test/java/com/example/config/
      ├─ ConfigAppApplicationTests.java
      └─ JasyptTest.java
```

## 2. 技术栈

- Java 21
- Maven（Maven Wrapper）
- Spring Boot 4.0.3
- Spring Boot Web
- Spring Boot Actuator
- Spring Boot Validation
- Jasypt Spring Boot Starter (`3.0.5`)

## 3. 配置与 Profile

### 3.1 默认配置

主配置文件：`config-app/src/main/resources/application.yaml`

- 默认激活：`spring.profiles.active: dev`
- 服务端口：`8080`
- 上下文路径：`/api`
- 暴露 Actuator 端点：`health,info,configprops,env`

### 3.2 Profile 文件

- `application-dev.yaml`：开发环境（含 Jasypt 示例）
- `application-test.yaml`：测试环境
- `application-prod.yaml`：生产环境（通过环境变量注入敏感信息）
- `application-monitoring.yaml`：监控增强配置

### 3.3 常见启动方式

```powershell
# 在项目根目录执行（启动子模块）
.\mvnw.cmd -pl config-app spring-boot:run

# 指定 profile 启动（示例：test）
.\mvnw.cmd -pl config-app spring-boot:run -Dspring-boot.run.profiles=test

# 指定 profile 启动（示例：prod）
.\mvnw.cmd -pl config-app spring-boot:run -Dspring-boot.run.profiles=prod
```

应用默认访问地址：`http://localhost:8080/api`

## 4. 主要接口

> 以下路径均基于 `/api`。

### 4.1 配置读取演示

- `GET /config/value`：`@Value` 读取配置
- `GET /config/random`：`SpEL` 与系统属性读取
- `GET /config/properties`：`@ConfigurationProperties` 读取完整配置
- `GET /config/contact`：读取联系人配置
- `GET /config/database`：读取数据库配置

### 4.2 Environment 与属性源

- `GET /config/env?key=app.name`：按 key 动态读取
- `GET /config/env-with-default?key=app.xxx&default=默认值`：带默认值读取
- `GET /config/property-sources`：查看属性源顺序
- `GET /config/active-profiles`：查看当前激活 profile

### 4.3 Profile、优先级、校验与加密

- `GET /profile/info`：当前 profile 信息
- `GET /profile/database`：当前数据库配置
- `GET /profile/external-api`：外部 API 配置
- `GET /priority/test`：配置优先级测试
- `GET /validation/security`：校验后的安全配置读取
- `GET /encrypt/db-password`：加密配置读取示例

## 5. 构建与测试

```powershell
# 编译并运行测试（根项目）
.\mvnw.cmd clean test

# 仅构建 config-app
.\mvnw.cmd -pl config-app clean package
```

## 6. Jasypt 使用说明

`application-dev.yaml` 中示例使用了：

- 算法：`PBEWithMD5AndDES`
- 密钥：`my-secret-key`（仅演示用途）
- 密文格式：`ENC(...)`

可通过测试类 `config-app/src/test/java/com/example/config/JasyptTest.java` 生成加密字符串。

## 7. 生产环境注意事项

- 不要将真实密钥写入配置文件，使用环境变量或密钥管理系统。
- `application-prod.yaml` 中数据库与 API 密钥使用环境变量占位：
  - `DB_USERNAME`
  - `DB_PASSWORD`
  - `API_KEY`
- `EncryptController` 仅用于演示，生产环境不应返回明文或可逆敏感信息。
- 建议在生产环境收紧 Actuator 暴露范围。

## 8. 快速验证

启动后可先访问：

- `http://localhost:8080/api/config/value`
- `http://localhost:8080/api/profile/info`
- `http://localhost:8080/api/actuator/health`

---

如果你希望，我可以继续补一版 **接口示例响应**（含 JSON 样例）到 README，便于前后端联调直接使用。
