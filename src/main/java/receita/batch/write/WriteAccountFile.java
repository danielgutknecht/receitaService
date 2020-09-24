package receita.batch.write;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import receita.batch.entities.Account;

@Component
public class WriteAccountFile extends FlatFileItemWriter<Account> {

	public WriteAccountFile() {
		setName("contasProcessadas.csv");
		setResource(new FileSystemResource("./arquivos/arquivo-output/contas.csv"));
		setAppendAllowed(true);
		setLineAggregator(lineAggregator());
	}
	
	@Bean
	public BeanWrapperFieldExtractor<Account> fieldExtractor() {
		BeanWrapperFieldExtractor<Account> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] { "agencia", "conta", "saldo", "status", "resultadoReceita" });
		fieldExtractor.afterPropertiesSet();
		return fieldExtractor;

	}

	@Bean
	public DelimitedLineAggregator<Account> lineAggregator() {
		DelimitedLineAggregator<Account> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(";");
		lineAggregator.setFieldExtractor(fieldExtractor());
		return lineAggregator;

	}


}
