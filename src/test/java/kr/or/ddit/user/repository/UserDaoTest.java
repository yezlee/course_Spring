package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

//테스트는 메인메소드가 없는데 실행된다. 어떻게????????? 
//==> 어딘가에 메인을 실행해주는 게 있다.
//이건 워낙에 자주 쓰는거라서 이클립스에서 자체적으로 만들어놓음
// eclipse, maven - junit이 메인메소드가 없는데 실행이 되는이유가 저 두개에서 메인메소드를 실행해주니까

//스프링 환경에서 jUnit 코드를 실행 ==> jUnit코드도 스프링 빈으로 등록(스프링빈으로 등록이 되어있어야 주입이 가능하다!!!!!)
/*@RunWith(SpringJUnit4ClassRunner.class)
//설정정보를 알려주는 어노테이션
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")*/
public class UserDaoTest extends ModelTestConfig {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Test
	public void getUserTest() {
		/*** Given ***/
		String userid = "brown";

		/*** When ***/
		UserVo userVo = userDao.selectUser(userid);

		/*** Then ***/
		assertEquals("브라운", userVo.getUsernm());
	}

}
