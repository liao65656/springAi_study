# Spring AI Study Project

这是一个Spring AI学习项目。

## 安全配置说明

由于安全原因，`src/main/resources/application.yml` 文件中的敏感信息需要特殊处理。

### 配置方法

1. 复制模板文件：
   ```bash
   cp src/main/resources/application.yml.template src/main/resources/application.yml
   ```

2. 创建本地配置文件（不提交到版本控制）：
   ```bash
   cp src/main/resources/application.properties.template src/main/resources/application.properties
   ```

3. 或者在运行应用程序时通过环境变量设置密钥：
   ```bash
   export DEEPSEEK_API_KEY=your_actual_deepseek_api_key
   export DASHSCOPE_API_KEY=your_actual_dashscope_api_key
   export DB_USERNAME=your_database_username
   export DB_PASSWORD=your_database_password
   ```

### PDF文件说明

项目中的PDF文件已从版本控制中忽略，因为它们是二进制文件，不应该提交到Git仓库中。