<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/calendar/WdatePicker.js"></script>
</head>
<body>
   <fm:form method="post" modelAttribute="user">
		用户编码：<fm:input path="userCode"/>
		<fm:errors path="userCode"/><br/>
		用户名称：<fm:input path="userName"/>
		<fm:errors path="userName"/><br/>
		用户密码：<fm:password path="userPassword"/>
		<fm:errors path="userPassword"/><br/>
		用户生日：<fm:input path="birthday" Class="Wdate" id="birthday" name="birthday" 
					readonly="readonly" onclick="WdatePicker();"/>
	    <fm:errors path="birthday"/><br/>
	   	用户性别：<fm:radiobutton path="gender" value="1"/>男
	   			<fm:radiobutton path="gender" value="2"/>女  <br/>
	           用户地址：<fm:input path="address"/><br/>
	           用户电话：<fm:input path="phone"/><br/>
	           用户角色：<fm:radiobutton path="userRole" value="1"/>系统管理员
	           <fm:radiobutton path="userRole" value="2"/>经理
	           <fm:radiobutton path="userRole" value="3" checked="checked"/>普通用户<br/>
	    <input type="submit" value="保存"/>
	</fm:form>
</body>
</html>