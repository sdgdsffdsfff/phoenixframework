# phoenixframework<br>
   phoenixframework是一个自动化测试平台，集代码托管，
 分机（node节点）管理，定时任务，<br>
分布式或并发等方式执行通过phoenix_develop模块调试好的用例。<br>
平台使用SSH4开发，覆盖了webgui，接口，移动mobile等终端的测试与监控。<br>
目前webGUI模块已经完成，兼容chrome，Firefox，IE以及phantomjs驱动。<br>
平台通过phoenix_develop模块在客户端开发及调试代码，
然后通过将代码托管到phoenix_web控制端，<br>
控制端通过指派多个phoenix_node端方式执行测试用例。
通过使用phoenix_develop开发用例代码的示例，<br>
用例如果在本地调试时没有问题，那么就可以放到控制端进行执行了。<br>
平台网站：http://www.cewan.la<br>
中文搜：测完啦<br>
<br>
最新版本：1.3.5<br>
更新内容：<br>
1、phoenix_db：封装了Druid，通过Druid可以轻量级的对其他数据库进行操作<br>
2、phoenix_node：增加在执行过程中对第三方数据库进行操作<br>
3、phoenix_web：优化对异常信息的处理<br>
4、phoenix_interface：增加对cookie，host，proxy等设置<br>
5、phoenix_web：在数据批次导入界面，增加选择的数据表中的用例与界面用例是否一致的判断<br>
6、phoenix_webdriver:增加对Socket服务器连接的支持<br>
7、phoenix_webdriver:删除了原自带的驱动，使体积减小到了64kb，方便引入<br>
<br>
系统名称：自动化测试平台<br> 
系统介绍： <br>
【支持的部署方式】：J2EE，Jenkins，maven，J2SE，分布式部署，Jetty部署 <br>
【技术说明】：Apache quartz，Webmagic，httpunit，selendroid，<br>
selenide，Spring+SpringMVC+Hibernate4，Executor，Forkjoin，Maven项目管理，<br>
Bootstrap，JQuery，JDK动态编译+反射+执行，DWR，highchat <br>
【权限管理】：方法级别的权限控制 <br>
【覆盖系统类型】：WEB GUI自动化测试，接口自动化测试，Android/IOS app自动化测试，<br>
WEB GUI自动化监控，接口自动化监控，数据库测试，简单安全性测试 <br>
【消息通知】：Email异步发送，短信异步发送，在线日志检视，统计报表生成<br>
【模块介绍】<br>
phoenix_develop：用例代码开发模块<br>
phoenix_node:分布式执行node节点<br>
phoenix_web:平台控制端<br>
phoenix_webdriver:webGUI自动化测试模块<br>
phoenix_mobiledriver:移动设备测试模块<br>
phoenix_interface：接口测试系统<br>
phoenix_db:数据库操作模块，对hibernate4的封装<br>