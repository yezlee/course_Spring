package kr.or.ddit.user.repository;


import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;


// <bean id="" class=""  근데 클래스는 이미뭔지 알아. 아이디가 문제
// @Repository 에서 별다른 설정을 하지 않으면 스프링 빈 이름으로 class이름에서 첫글자를 소문자로 한 문자열이 스프링 빈 이름으로 설정된다.
// UserDaoImpl ==> userDaoImpl


// 네이밍 : 인터페이스는 UserDao 구현하는클래스 UserDaoImpl ==> @Resource(name="userDaoImpl")
// 근데 예전에 인터페이스를 UserDaoI 로 하고 구현클래스를 UserDao로 한적 있었어 이걸 바꿔주려면 이름을 지정해주면돼 @Repository("userDao") 이렇게 하면 테스트에다가 @Resource(name="userDao")

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public UserVo selectUser(String userid) {
		return template.selectOne("users.selectUser", userid);
	}

	@Override
	public List<UserVo> selectAllUsers() {
		return template.selectList("users.selectAllUser");
	}

	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		return template.selectList("users.selectPagingUser", vo);
	}

	@Override
	public int selectAllUserCnt() {
		return template.selectOne("users.selectAllUserCnt");
	}

	@Override
	public int modifyUser(UserVo userVo) {
		return template.update("users.modifyUser", userVo);
	}

	@Override
	public int insertUser(UserVo userVo) {
		return template.insert("users.insertUser", userVo);
	}

	@Override
	public int deleteUser(String userid) {
		return template.delete("users.deleteUser", userid);
	}
	
	
	
	
}
