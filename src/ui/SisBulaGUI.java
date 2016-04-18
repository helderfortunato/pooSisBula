package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.ufpb.sisbula.Doenca;
import br.ufpb.sisbula.Fabricante;
import br.ufpb.sisbula.Medicamento;
import br.ufpb.sisbula.MedicamentoInexistenteException;
import br.ufpb.sisbula.MedicamentoJaExisteException;
import br.ufpb.sisbula.Sintoma;
import br.ufpb.sisbula.SisBula;
import br.ufpb.sisbula.SisBulaMemory;

/**
 * Classe representando uma interface grÃ¡fica com Menu para o sistema SisBula
 * ObservaÃ§Ã£o: CÃ³digo baseado no Exemplo 8.2 do livro Java 7 Ensino DidÃ¡tico
 */

public class SisBulaGUI extends JFrame {
	
	private SisBula sisBula;
	private SisBulaMemory sisM;
	private Container painelDeConteudo;
	private JMenuBar barraDeMenu;
	private JMenu medicamentoMenu, creditosMenu, doencaMenu, sintomaMenu;
	private JMenuItem cadastraMed, pesquisaMed,removerMedi, creditos, listaMedi, pesquisaMedPDoenca,pesquisaMedPSintoma, pesquisaMediFabricante,
	cadastrarDoenca, removerDoenca, pesquisarDoenca,todasDoencas, cadastrarSintoma, removerSintoma, pesquisarSintoma, todosSintomas;
	private JButton salvarb;
	
	public SisBulaGUI(SisBula sis){
		this.sisBula = sis;
		this.sisM= new SisBulaMemory();
		inicializarComponentes();
		definirEventos();
	}

	
	private void inicializarComponentes(){
		setTitle("SisBula");
		setBounds(0,0,800,600);
		painelDeConteudo = getContentPane();
		barraDeMenu = new JMenuBar();
		medicamentoMenu = new JMenu("Medicamento");
		doencaMenu= new JMenu("Doença");
		sintomaMenu= new JMenu("Sintoma");
		creditosMenu = new JMenu("Creditos");

		salvarb= new JButton("Salvar");
		
		barraDeMenu.add(medicamentoMenu);
		barraDeMenu.add(doencaMenu);
		barraDeMenu.add(sintomaMenu);
		barraDeMenu.add(creditosMenu);
		barraDeMenu.add(salvarb);
		
		
		cadastraMed = new JMenuItem("Cadastrar medicamento");
		pesquisaMed = new JMenuItem("Pesquisar medicamento");
		listaMedi= new JMenuItem("Lista de Medicamentos");
		removerMedi= new JMenuItem("Remover medicamento cadastrado");
		pesquisaMedPDoenca= new JMenuItem("Pesquisar medicamentos para uma doença");
		pesquisaMedPSintoma= new JMenuItem("Pesquisar medicamentos para um sintoma");
		pesquisaMediFabricante = new JMenuItem("Pesquisa todos o medicamentos de determinado fabricante");
		cadastrarSintoma = new JMenuItem("Cadastar um sintoma");
		removerSintoma = new JMenuItem("Remover um sintoma cadastrado");
		pesquisarSintoma= new JMenuItem("Pesquisar um sintoma");
		todosSintomas= new JMenuItem("Ver todos os sintomas cadastrados");
		cadastrarDoenca= new JMenuItem("Cadastrar uma doença");
		removerDoenca= new JMenuItem("Remover uma doença cadastrada");
		pesquisarDoenca= new JMenuItem("Pesquisar uma doença");
		todasDoencas = new JMenuItem("ver todas as doenças cadastradas");
		
		
		medicamentoMenu.add(cadastraMed);
		medicamentoMenu.add(pesquisaMed);
		medicamentoMenu.add(listaMedi);
		medicamentoMenu.add(pesquisaMedPDoenca);
		medicamentoMenu.add(pesquisaMedPSintoma);
		medicamentoMenu.add(pesquisaMediFabricante);
		medicamentoMenu.add(removerMedi);
		doencaMenu.add(cadastrarDoenca);
		doencaMenu.add(pesquisarDoenca);
		doencaMenu.add(todasDoencas);
		doencaMenu.add(removerDoenca);
		sintomaMenu.add(cadastrarSintoma);
		sintomaMenu.add(pesquisarSintoma);
		sintomaMenu.add(todosSintomas);
		sintomaMenu.add(removerSintoma);
		creditos = new JMenuItem("Creditos");
		creditosMenu.add(creditos);
		setJMenuBar(barraDeMenu);
	}
	
	
	
	private void definirEventos(){
		cadastraMed.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Medicamento m = leDadosMedicamento();
				try {
					sisBula.cadastraMedicamento(m);
					JOptionPane.showMessageDialog(painelDeConteudo, "Cadastro efetuado com sucesso");
				} catch (MedicamentoJaExisteException e1) {
					JOptionPane.showMessageDialog(painelDeConteudo, e1.getMessage());
				}
			}
		});
		pesquisaMed.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nome = JOptionPane.showInputDialog("Qual o nome do medicamento a pesquisar?");
				Fabricante fab = leFabricante();
				try {
					Medicamento m  = sisBula.pesquisaMedicamento(nome, fab);
					JOptionPane.showMessageDialog(painelDeConteudo, "Medicamento encontrado:"+m.toString());
				} catch (MedicamentoInexistenteException e1) {
					JOptionPane.showMessageDialog(painelDeConteudo, e1.getMessage());
				}
			}
		});
		listaMedi.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				for(Medicamento m: sisBula.getMedicamentos().values()){
					System.out.println(m.getNome());
				}
			}
		});
		removerMedi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nome = JOptionPane.showInputDialog("Qual o nome do medicamento a remover:");
				sisM.removerMedi(nome);
			}
		});
		creditos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(painelDeConteudo, "Sistema feito para aula de POO de Ayla com base no exemplo 8.2 do livro Java 7 Ensino DidÃ¡tico");
			}
		});
		salvarb.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			sisM.sairDrogaria();
			JOptionPane.showMessageDialog(painelDeConteudo, "Todas as modificações foram salvas.");
		}
	});
		pesquisaMedPDoenca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nome = JOptionPane.showInputDialog("Para qual Doença você precisa do medicamento?");
				List<Medicamento> m  = sisM.pesquisaMedicamentosPara(sisM.pesquisarDoenca(nome));
				JOptionPane.showMessageDialog(painelDeConteudo, "Medicamentos encontrado:"+m);
			}
		});
		pesquisaMedPSintoma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nome= JOptionPane.showInputDialog("Para qual sintoma você precisa do medicamento?");
				List<Medicamento> m = sisM.pesquisaMedicamentosPara(sisM.pesquisarSintoma(nome));
				JOptionPane.showMessageDialog(painelDeConteudo, "Medicamentos encontrados:"+ m);
				
			}
		});
		pesquisaMediFabricante.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Fabricante f = leFabricante();
				JOptionPane.showMessageDialog(painelDeConteudo, "Todos os medicmaentos do fabricante "+sisM.pesquisaMedicamentosDoFabricante(f));
				

			}
		});
		cadastrarDoenca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sisM.cadastrarDoenca(leDadosDoenca());
				JOptionPane.showMessageDialog(painelDeConteudo, "Doenca cadastrada");
			}
		});
		cadastrarSintoma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sisM.cadastrarSintoma(leDadosSintoma());
				JOptionPane.showMessageDialog(painelDeConteudo, "Sintoma cadastrado");
			}
		});
		pesquisarDoenca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String d = JOptionPane.showInputDialog("Qual doenca deseja pesqusiar?");
				sisM.pesquisarDoenca(d);
			}
		});
		pesquisarSintoma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = JOptionPane.showInputDialog("Qual sintoma deseja pesquisar?");
				sisM.pesquisarSintoma(s);
			}
		});
		todasDoencas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(painelDeConteudo, "Doencas cadastradas"+sisM.getDoencas());
			}
		});
		todosSintomas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(painelDeConteudo, "Sintomas cadastrados"+sisM.getSintomas());
			}
		});
		removerDoenca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sisM.removerDoenca(leDadosDoenca());
				JOptionPane.showMessageDialog(painelDeConteudo, "Doença removida");
			}
		});
		removerSintoma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sisM.removerSintoma(leDadosSintoma());
				JOptionPane.showMessageDialog(painelDeConteudo, "Sintoma removido");
			}
		});
	}

	private Doenca leDadosDoenca(){
		String nome= JOptionPane.showInputDialog("Qual o nome da doenca?");
		return new Doenca(nome);
	}
	private Sintoma leDadosSintoma(){
		String nome = JOptionPane.showInputDialog("Qual o nome do sintoma?");
		return new Sintoma(nome);
	}
	private Medicamento leDadosMedicamento() {
		String nome = JOptionPane.showInputDialog("Qual o nome do medicamento?");
		Fabricante fabricante = leFabricante();
		return new Medicamento(nome,fabricante);
	}
	
	private Fabricante leFabricante(){
		String fabricante = JOptionPane.showInputDialog("Qual o fabricante?");
		if (fabricante.equalsIgnoreCase("MEDLEY")){
			return Fabricante.MEDLEY;
		} else if (fabricante.equalsIgnoreCase("EMS")){
			return Fabricante.EMS;
		} else if (fabricante.equalsIgnoreCase("EUROFARMA")){
			return Fabricante.EUROFARMA;
		} else {
			return Fabricante.GENERICO;
		}
	}
	
	public static void main(String [] args){
		SisBula sis = new SisBulaMemory();
		SisBulaGUI janelaPrincipal = new SisBulaGUI(sis);
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaPrincipal.setVisible(true);
	}
}
