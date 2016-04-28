package br.ufpb.sisbula;

public class CausaDeDoenca {
	private String descricao;
	
	public CausaDeDoenca(String descricao){
		this.descricao=descricao;
	}
	public String toString(){
		return this.descricao;
	}

}
