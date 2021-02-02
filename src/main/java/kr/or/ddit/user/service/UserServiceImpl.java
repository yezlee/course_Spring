package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService") // 이름설정을 안해놓으면 UserServiceImpl이걸로 이름을 디폴트로 만들어버려서 테스트코드 에러뜸
public class UserServiceImpl implements UserService{

	//다른 스프링빈을 주입하는 어노테이션 중 하나가 리소스
	//네임은 다오임플에 있는 @Repository("userDao") 여기서 갖고오거나 만약에 리포지토리가 없으면 디폴트로 생성된 클래스이름 앞에 소문자로 바꾼거 그거 가져오면 된다.
	@Resource(name="userDao")
	private UserDao userDao;
	
	/*
	 * Multiple annotations found at this line: - No constructor with 0 arguments(인수, 파라미터에 실제로 담기는 값)
	 * defined in class 'kr.or.ddit.user.service.UserServiceImpl'
	 * 
	 * 기본생성자가 없단다... 아래 만들어주면서
	 */

	public UserServiceImpl() {}

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}
	

	@Override
	public List<UserVo> selectAllUsers() {
		return userDao.selectAllUsers();
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo vo) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("pageVo", vo);
		map.put("userList", userDao.selectPagingUser(vo));
		map.put("userCnt", userDao.selectAllUserCnt());
		map.put("pagination", (int)Math.ceil((double)((int)map.get("userCnt"))/vo.getPageSize()));
		return map;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		return userDao.modifyUser(userVo);
	}

	@Override
	public int insertUser(UserVo userVo) {
		return userDao.insertUser(userVo);
	}

	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}


}