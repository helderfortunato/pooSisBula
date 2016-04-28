package br.ufpb.sisbula;

import java.util.ArrayList;
import java.util.List;

public class Doenca extends IndicacaoMedicamento{
	List<Sintoma> sintomas;
	List<CausaDeDoenca> causa;
	public Doenca(String nome){
		super(nome);
		this.sintomas = new ArrayList<Sintoma>();
		this.causa = new ArrayList<CausaDeDoenca>();
	}
	public Doenca(String nome,List<Sintoma> s){
		super(nome);
		this.setSintomas(s);
	}
	public void setSintomas(List<Sintoma> sintomas){
		this.sintomas = sintomas;
	}
	public void setCaudaDeDoenca(List<CausaDeDoenca> causas){
		this.causa= causas;
	}
	public List <Sintoma> getSintomas(){
		return this.sintomas;
	}
	public List<CausaDeDoenca> getCausas(){
		return this.causa;
	}
	
	public void adicionaSintoma(Sintoma sint){
		this.sintomas.add(sint);
	}
	public void adcionaCausaDeDoenca(CausaDeDoenca causa){
		this.causa.add(causa);
	}
}
