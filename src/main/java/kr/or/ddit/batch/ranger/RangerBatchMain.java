package kr.or.ddit.batch.ranger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RangerBatchMain {
	public static void main(String[] args) {

		// spring container == IOC container == container
		ApplicationContext context =
				new ClassPathXmlApplicationContext(
						new String[] {
							"classpath:/kr/or/ddit/config/spring/batch-context.xml",
							"classpath:/kr/or/ddit/config/spring/scheduler-context.xml"}
					);
			
		


		// job 실행하기위해서는 두가지가 있음됨 - jobLauncher, job
		// DI, DL - 이게 DL
//		JobLauncher jobLauncher = (JobLauncher)context.getBean("jobLauncher");
		//위방법이랑 아래방법이랑 동일하다.
		//위처럼 형변환을 해줘도 되고, 그 형을 같이 받아도된다. 파라미터로? 
		JobLauncher jobLauncher = context.getBean("jobLauncher" , JobLauncher.class);
		Job job = context.getBean("rangersJob", Job.class);
		
		try {
			jobLauncher.run(job, new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		

	}
}
