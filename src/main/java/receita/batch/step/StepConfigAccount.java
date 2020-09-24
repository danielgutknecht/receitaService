package receita.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import receita.batch.entities.Account;
import receita.batch.listener.StepListenerInfo;
import receita.batch.processor.ItemProcessAccount;
import receita.batch.reader.ItemReaderAccount;
import receita.batch.tasklet.FileDeletingTasklet;
import receita.batch.write.WriteAccountFile;

@Component
public class StepConfigAccount {

	@Autowired
	private StepBuilderFactory stepBuilders;

	@Autowired
	private StepListenerInfo stepListenerInfo;

		
	@Bean
	public ItemReaderAccount itemReader() {
		return new ItemReaderAccount();
	}

	@Bean
	public ItemProcessAccount processor() {
		return new ItemProcessAccount();
	}

	@Bean
	public WriteAccountFile writeAccountFileItem() {
		return new WriteAccountFile();
	}
	
	@Bean
	public FileDeletingTasklet fileDeletingTasklet() {
		return new FileDeletingTasklet(new FileSystemResource("target/arquivo-input"));
	}

	@Bean
	public Step step1() {
		return stepBuilders.get("step1")
				.listener(stepListenerInfo)
				.<Account, Account>chunk(100)
				.reader(itemReader())
				.processor(processor())
				.writer(writeAccountFileItem())
				.build();
	}

	@Bean
	public Step step2() {
		return stepBuilders
				.get("step2")
				.tasklet(fileDeletingTasklet())
				.build();
	}

}
