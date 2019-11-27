package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import composite.Classe;
import composite.Relacionamento;

class TC05 {

	@Test
	void testCriarAgregacao() {
		Classe c = new Classe(null);
		assertNotNull(c.getNome());
		assertEquals(c.getNome(), "EquipeProj");
		c.setMultiplicidade("0 .. N");
		c.setNavegabilidade("navegavel");
		assertEquals(c.getMultiplicidade(), "0 .. N");
		assertEquals(c.getNavegabilidade(), "navegavel");
		
		Classe c2 = new Classe(null);
		assertNotNull(c2.getNome());
		assertEquals(c2.getNome(), "Funcionario");
		c2.setMultiplicidade("1 .. N");
		c2.setNavegabilidade("navegavel");
		assertEquals(c2.getMultiplicidade(), "1 .. N");
		assertEquals(c2.getNavegabilidade(), "navegavel");
		
		Relacionamento rela = new Relacionamento(c, c2, "Agregacao", null, "direita" ,"N .. N");
		assertNotNull(rela.getNome());
		assertEquals(rela.getNome(), "Emprega");
		assertTrue(rela.getElementos().contains(c));
		assertTrue(rela.getElementos().contains(c2));
		assertEquals(rela.getTipoRelacionamento(), "Agregacao");
		assertEquals(rela.getDirecaoLeitura(), "direita");
		assertEquals(rela.getMultiplicidade(), "N .. N");
		
		/*O nome não esta sendo atribuido ao relacionamento pois ha a declaracao de um atributo nome
         * tanto na classe Relacionamento quanto na sua classe pai Componente e o nome dado e atribuído a
         * variavel da classe pai*/
		
		rela.desenha();
	}

}
