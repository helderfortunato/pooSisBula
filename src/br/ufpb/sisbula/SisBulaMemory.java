package br.ufpb.sisbula;



import java.util.List;
import java.util.Map;
/**
 * Descreve as funcionalidades de um sistema sobre medicamentos, doenças, sintomas.
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
	 * Cria uma variavel de instancia para a classe GerenteDS, classe responsavel pelo gerenciamento das doenças e sintomas.
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
	 * @throws MedicamentoJaExisteException Quando já existe
	 * um medicamento com o mesmo nome.
	 */
	public void cadastraMedicamento(Medicamento m) throws MedicamentoJaExisteException {
		gMedi.cadastraMedicamento(m);

	}
	
	/**
	 * Pesquisa um medicamento pelo nome e fabricante e retorna o mesmo.
	 * @param nome do medicamento
	 * @param fabricante do medicamento
	 * @throws MedicamentoInexistenteException caso o medicamento com esses @param não forem encontrados
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
	 * Retorna uma lista dos medicamentos indicados para doenças ou sintomas. 
	 * @param uma indicacaoMedicamento podendo ser :sintoma ou doençaa
	 * @return a lista dos medicamentos para o sintoma ou doença pesquisado
	 */
	public List<Medicamento> pesquisaMedicamentosPara(IndicacaoMedicamento ind) {
		return gMedi.pesquisaMedicamentosPara(ind);
	}
	
	/**
	 * Retorna todos os medicamentos já cadastrados em um Map<String, Medicamento>
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
	 * Cadastra uma nova doença
	 * @param d doença a ser cadastrada
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
	 * Remove uma doença na lista de doençcas cadastradas
	 * @param d doença a ser removida
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
	 * Retorna uma lista de todas as doenças cadastradas
	 * @return uma lista de doenças
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
	 * Recebe um string para fazer a pesquisa de uma doença pelo nome
	 * @param d o nome da doença
	 * @return uma doença ou null se a doença ainda não tiver sido cadastrada
	 */
	@Override
	public Doenca pesquisarDoenca(String d){
		return gDS.pesquisarDoenca(d);
	}
	/**
	 * Recebe um string para fazer a pesquisa de um sintoma pelo nome
	 * @param s nome do sintoma
	 * @return um sintoma ou null se o sintoma ainda não tiver sido cadastrada
	 */
	@Override
	public Sintoma pesquisarSintoma(String s){
		return gDS.pesquisarSintoma(s);
	}
	/**
	 * <<<<METODO INCOMPLETO>>>>
	 * Cadastra medicamento para uma doenca, se essa doenca não existir sera criada, caso o medicamento já exista essa doença sera adicionada. 
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
	 * Cadastra medicamento para um sintoma, se esse sintoma não existir sera criada, caso o medicamento já exista esse sintoma sera adicionada.
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
	 * Sai do sistema, salvando todas a modificaçoes feitas na instância atual.
	 * Todos os dados removidos e/ou temporarios serão perdidos definitivante. 
	 */
	public void sairDrogaria(){
		gMedi.sairDaDrogaria();
	}
}
