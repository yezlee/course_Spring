package kr.or.ddit.user.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.DbConfig;
import kr.or.ddit.ioc.config.IocJavaConfig;
import kr.or.ddit.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IocJavaConfig.class})
public class IocJavaConfigTest {

	
	@Resource(name="userServiceCons")
	private UserService userServiceCons;
	
	@Resource(name="userService")
	private UserService userService;

	@Resource(name="userService")
	private UserService userService2;
	
	@Resource(name="userServicePrototype")
	private UserService userServicePrototype;
	
	@Resource(name="userServicePrototype")
	private UserService userServicePrototype2;
	
	@Resource(name="dbConfig")
	private DbConfig dbconfig;
	
	
	
	
	
	//userServiceCons ������ ���� ���������� �����Ǿ����� �׽�Ʈ
	@Test
	public void UserServiceConstest() {
		
		assertNotNull(userServiceCons);
	}
	
	
	@Test
	public void beanScopeTest() {
		
		//������ ������ �̱��� �������� ���� �ΰ��� ��ü�� �� Ŭ������ ���� �������Ƿ� �����ؾ���
		//������ �������� �̱��� ������ bean ������Ʈ�� �������� �ϳ��� ��ü�� �����ȴ�. �׷��� not equals
		assertNotEquals(userService, userServiceCons);		
		
	}
	
	@Test
	public void beanScopeTest2() {
		
		//������ ������ ���� ���Թ޾����Ƿ� userService, userService2�� ���� ��ü��.
		assertEquals(userService, userService2);
	}
	
	
	@Test
	public void beanScopePrototypeTest() {
		
		//������ userServiceProtytype �� ����(scope : prototype)
		assertNotEquals(userServicePrototype, userServicePrototype2);
	}
	
	
	@Test
	public void propertyPlaceholderTest() {
		assertNotNull(dbconfig);
		assertEquals("yez", dbconfig.getUserName());
		assertEquals("java", dbconfig.getPassword());
		assertEquals("jdbc:oracle:thin:@112.220.114.130:1521:xe", dbconfig.getUrl());
		assertEquals("oracle.jdbc.driver.OracleDriver", dbconfig.getDriverClassName());
	}
	
	
}
	
