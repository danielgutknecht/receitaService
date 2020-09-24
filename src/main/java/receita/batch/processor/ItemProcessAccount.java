package receita.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import receita.batch.api.ReceitaService;
import receita.batch.entities.Account;

@Component
public class ItemProcessAccount implements ItemProcessor<Account, Account> {

	@Bean
	public ReceitaService getReceitaService() {
		return new ReceitaService();
	}

	@Override
	public Account process(Account conta) throws Exception {
			
		try {
			boolean answer = getReceitaService().atualizarConta(
					conta.getAgencia(), 
					conta.getContaSemHifen(),
					Double.parseDouble(conta.getSaldo().replace(",", ".")), 
					conta.getStatus());

			conta.setResultadoReceita(answer);

			System.out.println("Arquivo enviado com sucesso!");

		} catch (Exception e) {
			System.out.println("Erro ao enviar arquivo: " + e.getMessage());
		}
		return conta;
	}

	
}
