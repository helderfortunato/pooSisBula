package br.ufpb.sisbula;

import java.util.ArrayList;
import java.util.List;

public class Doenca extends IndicacaoMedicamento{
	List<Sintoma> sintomas;
	public Doenca(String nome){
		super(nome);
		this.sintomas = new ArrayList<Sintoma>();
	}
	public Doenca(String nome,List<Sintoma> s){
		super(nome);
		this.setSintomas(s);
	}
	public void setSintomas(List<Sintoma> sintomas){
		this.sintomas = sintomas;
	}
	public List <Sintoma> getSintomas(){
		return this.sintomas;
	}
	
	public void adicionaSintoma(Sintoma sint){
		this.sintomas.add(sint);
	}
}
