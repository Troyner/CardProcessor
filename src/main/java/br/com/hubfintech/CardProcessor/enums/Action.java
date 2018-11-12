package br.com.hubfintech.CardProcessor.enums;

/**
 * Actions of a transaction
 * @author marcus.martins
 *
 */
public enum Action {

	WITHDRAW("withdraw"), DEPOSIT("deposit");
	
	private String valor;
	
	private Action(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
}
