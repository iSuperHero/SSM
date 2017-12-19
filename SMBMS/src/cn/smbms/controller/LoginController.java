package cn.smbms.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import cn.smbms.util.Constants;

@Controller
public class LoginController {

	@Resource
	private UserService userService;
	
	//跳转到登录页面
	@RequestMapping(value="/login.html")
	public String toLogin(){
		return "login";
	}
	
	//执行登录功能
	@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,
						  @RequestParam String userPassword,
						  HttpServletRequest request,
						  HttpSession session) throws Exception{
		User user = userService.login(userCode, userPassword);
		if(null!=user){
			session.setAttribute(Constants.USER_SESSION, user);
			return "redirect:/sys/main.html";
		}else{
			request.setAttribute("error", "用户名或密码错误");
			return "login";
		}
	}
	
	//注销功能
	@RequestMapping("/logout.html")
	public String logout(HttpSession session){
		session.removeAttribute(Constants.USER_SESSION);
		return "login";
	}
	
	//跳转到主页面
	@RequestMapping(value="/sys/main.html")
	public String main(){
		return "frame";
	}
}
