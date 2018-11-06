package br.com.hubfintech.CardProcessor.enums;

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
