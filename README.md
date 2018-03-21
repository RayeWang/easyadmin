# easyadmin

数据库一定要用utf8mb4编码，记得先执行manager.sql，再执行initdata.sql,其中tenant表不用管，这是我打算做的另外一个项目设计的表，因为直接复制的本项目，怕到时候忘记，所以没有删除
[拦截器相关文档](http://git.oschina.net/raywang2014/easyadmin/wikis/%E6%8B%A6%E6%88%AA%E5%99%A8%E8%AE%BE%E8%AE%A1%E7%9B%B8%E5%85%B3)
##简介
一个简单好看的后台开发模板框架，目前具备用户管理，菜单管理和角色管理3个功能，也只打算做这3个，毕竟这是所有后台管理框架的核心，而本项目也只是打算做一个模板框架而已
##优点
###一、方便快捷，配置文件少；项目基于spring boot，无需繁琐的xml配置
###二、界面美观；基于Ace Admin前端框架
###三、权限拦截器性能高，仅对Controller请求进行拦截
###四、功能单一，方便扩展（也是缺点）
##缺点
###一、Ace Admin文档太少（不过常用的功能都已有现成页面，只需要稍作修改即可）
###二、拦截器无法拦截静态文件
##主体框架
项目基于Spring boot+Mybatis+Ace Admin
##用到的框架
[Spring boot](http://projects.spring.io/spring-boot/)

[Mybatis](http://www.mybatis.org/mybatis-3/zh/index.html)

[druid](https://github.com/alibaba/druid)

[gson](https://github.com/google/gson)

[lombok](https://projectlombok.org/)

[Ace Admin](http://ace.jeka.by/)
##拦截器流程
登录时读取当前用户拥有的可访问的url集合，在发起请求后，先判断是否是无需拦截的url（如登录地址），如果不是则从session中获取用户信息，如果获取失败说明没有登录或者登录已失效，则根据被调用的方法返回值类型，如果是String或者ModelAndView，则返回跳转到登录页面的ModelAndView，如果返回值是WebResult（指定的用@ResponseBody），则返回没有登录的json（因为没有找到判断方法是否有某个注解的方法，所以只能通过这个方法来实现了）；如果已经登录了，则判断当前url是否是在当前用户拥有的权限列表，如果在则调用方法，如果不在则同未登录处理一样，只是返回的是没有权限的页面或json

###关于超级用户和默认权限

超级用户的id为-1，访问所有url都不会进行拦截

默认权限的角色id为-1，用于设置公共的权限，所有用户默认被授予此权限
##部分截图
![登录页面](http://git.oschina.net/raywang2014/easyadmin/raw/master/imgs/20170411192433.png)
![菜单管理](http://git.oschina.net/raywang2014/easyadmin/raw/master/imgs/20170411193119.png)
![角色管理](http://git.oschina.net/raywang2014/easyadmin/raw/master/imgs/20170411193134.png)
![用户管理](http://git.oschina.net/raywang2014/easyadmin/raw/master/imgs/20170411193149.png)
