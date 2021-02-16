package kr.or.ddit.batch.ranger;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RangerScheduler {

	private static final Logger logger = LoggerFactory.getLogger(RangerScheduler.class);

	//DI
	@Resource(name = "jobLauncher")
	private JobLauncher jobLauncher;
	
	@Resource(name = "rangersJob")
	private Job job;
	
	public void rangerTask() {
		
		
		
		try {
			jobLauncher.run(job, new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | 
				JobInstanceAlreadyCompleteException| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		
	}
	
	
//	public void logging() {
//		logger.debug("rangerScheduler.logging()");
//	}
	
	
	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(
						new String[] {
							"classpath:/kr/or/ddit/config/spring/batch-context.xml",
							"classpath:/kr/or/ddit/config/spring/scheduler-context.xml"}
					);
		
		
		
		
		
		
		//이렇게하면 되긴되는데, 이걸 안쓰는, 못쓰는 이유가
		//<bean id="rangerScheduler" class="kr.or.ddit.batch.ranger.RangerScheduler"/>
		//이건 스프링에서 한거고
		//아래는 내가 직접 만든거
		//둘이 다른거야
//		RangerScheduler rangerScheduler = new RangerScheduler();
//		rangerScheduler.jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
//		rangerScheduler.job = context.getBean("rangersJob", Job.class);
		
	}
}
