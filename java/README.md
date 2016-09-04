# feedback-java 后台支持项目

## 使用指南

1. 项目所依赖的环境
    * java-1.8
    * gradle-2.14
    * bower-1.7.9
    * npm-3.6.0
    * node-5.7.1
    * tomcat-1.8

2. 工具说明
    * java：为项目所运行的依赖宿主语言
    * tomcat：为项目所运行的容器
    * gradle：为项目的包管理工具
    * node：为npm所依赖的环境
    * bower：项目前端包管理工具
    * npm：package软件管理工具

3. 项目运行指南
    * 假设你已经安装好了java，tomcat，gradle，node，npm
    * 安装前端软件包
    
    ```shell
    npm install
    ```
    * 前端框架包下载
        
    ```shell
    bower install 
    ```
    * 复制前端依赖包到项目中
    
    ```shell
    grunt begin
    ```
    * 最后下载项目的java包并打包
    
    ```shell
    gradle build
    ```


