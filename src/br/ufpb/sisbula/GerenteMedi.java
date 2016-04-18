package br.ufpb.sisbula;


import gravadores.arquivo.GravadorMedi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenteMedi {
	private Map<String, Medicamento> medicamentos;
	private GravadorMedi gravador = new GravadorMedi();
	
	
	public GerenteMedi() {
		try{
			this.medicamentos= gravador.leMedi();
		}catch(IOException e){
			System.out.println("Não existe medicamento cadastrado ainda. Iniciando nova lista de medicamentos");
			this.medicamentos = new HashMap<String,Medicamento>();
		}
	}
	
	public void cadastraMedicamento(Medicamento m) throws MedicamentoJaExisteException {
		Medicamento med = this.medicamentos.get(m.getId());
		if (med!=null){
			throw new MedicamentoJaExisteException("Já existe medicamento com este nome:" + m.getNome());
		} else {
			this.medicamentos.put(m.getId(), m);
		}

	}

	public Medicamento pesquisaMedicamento(String nome, Fabricante fabricante) throws MedicamentoInexistenteException {
		String id = nome+fabricante.toString();
		Medicamento med = this.medicamentos.get(id);
		
		if (med!=null) {
				return med;
		} else {
			throw new MedicamentoInexistenteException("Não existe medicamento com este nome:" + nome);
	
		}
	}
	
	public List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab){
		List<Medicamento> lista = new ArrayList<Medicamento>();
		for (Medicamento m: this.medicamentos.values()){
			if (m.getFabricante().equals(fab)){
				lista.add(m);
			}
		}
		return lista;
	}

	public List<Medicamento> pesquisaMedicamentosPara(IndicacaoMedicamento ind) {
		List <Medicamento> lista = new ArrayList<Medicamento>();
		for (Medicamento m: this.medicamentos.values()){
			for (IndicacaoMedicamento i: m.getIndicacoes()){
				if (i.getNome().equals(ind.getNome())){
					lista.add(m);
					break;
				}
			}
		}
		return lista;
	}
	
	public Map<String, Medicamento> getMedicamentos(){
		return this.medicamentos;
	}
	/**
	 * Pode ser acressentado a esse metodo a opção de verificar se o medicamento a ser removido pertence ao fabricante f ou ff em caso de nomes iguals.
	 * @param nomeMedi
	 */
	public void removerMedi(String nomeMedi){
		for(Medicamento m: medicamentos.values() ){
			if(m.getNome().equalsIgnoreCase(nomeMedi)){
				this.medicamentos.remove(nomeMedi);
			}
		}
	}
	public void cadastrarMedicamentoParaDoenca(String m, Doenca doenca){
		//TODO Não entendi bem essa parte.
	}
	public void cadastrarMedicamentoParaSintoma(String m, Sintoma sintoma){
		//TODO Não entendi bem essa parte.
	}
	public void sairDaDrogaria(){
		try{
			this.gravador.gravarMedi(medicamentos);
		}catch(IOException e){
			System.err.println(e.getMessage());
		}
	}
	
}
