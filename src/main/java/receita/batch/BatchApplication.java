package receita.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import receita.batch.listener.ExecutorJob;


@SpringBootApplication
public class BatchApplication implements CommandLineRunner {	
	

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}
	
	
	
	@Autowired
	private ExecutorJob executorJob; 
	 
	@Override
    public void run(String... args) throws Exception {
		executorJob.execute();
    }
    
}
