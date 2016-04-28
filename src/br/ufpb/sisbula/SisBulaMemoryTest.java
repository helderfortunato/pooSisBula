package br.ufpb.sisbula;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SisBulaMemoryTest {
	SisBulaMemory sis;
	@Before
	public void setUp() throws Exception {
		sis = new SisBulaMemory();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testaCadastroDeMedicamentoOK() {
		Medicamento m = new Medicamento("Novalgina");
		try {
			sis.cadastraMedicamento(m);
		} catch (Exception e){
			fail("Lançou exceção sem necessidade");
		}	
	}

	
	@Test
	public void testaCadastroDeMedicamentoOK2() {
		try {
			Medicamento m1= new Medicamento("Novalgina");
			sis.cadastraMedicamento(m1);
			Medicamento m = sis.pesquisaMedicamento("Novalgina", Fabricante.GENERICO);
			assertEquals("Novalgina", m.getNome());
			assertEquals(Fabricante.GENERICO, m.getFabricante());
		} catch (Exception e){
			fail("Lançou exceção sem necessidade");
		}	
	}

	@Test
	public void testaPesquisaDeMedicamento(){
		try {
			Medicamento m1= new Medicamento("Novalgina");
			sis.cadastraMedicamento(m1);
			Medicamento m = sis.pesquisaMedicamento("Novalgina", Fabricante.GENERICO);
			assertEquals("Novalgina", m.getNome());
			assertEquals(Fabricante.GENERICO, m.getFabricante());
		} catch (Exception e){
			fail("Lançou exceção sem necessidade");
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
		
		SisBula sisBula = new SisBulaMemory();
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
			System.out.println("Exceção esperada");
		}
		
	}
	
//	@Test
//	public void testaCadastroIndicacoesMedicamento() throws Exception {
//		Medicamento mn = new Medicamento("Dipirona");
//		sis.cadastrarDoenca("Zika");
//		sis.cadastraMedicamento(mn);
//		sis.cadastrarSintoma("Febre");
//		sis.cadastrarMedicamentoParaDoenca("Dipirona","Zika");
//		sis.cadastrarMedicamentoParaSintoma("Dipirona","Febre");
//		List<Medicamento> remediosPraZika = sis.pesquisaMedicamentosPara("Zika");
//		List<Medicamento> remediosPraFebre = sis.pesquisaMedicamentosPara("Febre");
//		assertEquals(1, remediosPraZika.size());
//		assertEquals(1, remediosPraFebre.size());
//		Medicamento m1 = remediosPraZika.get(0);
//		Medicamento m2 = remediosPraFebre.get(0);
//		assertEquals("Dipirona", m1.getNome());
//		assertEquals("Dipirona", m2.getNome());
//		
//		
//		
//	}
	
	@Test
	public void testaCadastroDoenca(){
		sis.cadastrarDoenca("Alzheimer");
		sis.cadastraSintomaDeDoenca("Alzheimer","perda da memória");
		sis.cadastraSintomaDeDoenca("Alzheimer","distúrbios de comportamento");
		sis.cadastraPossivelCausaDeDoenca("Alzheimer","Sedentarismo");
		sis.cadastraPossivelCausaDeDoenca("Alzheimer","Falta de exercícios mentais");
		List <Doenca> doencas = sis.pesquisaDoencasCausadasPor("Sedentarismo");
		assertTrue(doencas.size()==1);
		assertEquals("Alzheimer", doencas.get(0).getNome());
		List<CausaDeDoenca> possiveisCausas = sis.pesquisaPossiveisCausasDe("Alzheimer");
		assertEquals(2, possiveisCausas.size());
		assertTrue(!possiveisCausas.get(0).equals(possiveisCausas.get(1)));
		assertTrue(possiveisCausas.get(0).toString().equals("Sedentarismo")||possiveisCausas.get(0).toString().equals("Falta de exercícios mentais"));
		assertTrue(possiveisCausas.get(1).toString().equals("Sedentarismo")||possiveisCausas.get(1).toString().equals("Falta de exercícios mentais"));
				
		
	}

}
