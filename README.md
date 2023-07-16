# 智能简历分析项目
Resume Design Competition of University Computer Competition 2023计算机设计大赛作品

核心功能：简历管理、智能简历解析、岗位管理、候选人推荐、人岗匹配等智能招聘类系统。

亮点：
1. 采用前后端开发，Restful风格请求定义
2. 采用NLP、CV领域相关商业大模型来解析简历，实现准确提取
3. 构建人岗匹配算法来进行岗位推荐

**也是学习vue之后的第一个练手项目 >..<**
## 技术栈
1. 前端采用vue框架开发，element-plus作为组件库；后端采用SpringBoot+Mybatis开发。
2. 存储采用Mysql+阿里OSS
3. 简历分析模型包含OCR、Paddle等cv以及nlp等技术和模型（此部分需要单独配置）。


## 相关链接
 1. [示例测试](http://cv.tans.fun)
 2. [API接口文档](https://apifox.com/apidoc/shared-164d0341-8911-4378-a3da-6e42d63524a8/api-88155014)
## 部署测试

### 后端部分：
1. 下载[schema.sql](https://github.com/TanYongF/CV_Design/tree/master/backend/src/main/resources/db),在数据库控制台执行脚本，创建相应表结构
2. 配置`backend/src/main/resources/application.yml` 中Mysql地址和OSS上传服务
3. 执行`backend/src/main/java/fun/tans/CVApplication.java`中主类方法即可
4. 参照接口文档对 http://localhost:8083/ 进行接口测试 

### 前端部分
1. 修改 `frontend/.env.production` 文件配置api代理前缀 
2. 进入`frontend` 执行`npm run build`
3. 将生成的`dist`目录下的所有东西上传至网站根目录

### 配置代理转发（可选）
5. 在服务器配置Nginx，如需将 `http://ip1:port1/api/**` 路径转发至 `http://ip2:port2/**` 其中ip1、port1是请求静态资源文件的地址和端口，ip2、port2是请求后端接口的地址
```conf
    location ^~ /api {
        rewrite ^/api/(.*)$ /$1 break;  #将/api/** 替换成 /** 进行请求转发
        proxy_pass http://127.0.0.1:8083; #这里替换为实际api接口地址
        proxy_set_header Host $Host:$server_port;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header REMOTE-HOST $remote_addr;
        add_header X-Cache $upstream_cache_status;
        proxy_set_header X-Host $host:$server_port;
        proxy_set_header X-Scheme $scheme;
        proxy_connect_timeout 30s;
        proxy_read_timeout 86400s;
        proxy_send_timeout 30s;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
    }

```


