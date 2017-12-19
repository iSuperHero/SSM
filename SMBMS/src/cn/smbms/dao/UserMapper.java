package cn.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;

public interface UserMapper {

	//用户登录
	public User getLoginUser(@Param("userCode")String userCode) throws Exception;
	//查询记录数
	public Integer getCount(@Param("userName")String userName,
				 			@Param("userRole")Integer userRole);
	//分页查询
	public List<User> getUserList(@Param("userName")String userName,
 								  @Param("userRole")Integer userRole,
 								  @Param("curPageNo")Integer curPageNo,
 								  @Param("pageSize")Integer pageSize);
	//按主键查询
	public User getUserById(@Param("id")Integer id);
	//修改用户
	public int udpateUser(User user);
	//删除用户
	public int deleteUser(@Param("id")Integer id);
	//新增用户
	public int addUser(User user);
	//修改免密码
	public int modifyPwd(@Param("newPwd")String newPwd,@Param("id")Integer id);
}
