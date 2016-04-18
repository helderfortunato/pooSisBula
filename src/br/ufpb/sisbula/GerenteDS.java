package br.ufpb.sisbula;

import java.util.LinkedList;
import java.util.List;

public class GerenteDS {
	private List<Doenca> doencas;
	private List<Sintoma> sintomas;
	
	public GerenteDS(){
		this.doencas= new LinkedList<Doenca>();
		this.sintomas = new LinkedList<Sintoma>();
	}
	
	public void cadastrarDoenca(String d){
		Doenca d1= new Doenca(d);
		if(doencas.contains(d1)==false){
			doencas.add(d1);
		}
		
	}
	public void cadastrarSintoma(String s){
		Sintoma s1= new Sintoma(s);
		if(sintomas.contains(s1)==false){
			sintomas.add(s1);
		}
	}
	public boolean cadastrarDoenca(Doenca d){
		if(doencas.contains(d)==false){
			doencas.add(d);
			return true;
		}else{
			return false;
		}
	}
	public boolean cadastrarSintoma(Sintoma s){
		if(sintomas.contains(s)==false){
			sintomas.add(s);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removerDoenca(Doenca d){
		if(doencas.contains(d)){
			doencas.remove(d);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removerSintoma(Sintoma s){
		if(sintomas.contains(s)){
			sintomas.remove(s);
			return true;
		}else{
			return false;
		}
	}
	public List<Doenca> getDoencas(){
		return this.doencas;
	}
	
	public List<Sintoma> getSintoma(){
		return this.sintomas;
	}
	public Doenca pesquisarDoenca(String d){
		for(Doenca dd: doencas){
			if(dd.getNome().equalsIgnoreCase(d)){
				return dd;
			}
		}
		return null;
	}
	
	public Sintoma pesquisarSintoma(String s){
		for(Sintoma ss: sintomas){
			if(ss.getNome().equalsIgnoreCase(s)){
				return ss;
			}
		}
		return null;
		
	}
	public IndicacaoMedicamento pesquisaDS(String nome){
		if(this.pesquisarDoenca(nome)!=null){
			return this.pesquisarDoenca(nome);
		}else if(this.pesquisarSintoma(nome)!=null){
			return this.pesquisarSintoma(nome);
		}else{
			return null;
		}
	}
}
