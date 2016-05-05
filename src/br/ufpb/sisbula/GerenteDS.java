package br.ufpb.sisbula;

import java.util.LinkedList;
import java.util.List;

public class GerenteDS {
	private List<Doenca> doencas;
	private List<Sintoma> sintomas;
	private List<CausaDeDoenca> causas;
	public GerenteDS(){
		this.doencas= new LinkedList<Doenca>();
		this.sintomas = new LinkedList<Sintoma>();
		this.causas= new LinkedList<CausaDeDoenca>();
	}
	public boolean cadastrarDoenca(String d){
		Doenca d1= new Doenca(d);
		if(doencas.contains(d1)==false){
			doencas.add(d1);
			return true;
		}
		return false;
		
	}
	public boolean cadastrarSintoma(String s){
		Sintoma s1= new Sintoma(s);
		if(sintomas.contains(s1)==false){
			sintomas.add(s1);
			return true;
		}
		return false;
	}
	public boolean cadasstrarCausaDeDoenca(String causa){
		CausaDeDoenca c= new CausaDeDoenca(causa);
		if(causas.contains(c)==false){
			causas.add(c);
			return true;
		}
		return false;
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
	public boolean removerCausaDDoenca(CausaDeDoenca c){
		if(causas.contains(c)){
			causas.remove(c);
			return true;
		}
		return false;
	}
	public List<Doenca> getDoencas(){
		return this.doencas;
	}
	
	public List<Sintoma> getSintoma(){
		return this.sintomas;
	}
	public List<CausaDeDoenca> getCausaDeDoenca(){
		return this.causas;
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
	public void cadastraSintomaDeDoenca(String nomeDaDoenca, String sintoma){
		Sintoma s1 = this.pesquisarSintoma(sintoma);
		if(s1==null){
			this.cadastrarSintoma(sintoma);
			this.pesquisarDoenca(nomeDaDoenca).adicionaSintoma(this.pesquisarSintoma(sintoma));
		}else{
			this.pesquisarDoenca(nomeDaDoenca).adicionaSintoma(s1);
		}
	}
	public void cadastraPossivelCausaDeDoenca(String doenca, String possivelCausa){
		CausaDeDoenca cdd= new CausaDeDoenca(possivelCausa);
		Doenca d = this.pesquisarDoenca(doenca);
		d.adcionaCausaDeDoenca(cdd);
	}

	public List<Doenca> pesquisaDoencasCausadasPor(String fator) {
		List<Doenca> dd= new LinkedList<Doenca>();
		for(Doenca d: doencas){
			List<CausaDeDoenca> cd= d.getCausas();
			for(CausaDeDoenca c: cd){
				if(c.toString().equalsIgnoreCase(fator)){
					dd.add(d);
					break;
				}
			}
		}
		return dd;
	}
	public List<CausaDeDoenca>pesquisaPossiveisCausasDe(String doenca){
		List<CausaDeDoenca> cd= this.pesquisarDoenca(doenca).getCausas();
		return cd;
	}
}
