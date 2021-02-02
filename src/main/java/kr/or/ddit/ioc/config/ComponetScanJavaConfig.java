package kr.or.ddit.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


//문자열로 전체 package 를 써줘도 되고, interface(해당 인터페이스가 속한 패키지 정보만 추출해서 )를 만들어서 (basePackage 용) 
//interface이름.class 로 써줘도 된다 
//여기서 쓰는 패키지명의 패스는 스프링 빈이건 아니건 전혀 상관없어. 기능은 그냥 얘가 속한 패키지가 어딘지 그걸 아는거....?
//ConponentScanI라는 스캔을 위한 클래스를 만들기도 함 - 선생님 예
@ImportResource("classpath:/kr/or/ddit/config/spring/datasource-context.xml")
@ComponentScan(basePackages = {"kr.or.ddit"})
@Configuration
public class ComponetScanJavaConfig {

}
