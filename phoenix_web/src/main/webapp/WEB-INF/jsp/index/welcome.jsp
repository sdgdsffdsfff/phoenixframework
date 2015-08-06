<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>系统说明</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/style.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }

    </style>
</head>
<body>
<table class="table table-bordered table-hover definewidth m10" >
        <tr>
           <th colspan="3" align="center">版本说明</th>
        </tr>
        <tr>
            <td>版本1.3.3</td>
            <td>接口测试增加了支持批量数据参数化功能<br>
				phoenix_web：修复发送成功后删除功能bug<br>
				phoenix_web：增加了用于接口批量参数化的两张表，其他表结构不受影响<br>
				phoenix_web：增加接口测试批量数据的批量导入、导出，以及数据的在线维护<br>
				phoenix_web：修复编辑INTERFACE_CASE时，数据没有带出来问题<br>
				phoenix_web：去除了用户角色的唯一性校验的索引<br>
				phoenix_web,phoenix_node：为方便部署，都使用jdk1.7进行编译<br>
            </td>
            <td>2015.8.6</td>
         </tr>
        <tr>
            <td>版本1.3.2</td>
            <td>1、phoenix_webdriver：增加了公共用例引用功能，具体示例请见phoenix_develop的org.phoenix.cases.aggregate<br>
				2、phoenix_web：修复通过编辑任务添加的定时任务策略不生效问题<br>
				3、phoenix_node：增加了对公共用例引用的解析功能<br>
				4、phoenix_web：用例管理->用例列表界面增加根据场景名称筛选<br>
				5、其他几个小bug修复<br>
            </td>
            <td>2015.7.25</td>
        </tr>
        <tr>
            <td>版本1.3.1</td>
            <td>1、phoenix_web：修复xpath作数据分离后带有'\'符号的不能正常使用问题，增加了用例类型分类<br>
				2、phoenix_webdriver：修复webElementLinkFinder方法数据分离无效的问题，增加了对接口测试支持<br>
				3、phoenix_develop增加了几个应用案例，如如何做接口测试等<br>
				4、phoenix_web：修复通过编辑任务添加的定时任务策略不生效问题<br>
				5、phoenix_node：增加了接口测试用例类型支持，与webUI不同的是，接口用例使用独立的多个线程执行，而webUI是单线程。<br>
				6、phoenix_interface：增加了接口测试API<br>
				7、phoenix_webdriver:增加了Linux系统下chrome，Firefox，PhantomJs的支持，但不支持IEDriver
			</td>
            <td>2015.7.10</td>
        </tr>
        <tr><th colspan="3" align="center">联系方式</th></tr>
        <tr>
           <td>mengfeiyang</td>
           <td colspan="2">5156meng.feiyang@163.com</td>
        </tr>
        <tr>
           <td>网站</td>
           <td colspan="2"><a href="http://www.cewan.la" target="_blank">http://www.cewan.la</a>（测完啦！）</td>
        </tr>
        <tr>
           <td>QQ群</td>
           <td colspan="2">246776066</td>
        </tr>
        <tr><th colspan="3" align="center">系统支持类型</th></tr>
        <tr>
           <td>目前最新版本</td>
           <td colspan="2">支持Web GUI/Web接口自动化测试、Web GUI/Web接口自动监控与报警</td>
        </tr>
        <tr>
           <td>下一版本计划</td>
           <td colspan="2">增加移动mobile自动化测试与监控插件</td>
        </tr>
        <tr><th colspan="3" align="center">平台说明</th></tr>
        <tr>
           <td>部署方式</td>
           <td colspan="2">J2EE，Jenkins，maven，J2SE，分布式部署，Jetty部署</td>
        </tr>
        <tr>
           <td>技术说明</td>
           <td colspan="2">Apache quartz，Webmagic，httpunit，selendroid，
selenide，Spring+SpringMVC+Hibernate4，Executor，Forkjoin，Maven项目管理，
Bootstrap，JQuery，JDK动态编译+反射+执行，DWR，highchat </td>
        </tr>
        <tr>
           <td>模块划分</td>
           <td colspan="2">
            phoenix_develop：用例代码开发模块<br>
			phoenix_node:分布式执行node节点<br>
			phoenix_web:平台控制端<br>
			phoenix_webdriver:webGUI自动化测试模块<br>
			phoenix_mobiledriver:移动设备测试模块<br>
			phoenix_interface：接口测试系统<br>
			phoenix_db:数据库操作模块，对hibernate4的封装<br>
		</td>
        </tr>
     </table>   
</body>
</html>
