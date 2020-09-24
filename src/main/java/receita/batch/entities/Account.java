package receita.batch.entities;


public class Account {

	private String agencia;
	private String conta;
	private String saldo;
	private String status;
	private Boolean resultadoReceita;

	
	public Account() {
		
	}

	public Account(String agencia, String conta, String saldo, String status, Boolean resultadoReceita) {
		super();
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldo;
		this.status = status;
		this.resultadoReceita = resultadoReceita;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}
	
	//Receber conta sem hifen
	public String getContaSemHifen() {
		return this.getConta().replaceAll("-", "");
	}

	public String getSaldo() {
		return saldo;
	}

	//receber saldo formatado
	public String getSaldoFormatadoString() {
		return String.format("%.2f", getSaldo()).replace(',', '.');
	}
	
	

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getResultadoReceita() {
		return resultadoReceita;
	}

	public void setResultadoReceita(Boolean resultadoReceita) {
		this.resultadoReceita = resultadoReceita;
	}	
	
}
