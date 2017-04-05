 **SPPan博客系统** 
1. 后台使用springboot、spring data jpa实现。
2. 模版引擎使用超级经典的freemarker。
3. 管理后台UI使用Hadmin后台模版框架。
4. 页面前台使用超级小清新的layui。
5. 数据库使用MySQL。我开发的时候使用的MySQL，理论上可以支持各种形式的数据库，但是需要更换驱动类，因为程序代码不掺杂任何sql，全部由jpa生成。  
设置spring.jpa.properties.hibernate.hbm2ddl.auto=create以后，可以自动创建数据表。
6. 项目基于我的博客系统[我的博客](http://60.205.178.218)来实现，但是现在线上运行的是jfinal版本的，暂时没有开源，不过在不久的将来同样会开源。

> 关于关注之前的SPPanBlog开源项目的各位亲，由于上一版本写的是在马虎了点，代码各种乱，所以我直接使用本次版本的强制覆盖了之前的代码，也就是废弃了，至于还是比较喜欢jfinal的，可以稍微等等，等我完成本次springboot版本的博客以后，会同时开源本系统的jfinal实现版本，两个版本只是技术实现不一样，其他基本上没有什么差别。
系统截图：
![输入图片说明](http://git.oschina.net/uploads/images/2017/0405/181952_cd112209_559378.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0405/182009_490a1c7c_559378.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0405/182035_f42a637d_559378.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0405/182100_eb4e0594_559378.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0405/182131_3089e66d_559378.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0405/182155_e9fb07e8_559378.png "在这里输入图片标题")