package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	//다른 스프링빈을 주입하는 어노테이션 중 하나가 리소스
	//네임은 다오임플에 있는 @Repository("userDao") 여기서 갖고오거나 만약에 리포지토리가 없으면 디폴트로 생성된 클래스이름 앞에 소문자로 바꾼거 그거 가져오면 된다.
	@Resource(name="userDao")
	private UserDao userDao;
	
	/*
	 * Multiple annotations found at this line: - No constructor with 0 arguments
	 * defined in class 'kr.or.ddit.user.service.UserServiceImpl'
	 * 
	 * 기본생성자가 없단다... 아래 만들어주면서
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
