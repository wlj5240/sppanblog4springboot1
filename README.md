 **SPPanBlog4SpringBoot博客系统** 
#### 2018.03.28 v2.0正式版发布
一、使用vue和element-ui进行前端开发：
1. 管理后台前后端分离。
    1. github地址：https://github.com/whoismy8023/blog-admin-web.git
    2. gitee地址：https://gitee.com/whoismy8023/blog-admin-web.git

2. 前台前后端分离。
    1. github地址：https://github.com/whoismy8023/blog-front-web.git
    2. gitee地址：https://gitee.com/whoismy8023/blog-front-web.git
3. java后端代码。
    1. github地址：https://github.com/whoismy8023/sppanblog4springboot.git
    2. gitee地址：https://gitee.com/whoismy8023/sppanblog4springboot.git
    
二、功能升级：
1. markdown编辑器修改为mavon-editor。
2. 完成banner功能，并支持自定义banner。
3. 支持网站名称、网站介绍、icp自定义。
4. 重写标签和分类中包含文章统计功能，提高统计效率。
5. 加入guava依赖，使用guava的cache重写访问统计逻辑。
6. 加入文章点赞和点踩功能。
7. 移除项目freemarker依赖。
8. 关于自定义页面使用markdown编写。
9. 加入系统公告功能。
10. 查询作者会查询到相关密码以及加密盐问题处理。

三、部署说明：
1. 部署后台程序，拿到访问地址。
2. 修改api访问地址：config/dev.env.js 中的BASE_API。
3. 配置config/index.js中的proxyTable，处理开发的时候出现的跨域问题。
    ```javascript
    proxyTable: {
      '/api': {
        target: 'http://localhost:8080',
        pathRewrite: {
          '^/api': '/'
        }
    }
    ```
4. 通过命令行进入到项目的根目录，执行如下命令安装依赖文件
    ```sh
    npm install
    ```
5. 运行如下命令启动
    ```sh
    npm run dev
    ```
        
四 打包发布
1. 修改api访问地址：config/prod.env.js中的BASE_API。
2. 运行如下命令进行打包
    ```sh
      npm run build
    ```
3. 打包完成后可以看到根目录的dist文件夹，直接复制到nginx目录中配置即可使用。

五、v2.0预览图
![输入图片说明](http://whoismy8023.gitee.io/2017/04/24/images/SPPanBlog4SpringBoot/1.png "在这里输入图片标题")

![输入图片说明](http://whoismy8023.gitee.io/2017/04/24/images/SPPanBlog4SpringBoot/2.png "在这里输入图片标题")

![输入图片说明](http://whoismy8023.gitee.io/2017/04/24/images/SPPanBlog4SpringBoot/3.png "在这里输入图片标题")

![输入图片说明](http://whoismy8023.gitee.io/2017/04/24/images/SPPanBlog4SpringBoot/4.png "在这里输入图片标题")

![输入图片说明](http://whoismy8023.gitee.io/2017/04/24/images/SPPanBlog4SpringBoot/5.png "在这里输入图片标题")

#### 2017.12.12
系统1.0使用Hadmin开发管理后台，和项目集成在一起，详细代码见开源项目的v1.0分支。

1. 后台使用springboot、spring data jpa实现。全文检索使用Lucene。
2. 模版引擎使用超级经典的freemarker。
3. 管理后台UI使用Hadmin后台模版框架。
4. 页面前台使用超级小清新的layui。
5. 数据库使用MySQL。我开发的时候使用的MySQL，理论上可以支持各种形式的数据库，但是需要更换驱动类，因为程序代码不掺杂任何sql，全部由jpa生成。  
设置spring.jpa.properties.hibernate.hbm2ddl.auto=create以后，可以自动创建数据表。
6. **jfinal版本的开源地址为：http://git.oschina.net/whoismy8023/sppanblog4jfinal**

系统截图：  
![输入图片说明](http://git.oschina.net/uploads/images/2017/0411/205539_eee2b847_559378.png "在这里输入图片标题")

![输入图片说明](http://git.oschina.net/uploads/images/2017/0411/205603_bfca9484_559378.png "在这里输入图片标题")

![输入图片说明](http://git.oschina.net/uploads/images/2017/0411/205601_8719a026_559378.png "在这里输入图片标题")

![输入图片说明](http://git.oschina.net/uploads/images/2017/0411/205645_8a1b3a5b_559378.png "在这里输入图片标题")

![输入图片说明](http://git.oschina.net/uploads/images/2017/0411/205708_8f5db6e3_559378.png "在这里输入图片标题")
