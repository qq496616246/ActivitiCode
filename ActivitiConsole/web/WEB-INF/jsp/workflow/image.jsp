<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前流程图</title>
</head>
<body>
<!-- 1.获取到规则流程图 -->
<img style="position: absolute;top: 0px;left: 0px;" src="${pageContext.request.contextPath}/lookprocess?pdid=${deplyomentId}"/>

<!-- 2.根据当前活动的坐标，动态绘制DIV -->
<div style="position: absolute;border:1px solid red;top:${currentTask.y}px;left:${currentTask.x}px;width: ${currentTask.width}px;height:${currentTask.height}px;"></div>

</body>
</html>
