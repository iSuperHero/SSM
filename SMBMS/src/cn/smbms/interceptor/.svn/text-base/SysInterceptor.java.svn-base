package cn.smbms.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.smbms.pojo.User;
import cn.smbms.util.Constants;

public class SysInterceptor extends HandlerInterceptorAdapter{

	//private Logger logger = Logger.getLogger(SysInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws IOException{
		System.out.println("preHandle");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constants.USER_SESSION);
		if(null == user){
			response.sendRedirect(request.getContextPath()+"/401.jsp");
			return false;
		}
		return true;
	}
}
