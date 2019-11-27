package testes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import composite.Classe;
import composite.ComponenteComposto;
import composite.Controller;
import composite.Diagrama;
import composite.DiagramasSalvos;
import composite.Interface;

public class TC10 {

	@Test
	public void testDesfazer() {
		Diagrama d = new Diagrama("d1");
		assertNotNull(d.getNome());
		assertEquals(d.getNome(), "d1");
		
		Classe c = new Classe("c1");
		assertNotNull(c.getNome());
		assertEquals(c.getNome(), "c1");
		
		Classe c2 = new Classe("c2");
		assertNotNull(c2.getNome());
		assertEquals(c2.getNome(), "c2");
		
		Interface in = new Interface("ni");
		assertNotNull(in.getNome());
		assertEquals(in.getNome(), "ni");
		
		//save do diagrama vazio
		Controller.elementoCompostoAtual = d;
		Controller.salvarElemento(d);
		
		d.addFilho(c);
		//save do diagrama com as classe c 
		Controller.salvarElemento(d);
		d.addFilho(in);
		//save do diagrama com a interface in
		Controller.salvarElemento(d);
		//save do diagrama com as classes 
		Controller.salvarElemento(d);
		d.addFilho(c2);
		
		
		System.out.println(d.getFilhos());
		
		assertTrue(d.getFilhos().contains(c));
		assertTrue(d.getFilhos().contains(in));
		assertTrue(d.getFilhos().contains(c2));
		
		//retorna o elemento após o ctrl Z
		ComponenteComposto cc = DiagramasSalvos.undo();
		d = (Diagrama) cc;
		
		assertTrue(!d.getFilhos().contains(c));
		assertTrue(!d.getFilhos().contains(in));
		assertTrue(!d.getFilhos().contains(c2));
		
		System.out.println(d.getFilhos());
		
		
		/*
		 * O programa imprime que o elemento foi removido, mas na pratica ele continua 
		 * existindo dentro do diagrama.
		 */
		
	}

}
