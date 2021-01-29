package kr.or.ddit.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//여기서 쓰는 패키지명의 패스는 스프링 빈이건 아니건 전혀 상관없어. 기능은 그냥 얘가 속한 패키지가 어딘지 그걸 아는거....?
//ConponentScanI라는 스캔을 위한 클래스를 만들기도 함 - 선생님 예
@ComponentScan(basePackages = {"kr.or.ddit"})
@Configuration
public class ComponetScanJavaConfig {

}
