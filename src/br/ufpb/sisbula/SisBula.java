package br.ufpb.sisbula;

import java.util.List;
import java.util.Map;

/**
 * Descreve as funcionalidades de um sistema de informações
 * sobre medicamentos
 * 
 * @author ayla
 *
 */
public interface SisBula {
	/**
	 * Cadastra um novo medicamento no sistema 
	 * @param m O novo medicamento a ser cadastrado.
	 * @throws MedicamentoJaExisteException Quando já¡ existe
	 * um medicamento com o mesmo nome do medicamento a ser
	 * cadastrado.
	 */
	public void cadastraMedicamento(Medicamento m) throws MedicamentoJaExisteException;
	
	/**
	 * Retorna uma lista dos medicamentos indicados para certas
	 * doenÃ§as ou sintomas. 
	 * @param ind Um sintoma ou doençaa
	 * @return a lista dos medicamentos para o sintoma
	 * ou doença pesquisado
	 */
	public List<Medicamento> pesquisaMedicamentosPara(String nome);
	public Map<String, Medicamento> getMedicamentos();
	public Medicamento pesquisaMedicamento(String nome, Fabricante fabricante) throws MedicamentoInexistenteException;

	public abstract void cadastrarMedicamentoParaSintoma(String m,
			String sintoma) throws MedicamentoInexistenteException;

	public abstract void cadastrarMedicamentoParaDoenca(String m,
			String doenca) throws MedicamentoInexistenteException;

	public abstract Sintoma pesquisarSintoma(String s);

	public abstract Doenca pesquisarDoenca(String d);

	public abstract List<Sintoma> getSintomas();

	public abstract List<Doenca> getDoencas();

	public abstract boolean removerSintoma(Sintoma s);

	public abstract boolean removerDoenca(Doenca d);

	public abstract boolean cadastrarSintoma(String s);

	public abstract boolean cadastrarDoenca(String d);

	public abstract void removerMedi(String nomeMedi);

	List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab);
}
