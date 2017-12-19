package cn.smbms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.dao.UserMapper;
import cn.smbms.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User login(String userCode, String password) throws Exception {
		User user =  userMapper.getLoginUser(userCode);
		if(null!=user){
			if(!user.getUserPassword().equals(password)){
				user = null;
			}
		}
		return user;
	}

	@Override
	public Integer getCount(Integer userRole, String userName) {
		return userMapper.getCount(userName, userRole);
	}

	@Override
	public List<User> getUserList(Integer userRole, String userName,
			Integer curPageNo, Integer pageSize) {
		return userMapper.getUserList(userName, userRole, curPageNo, pageSize);
	}

	@Override
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}

	@Override
	public int update(User user) {
		return userMapper.udpateUser(user);
	}

	@Override
	public int delete(Integer id) {
		return userMapper.deleteUser(id);
	}

	@Override
	public int add(User user) {
		return userMapper.addUser(user);
	}

	@Override
	public int updatePwd(String pwd, Integer id) {
		return userMapper.modifyPwd(pwd, id);
	}
}
