<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 使用springmvc的form标签，必须声明以下 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>form</title>
<script type="text/javascript" src="${path}/js/jquery-1.8.0.min.js"></script>
</head>
<body>
	<form:form action="form/add" method="post" modelAttribute="user">
        id:<form:input path="id"/><form:errors path="id"/><br>
        name:<form:input path="name"/><form:errors path="name"/><br>
        birth:<form:input path="birth"/><form:errors path="birth"/>
        <input type="submit" value="submit">
    </form:form>
</body>
</html>