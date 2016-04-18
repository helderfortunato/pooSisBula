package br.ufpb.sisbula;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SisBulaMemoryTest {
	SisBulaMemory sis;
	
	@Before
	public void setUp() throws Exception {
		File f = new File("drogaria.txt");
		
		if (f.exists()){
			f.delete();
		}
		sis = new SisBulaMemory();
	}
	
	@After
	public void tearDown() throws Exception {
		File f = new File("drogaria.txt");
		if (f.exists()){
			f.delete();
		}
	}
	@Test
	public void testaPersistencia() throws MedicamentoJaExisteException{
		File f = new File("drogaria.txt");
		assertFalse(f.exists());
		sis.cadastraMedicamento(new Medicamento("Dipirona", Fabricante.EMS));
		sis.sairDrogaria();
		assertTrue(f.exists());
	}
	@Test
	public void testaGerentDS(){
		Doenca d1 = new Doenca("zika");
		sis.cadastrarDoenca(d1);
		List<Doenca> ld= sis.getDoencas();
		assertTrue(ld!=null);
		Sintoma s1= new Sintoma("dor de cabeça");
		sis.cadastrarSintoma(s1);
		List<Sintoma> ls = sis.getSintomas();
		assertTrue(ls!=null);
		System.out.println(sis.pesquisarDoenca("zika"));
		System.out.println(sis.pesquisarSintoma("dor de cabeça"));
	}
	
	@Test
	public void testaCadastroDeMedicamentoOK() {
		Medicamento m = new Medicamento("Novalgina");
		try {
			sis.cadastraMedicamento(m);
		} catch (Exception e){
			fail("Lançou exceçãoo sem necessidade");
		}	
	}
	
	
	@Test
	public void testaCadastroDeMedicamentoDuasVezes() {
		Medicamento m = new Medicamento("Novalgina");
		try {
			sis.cadastraMedicamento(m);
		} catch (Exception e){
			fail("Lançou exceção sem necessidade");
		}	
		
		try {
			sis.cadastraMedicamento(new Medicamento("Novalgina"));
			fail("Deveria ter lançado a exceção");
		} catch (MedicamentoJaExisteException e) {
			System.out.println("Muito bem, lançou a excecao direito");
		}
	}
	
	
	@Test
	public void testeDaProva(){
		SisBulaMemory sisBula = new SisBulaMemory();
		List<Medicamento> lista = sisBula.pesquisaMedicamentosDoFabricante(Fabricante.MEDLEY);
		assertEquals(0, lista.size());
		Medicamento dip = new Medicamento("Dipirona", Fabricante.MEDLEY);
		try {
			sisBula.cadastraMedicamento(dip);
		} catch (MedicamentoJaExisteException e){
			fail("Não deveria lançar exceção. Cadastro autorizado");
		}
		List<Medicamento> lista2 = sisBula.pesquisaMedicamentosDoFabricante(Fabricante.MEDLEY);
		assertEquals(1, lista2.size());
		assertTrue(lista2.get(0).getNome().equals("Dipirona"));
		Medicamento dip2 = new Medicamento("Dipirona",Fabricante.MEDLEY);
		try {
			sisBula.cadastraMedicamento(dip2);
			fail("Deveria ter lançado a exceção");
		} catch (MedicamentoJaExisteException e2){
			System.out.println("ExceÃ§Ã£o esperada");
		}
		
	}

}
