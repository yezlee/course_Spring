package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@org.aspectj.lang.annotation.Aspect // @Aspect 이렇게 안나온 이유는 클래스명과 같아서
public class Aspect {
	
	private static final Logger logger = LoggerFactory.getLogger(Aspect.class);
	
//	<aop:pointcut expression="execution(* kr.or.ddit..service.*.*(..))" id="servicePointCut"/>
//	위에 해당되는게 아래 어노테이션 - xml말고 어노테이션으로 aop 실행해보는방법 
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	
	//특정 메소드가 실행되기전에 실행되어야할 공통의 관심사항	
	//아래 어노테이션 달면 위에 더미 클래스의 포인트컷이 적용이된다.
	@Before("dummy()")
	public void beforeMethod(JoinPoint joinpoint) {
		logger.debug("Aspect.beforeMethod!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	
	//around : 특정 메소드 실행 전후
	// 	메소드 실행 전 - 공통 관심사항
	// 	메소드 원래 로직 - 반환타입이 있는지 없는지 몰라서 우선 있다고 한거야. object타입 - 원래 로직을 쓰는 시점을 알아야해서 근데 그걸 알수가 없으니까 리턴값을 있게한거 
	// 	메소드 실행 후 - 공통 관심사항
	
	//아래 어노테이션 달면 마찬가지로 더미클래스의 포인트컷이 적용됨
	@Around("dummy()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
		
		//메소드 본 로직 실행 전
		long startTime = System.nanoTime();
		
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		Object ret = joinPoint.proceed(joinPoint.getArgs());
		
		//메소드 본 로직 실행 후
		long endTime = System.nanoTime();
		
		logger.debug("{} {} : duration : {}", className, methodName, endTime-startTime);
		
		return ret;
	}
	
}
