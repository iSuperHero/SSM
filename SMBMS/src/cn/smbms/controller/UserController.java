package cn.smbms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.RoleService;
import cn.smbms.service.UserService;
import cn.smbms.util.Constants;
import cn.smbms.util.PageSupport;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value="/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
			
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	//分页查询
	@RequestMapping(value="/userlist.html")
	public String getUserList(Model model,@RequestParam(value="userName",required=false)String userName,
			@RequestParam(value="userRole",required=false)String userRole,
			@RequestParam(value="pageIndex",required=false)String pageIndex){
		logger.info("正在查询列表");
		List<User> userlist = new ArrayList<User>();
		int pageSize = Constants.pageSize;
		int curPageNo = 1;
		int userRoleId = 0;
		if(userName==null){
			userName="";
		}
		if(userRole!=null && !userRole.equals("")){
			userRoleId = Integer.parseInt(userRole); 
		}
		if(pageIndex!=null){
			try {
				curPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/user/syserror.html";
			} 
		}
		int totalCount	= userService.getCount(userRoleId,userName);
    	//总页数
    	PageSupport pages=new PageSupport();
    	pages.setPageSize(pageSize);
    	pages.setTotalCount(totalCount);
    	int totalPageCount = pages.getTotalPageCount();
    	//控制首页和尾页
    	if(curPageNo < 1){
    		curPageNo = 1;
    	}else if(curPageNo > totalPageCount){
    		curPageNo = totalPageCount;
    	}
    	System.out.println("--------------------------------------------"+curPageNo);
		userlist = userService.getUserList(userRoleId,userName,(curPageNo-1)*pageSize,pageSize);
		model.addAttribute("userList", userlist);
		List<Role> roleList = null;
		roleList = roleService.getRoleList();
		model.addAttribute("roleList", roleList);
		model.addAttribute("queryUserName", userName);
		model.addAttribute("queryUserRole", userRole);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", curPageNo);
		return "userlist";
	}
	
	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "syserror";
	}
	
	//查看用户详情
	@RequestMapping(value="/detail.html/{id}")
	public String detail(@PathVariable("id")Integer id,Model model){
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/userview";
	}
	
	//跳转到修改页面
	@RequestMapping("/update.html")
	public String toUpdate(@RequestParam("userid")Integer id,Model model){
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "modify";
	}
	
	//修改用户
	@RequestMapping(value="/modify.html",method=RequestMethod.POST)
	public String update(User user,HttpSession session){
		int count = userService.update(user);
		if(count>0){
			logger.info("修改成功");
			return "redirect:/user/userlist.html";
		}else{
			logger.info("修改失败");
			return "modify";
		}
	}
	
	//删除用户
	@RequestMapping(value="/delete.html",method=RequestMethod.GET)
	public String delete(@Param("userid")Integer userid){
		int count = userService.delete(userid);	
		if(count>0)
			logger.info("删除成功");
			return "redirect:/user/userlist.html";
	}
	
	//another delete
	@RequestMapping(value="/deluser",method=RequestMethod.POST)
	@ResponseBody
	public Object deluser(@Param("uid")Integer uid){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int count = userService.delete(uid);
		if(count>0){
			resultMap.put("delResult", "true");
		}else{
			resultMap.put("delResult", "false");
		}
		return JSON.toJSONString(resultMap);
	}
	
	//跳转到添加用户页面
	/*@RequestMapping(value="/add.html",method=RequestMethod.GET)
	public String add(@ModelAttribute("user")User user){
		logger.info("跳转成功！");
		return "user/useradd";
	}
	
	//save1
	@RequestMapping(value="/add.html",method=RequestMethod.POST)
	public String doAdd(User user,HttpSession session){
		logger.info("进入添加页面");
		user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
		user.setCreationDate(new Date());
		if(userService.add(user)==1){
			logger.info("用户添加成功");
			return "redirect:/user/userlist.html";
		}
		return "user/useradd";
	}*/
	
	//save2
	@RequestMapping(value="/add.html")
	public String add(@ModelAttribute("user")User user){
		logger.info("跳转成功！");
		return "useradd";
	}
	
	@RequestMapping(value="/add.html",method=RequestMethod.POST)
	public String doAdd(User user,HttpSession session){
		logger.info("进入添加页面");
		user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
		user.setCreationDate(new Date());
		if(userService.add(user)==1){
			logger.info("用户添加成功");
			return "redirect:/user/userlist.html";
		}
		return "useradd";
	}
	
	//跳转到修改密码页面
	@RequestMapping("sys/modifyPwd.html")
	public String toModifyPwd(){
		logger.info("--toModifyPwd");
		return "modifyPwd";
	}
	
	//判断密码是否正确
	@RequestMapping("mpwd.html")
	@ResponseBody
	public Object isOldPwd(@Param("oldpasswords")String oldpasswords,HttpSession session){
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		String oldpwd = ((User)(session.getAttribute(Constants.USER_SESSION))).getUserPassword();
		if(oldpwd.equals(oldpasswords)){
			resultMap.put("result","true");
		}else if(!oldpwd.equals(oldpasswords)){
			resultMap.put("result", "false");
		}else if(session.getAttribute(Constants.USER_SESSION)==null){
			resultMap.put("result", "sessionerror");
		}else if(oldpasswords==null || oldpasswords.equals("")){
			resultMap.put("result", "error");
		}else{
			resultMap.put("result", "error");
		}
		return JSON.toJSONString(resultMap);
	}
	
	//修改密码
	@RequestMapping(value="modifyPwd",method=RequestMethod.POST)
	public String modifyPwd(@Param("newpassword")String newpassword,HttpSession session){
		int id = ((User)session.getAttribute(Constants.USER_SESSION)).getId();
		int count = userService.updatePwd(newpassword, id);
		if(count==1){
			logger.info("密码修改成功");
			session.removeAttribute(Constants.USER_SESSION);
			return "login";
		}else{
			logger.info("密码修改失败");
			return "modifyPwd";
		}
	}
	
	@RequestMapping(value="/ucexist.html",method=RequestMethod.GET)
	@ResponseBody
	public Object ucexist(@Param("userCode")String userCode,HttpSession session){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(((User)session.getAttribute(Constants.USER_SESSION)).getUserCode().equals(userCode)){
			resultMap.put("result", "exist");
		}else{
			resultMap.put("result", "uexist");
		}
		return JSON.toJSONString(resultMap);
	}
}
