package kr.or.ddit.batch.ranger;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({"classpath:/kr/or/ddit/config/spring/batch-context.xml",
					   "classpath:/kr/or/ddit/config/spring/scheduler-context.xml",
					   "classpath:/kr/or/ddit/config/spring/batch-test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RangerBatchTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils; 
	
	@Test
	public void rangerTaskTest() throws Exception {
		//launcher랑 job을 실행하야하는데 잡은 보이지가 않아
		//==> batch테스트 실행시 job 타입으로 등록된 스프링 빈은 하나여야 하고 *** (이걸 꼭 지켜야한다.)
		//job 이름을 명시하지 않아도 container에 하나의 배치 job이 등록되어 있다는 가정이 있기떄문에 별도로 job이름을 명시하지 않는다.
		JobExecution execution =  jobLauncherTestUtils.launchJob();
		
		//ExitStatus.COMPLETED 이게 expected 값
		//execution.getExitStatus() 이게 실제값
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
	}

}
