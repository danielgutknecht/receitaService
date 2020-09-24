package receita.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import receita.batch.listener.JobListenerInfo;
import receita.batch.step.StepConfigAccount;


@EnableBatchProcessing
@Configuration
public class JobConfigAccount {

	@Autowired
	private JobListenerInfo jobListenerInfo;	
	
	@Autowired
	private JobBuilderFactory jobBuilders;

	@Autowired
	private StepConfigAccount stepAccount;	
	
		
	@Bean
	public Job accountJob() {
		return jobBuilders
				.get("accountJob")
				.incrementer(new RunIdIncrementer())
				.listener(JobListenerFactoryBean.getListener(jobListenerInfo))
				.flow(stepAccount.step1())
				.next(stepAccount.step2())
				.end()
				.build();
	}	

}
