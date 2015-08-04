<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>接口用例数据批次列表</title>
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
    <script>
    $(function () {
		var localObj = window.location;
		var contextPath = localObj.pathname.split("/")[1];
		var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
		var caseId = $("#caseId").val();
		$('#addnew').click(function(){
			window.location.href=basePath+"/data/INTERFACE_CASE/add/"+caseId;
		 });
		$('#importFile').click(function(){
			window.location.href=basePath+"/data/import/"+caseId;
		 });
    });

	function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			var url = "index.jsp";
			window.location.href=url;		
		}
	
	}
</script>
</head>
<body>
<form class="form-inline definewidth m20" action="index.jsp" method="get">  
接口用例Id：${caseId }&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">添加数据批次</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="importFile">导入数据批次</button>
</form>
<input id="caseId" value="${caseId }" type="hidden">
<table class="table table-bordered table-hover definewidth m10" >
    <tbody>
       <c:forEach items="${datas}" var="ils">
		<tr>
			<th class="tableleft" colspan="3">Expect：${ils.expectData} &nbsp;&nbsp;&nbsp;
				<a href="<%=request.getContextPath()%>/data/INTERFACE_CASE/dbatch/${caseId }/${ils.id}">删除批次</a>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/data/INTERFACE_CASE/update/${caseId }/${ils.id}">更新期望值</a>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/data/INTERFACE_CASE/iblist/${ils.id}">修改添加参数</a>
			</th>
		</tr>
		<tr>
			<td>id</td>
			<td>参数名</td>
			<td>参数值</td>
		</tr>
			<c:forEach items="${ils.interfaceDatas }" var="iids">
				<tr>
					<td>${iids.id }</td>
		            <td>${iids.dataName }</td>
		            <td>${iids.dataContent }</td>
		        </tr>
	        </c:forEach>
        </c:forEach>
        </tbody>
     </table>     
</body>
</html>
