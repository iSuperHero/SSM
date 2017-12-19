package cn.smbms.service;

import java.util.List;

import cn.smbms.pojo.User;

public interface UserService {

	public User login(String userCode,String password) throws Exception;
	public Integer getCount(Integer userRole,String userName);
	public List<User> getUserList(Integer userRole,String userName,
								  Integer curPageNo,Integer pageSize);
	public User getUserById(int id);
	public int update(User user);
	public int delete(Integer id);
	public int add(User user);
	public int updatePwd(String pwd,Integer id);
}
