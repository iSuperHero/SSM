<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>密码修改页面</span>
        </div>
        <div class="providerAdd">
        	<span style="color: red">${error}</span>
            <form id="userForm"  method="post" action="${pageContext.request.contextPath}/user/modifyPwd">
				<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="userCode">旧密码：</label>
                    <input type="password" name="oldpassword" id="oldpassword" value=""> 
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <label for="userName">新密码：</label>
                    <input type="password" name="newpassword" id="newpassword" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="ruserPassword">确认密码：</label>
                    <input type="password" name="rnewpassword" id="rnewpassword" value=""> 
					<font color="red"></font>
                </div>
                <div class="providerAddBtn">
                    <input type="submit" name="save" id="save" value="保存" >
				</div>
            </form>
        </div>
 </div>
 
 
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/pwdmodify.js"></script>
