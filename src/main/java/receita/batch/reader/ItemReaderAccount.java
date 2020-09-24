package receita.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import receita.batch.entities.Account;


@Component
public class ItemReaderAccount extends FlatFileItemReader<Account> {

	public ItemReaderAccount() {
		setName("itemReaderAccount");
		setResource(new FileSystemResource("./arquivos/arquivo-input/contas.csv"));
		setLinesToSkip(1);
		setLineMapper(lineMapper());
	}
	

	@Bean
	public LineMapper<Account> lineMapper() {
		DefaultLineMapper<Account> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(lineTokenizer());
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper());

		return defaultLineMapper;

	}

	@Bean
	public DelimitedLineTokenizer lineTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(";");
	    lineTokenizer.setStrict(false);
	    lineTokenizer.setNames(new String[] {"agencia", "conta", "saldo", "status"});
	    
		return lineTokenizer;

	}

	@Bean
	public BeanWrapperFieldSetMapper<Account> beanWrapperFieldSetMapper(){
		BeanWrapperFieldSetMapper<Account> bwfm = new BeanWrapperFieldSetMapper<>();
		bwfm.setTargetType(Account.class);
		
		return bwfm;		
	}


}
