package kr.or.ddit.user.repository;


import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;


// <bean id="" class=""  근데 클래스는 이미뭔지 알아. 아이디가 문제
// @Repository 에서 별다른 설정을 하지 않으면 스프링 빈 이름으로 class이름에서 첫글자를 소문자로 한 문자열이 스프링 빈 이름으로 설정된다.
// UserDaoImpl ==> userDaoImpl


// 네이밍 : 인터페이스는 UserDao 구현하는클래스 UserDaoImpl ==> @Resource(name="userDaoImpl")
// 근데 예전에 인터페이스를 UserDaoI 로 하고 구현클래스를 UserDao로 한적 있었어 이걸 바꿔주려면 이름을 지정해주면돼 @Repository("userDao") 이렇게 하면 테스트에다가 @Resource(name="userDao")

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Override
	public UserVo getUser(String userid) {
		//원래는 데이터베이스에서 조회를 해야하나, 개발 초기단계라 설정이 완료되지 않음, 현재 확인하려고 하는 기능은 스프링 컨테이너에 초점을 맞추기위해
		//new 연산자를 통해 생성한 vo객체를 반환
		
		return new UserVo("brown", "브라운");
		
	}
}
