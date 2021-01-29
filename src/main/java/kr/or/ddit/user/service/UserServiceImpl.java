package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	//�ٸ� ���������� �����ϴ� ������̼� �� �ϳ��� ���ҽ�
	//������ �ٿ����ÿ� �ִ� @Repository("userDao") ���⼭ ������ų� ���࿡ �������丮�� ������ ����Ʈ�� ������ Ŭ�����̸� �տ� �ҹ��ڷ� �ٲ۰� �װ� �������� �ȴ�.
	@Resource(name="userDao")
	private UserDao userDao;
	
	/*
	 * Multiple annotations found at this line: - No constructor with 0 arguments
	 * defined in class 'kr.or.ddit.user.service.UserServiceImpl'
	 * 
	 * �⺻�����ڰ� ���ܴ�... �Ʒ� ������ָ鼭
	 */
	
	public UserServiceImpl() {}
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserVo getUser(String userid) {
		return userDao.getUser(userid);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
}
