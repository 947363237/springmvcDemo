<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax</title>
<script type="text/javascript" src="${path}/js/jquery-1.8.0.min.js"></script>
<script>
	function ajax1(){
		var name = $("#name").val();
		$.post("${path}/mvc/getPerson",{name:name},function(data){
			$("#result").html(data);
		});
	}
</script>	
</head>
<body>
	<input id="name" name="name"/><button onclick="ajax1();">ajax</button>
	返回结果：<sapn id="result"></sapn>
	
	<br/><br/>
	<h4>文件上传：</h4>
	<form action="${path}/mvc/upload" method="post" 
		enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="submit">
	</form>
	<br/>上传成功，保存至：<span style="color: red;">${msg}</span>
</body>
</html>