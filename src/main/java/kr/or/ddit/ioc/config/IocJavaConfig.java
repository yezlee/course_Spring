package kr.or.ddit.ioc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.ioc.CollectionBean;
import kr.or.ddit.ioc.DbConfig;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.repository.UserDaoImpl;
import kr.or.ddit.user.service.UserServiceImpl;

//스프링 프레임 워크에게 해당 자바 파일이 스프링 설정 파일임을 알려준다. @Configuration 
@ImportResource("classpath:/kr/or/ddit/config/spring/datasource-context.xml")
@PropertySource(value = {"classpath:/kr/or/ddit/config/db/dbinfo.properties"})
@Configuration
public class IocJavaConfig {
	
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.userName}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String password;
	
	
	//메소드를 만드는데, 스프링 빈으로 만들 객체를 반환하는 메소드를 생성.
	//					메소드에 @Bean이라는 어노테이션을 적용 - 이거 붙이면 반환하는게 스프링객체로 반환
	//					@Bean 어노테이션에 별다른 옵션을 적용하지 않으면 생성된 스프링 빈의 이름은 메소드 이름으로 적용된다.
	//					@Bean어노테이션의 name속성을 통해 스프링 빈 이름 설정 가능. - repository이름이 클래스이름 따오던것처럼

	
	//<bean id ="userDao" class="kr.or.ddit.user.repository.UserDaoImpl"/> 
	@Bean
	public UserDao userDao() {
		return new UserDaoImpl();
	}
	
	
	/*
		<bean id="userService" class="kr.or.ddit.user.service.UserServiceImpl">
			<property name="userDao" ref="userDao"></property>
		</bean>
		
		
		UserService userService = new userServiceImpl();
		userService.serUserDao(userDao);
	*/
	@Bean
	public UserServiceImpl userService() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		
		return null;
	}
	
	
	/*
		<!--  생성자 주입  -->
		<!-- 2. 생성자 : 주입 받으려는 빈을 인자로 하는 생성자가 존재 해야함(xml, java설정파일) -->
		<bean id="userServiceCons" class="kr.or.ddit.user.service.UserServiceImpl">
			<constructor-arg ref="userDao"></constructor-arg>
		</bean>
		
		userServiceImpl에서 
		public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
		이게 아래랑 같은 맥락
	}
	 */
	@Bean
	public UserServiceImpl userServiceCons() {
		return new UserServiceImpl(userDao());
		
	}
	
	
	
	
	
	/*
		 <!-- prototype : 해당 빈을 dl, di 할때마다 매번 새롭게 만든 객체를 반환 -->
		<bean id="userServicePrototype" class="kr.or.ddit.user.service.UserServiceImpl" scope="prototype">
			<property name="userDao" ref="userDao"></property>
		</bean> 
	 */
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//	@Scope("prototype")
	@Bean
	public UserServiceImpl userServicePrototype() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		return userService;
	}
	
	
	
	
	/*
		 <bean id="collectionBean" class="kr.or.ddit.ioc.CollectionBean">
		<property name="list">
			<list>
				<value>brown</value>
				<value>sally</value>
				<value>cony</value>
			</list>
		</property> 
	 */
	
	public CollectionBean collectionBean() {
		CollectionBean collectionBean = new CollectionBean();
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("sally");
		list.add("cony");
		
		collectionBean.setList(list);
		return collectionBean;
	}
	
	
	
	
	/*
		 <context:property-placeholder location="classpath:/kr/or/ddit/config/db/dbinfo.properties"/>
		 이건 위에다가 설정해줌
		 
		<bean id="dbConfig" class="kr.or.ddit.ioc.DbConfig">
			<property name="driverClassName" value="${jdbc.driverClassName}"></property>
			<property name="url" value="${jdbc.url}"></property>
			<property name="userName" value="${jdbc.userName}"></property>
			<property name="password" value="${jdbc.password}"></property>
		</bean> 
	 */
	@Bean
	public DbConfig dbConfig() {
		DbConfig dbConfig = new DbConfig();
		dbConfig.setDriverClassName(driverClassName);
		dbConfig.setUrl(url);
		dbConfig.setUserName(userName);
		dbConfig.setPassword(password);
		
		return dbConfig;
	}
	
	
}