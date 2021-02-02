package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

//@ContextConfiguration(classes = {ComponetScanJavaConfig.class})
@ContextConfiguration("classpath:/kr/or/ddit/ioc/component-scan.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentScanJavaTest extends WebTestConfig{

	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="userService")
	private UserService userService;
	
	//@Repository 어노테이션을 적용한 userDaoImpl 스프링 빈이 정상적으로 컨테이너에 등록 되었는지 확인
	@Test
	public void userDaoImplSpringBeanTest() {
		assertNotNull(userDao);
		
		UserVo userVo = userDao.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}

	
	
	//userServiceImpl 스프링 빈이 정삭적으로 컨테이너에 등록 되었는지 확인
	@Test
	public void userServiceImplSpringBeanTest() {
		assertNotNull(userService);
		
		UserVo userVo = userService.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}

	
}
