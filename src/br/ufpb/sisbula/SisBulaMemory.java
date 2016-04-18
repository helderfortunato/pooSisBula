package br.ufpb.sisbula;



import java.util.List;
import java.util.Map;
/**
 * Descreve as funcionalidades de um sistema sobre medicamentos, doen�as, sintomas.
 * 
 * @author helder
 *
 */
public class SisBulaMemory implements SisBula {
	/**
	 * Cria uma variavel de instancia para a classe GerenteMedi, classe responsavel pelo gerenciamento dos medicamentos.
	 */
	private GerenteMedi gMedi;
	
	/**
	 * Cria uma variavel de instancia para a classe GerenteDS, classe responsavel pelo gerenciamento das doen�as e sintomas.
	 */
	private GerenteDS gDS;
	
	public SisBulaMemory() {
		this.gMedi = new GerenteMedi();
		this.gDS = new GerenteDS();
	}
	@Override
	/**
	 * Cadastra um novo medicamento no sistema 
	 * @param m O novo medicamento a ser cadastrado.
	 * @throws MedicamentoJaExisteException Quando j� existe
	 * um medicamento com o mesmo nome.
	 */
	public void cadastraMedicamento(Medicamento m) throws MedicamentoJaExisteException {
		gMedi.cadastraMedicamento(m);

	}
	
	/**
	 * Pesquisa um medicamento pelo nome e fabricante e retorna o mesmo.
	 * @param nome do medicamento
	 * @param fabricante do medicamento
	 * @throws MedicamentoInexistenteException caso o medicamento com esses @param n�o forem encontrados
	 * @return um medicamento
	 */
	@Override
	public Medicamento pesquisaMedicamento(String nome, Fabricante fabricante) throws MedicamentoInexistenteException {
		return gMedi.pesquisaMedicamento(nome, fabricante);
	}
	/**
	 * Pesquisa todos o medicamentos de determinado fabricante
	 * @param fab fabricante a ser pesquisado
	 * @return lista de medicamento
	 */
	public List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab){
		return gMedi.pesquisaMedicamentosDoFabricante(fab);
	}
	
	@Override
	/**
	 * Retorna uma lista dos medicamentos indicados para doen�as ou sintomas. 
	 * @param uma indicacaoMedicamento podendo ser :sintoma ou doen�aa
	 * @return a lista dos medicamentos para o sintoma ou doen�a pesquisado
	 */
	public List<Medicamento> pesquisaMedicamentosPara(IndicacaoMedicamento ind) {
		return gMedi.pesquisaMedicamentosPara(ind);
	}
	
	/**
	 * Retorna todos os medicamentos j� cadastrados em um Map<String, Medicamento>
	 * @return um map string, medicamento
	 */
	@Override
	public Map<String, Medicamento> getMedicamentos(){
		return gMedi.getMedicamentos();
	}
	/**
	 * Remove um medicamento da lista de medicamentos cadastrados
	 * @param nomeMedi nome do medicamento a ser removido
	 */
	@Override
	public void removerMedi(String nomeMedi){
		gMedi.removerMedi(nomeMedi);
	}
	/**
	 * Cadastra uma nova doen�a
	 * @param d doen�a a ser cadastrada
	 * @return um boolean
	 */
	@Override
	public boolean cadastrarDoenca(Doenca d){
		return gDS.cadastrarDoenca(d);
	}
	/**
	 * Cadastra um novo Sintoma
	 * @param s sintoma a ser cadastrado
	 * @return um boolean
	 */
	@Override
	public boolean cadastrarSintoma(Sintoma s){
		return gDS.cadastrarSintoma(s);
	}
	/**
	 * Remove uma doen�a na lista de doen�cas cadastradas
	 * @param d doen�a a ser removida
	 * @return um boolean
	 */
	@Override
	public boolean removerDoenca(Doenca d){
		return gDS.removerDoenca(d);
	}
	/**
	 * Remove um sintoma da lista de sintomas cadastrados
	 * @param s sintoma a ser removido
	 * @return um boolean
	 */
	@Override
	public boolean removerSintoma(Sintoma s){
		return gDS.removerSintoma(s);
	}
	/**
	 * Retorna uma lista de todas as doen�as cadastradas
	 * @return uma lista de doen�as
	 */
	@Override
	public List<Doenca> getDoencas(){
		return gDS.getDoencas();
	}
	/**
	 * Retorna uma lista de todos os sintomas cadastrados
	 * @return uma lista de sintomas
	 */
	@Override
	public List<Sintoma> getSintomas(){
		return gDS.getSintoma();
	}
	/**
	 * Recebe um string para fazer a pesquisa de uma doen�a pelo nome
	 * @param d o nome da doen�a
	 * @return uma doen�a ou null se a doen�a ainda n�o tiver sido cadastrada
	 */
	@Override
	public Doenca pesquisarDoenca(String d){
		return gDS.pesquisarDoenca(d);
	}
	/**
	 * Recebe um string para fazer a pesquisa de um sintoma pelo nome
	 * @param s nome do sintoma
	 * @return um sintoma ou null se o sintoma ainda n�o tiver sido cadastrada
	 */
	@Override
	public Sintoma pesquisarSintoma(String s){
		return gDS.pesquisarSintoma(s);
	}
	/**
	 * <<<<METODO INCOMPLETO>>>>
	 * Cadastra medicamento para uma doenca, se essa doenca n�o existir sera criada, caso o medicamento j� exista essa doen�a sera adicionada. 
	 * @param m Nome do medicamento
	 * @param doenca para ser cadastrada no medicamento
	 */
	@Override
	public void cadastrarMedicamentoParaDoenca(String m, String doenca){
		if(gDS.pesquisaDS(doenca)==null){
			gDS.cadastrarDoenca(doenca);	
		}
		gMedi.cadastrarMedicamentoParaDoenca(m, gDS.pesquisarDoenca(doenca));
	}
	/**
	 * <<<<METODO INCOMPLETO>>>>
	 * Cadastra medicamento para um sintoma, se esse sintoma n�o existir sera criada, caso o medicamento j� exista esse sintoma sera adicionada.
	 * @param m nome do medicamento
	 * @param sintoma para ser cadastrada no medicamento
	 */
	@Override
	public void cadastrarMedicamentoParaSintoma(String m, String sintoma){
		if(gDS.pesquisaDS(sintoma)==null){
			gDS.cadastrarSintoma(sintoma);	
		}
		gMedi.cadastrarMedicamentoParaSintoma(m, gDS.pesquisarSintoma(sintoma));
	}
	/**
	 * Sai do sistema, salvando todas a modifica�oes feitas na inst�ncia atual.
	 * Todos os dados removidos e/ou temporarios ser�o perdidos definitivante. 
	 */
	public void sairDrogaria(){
		gMedi.sairDaDrogaria();
	}
}
