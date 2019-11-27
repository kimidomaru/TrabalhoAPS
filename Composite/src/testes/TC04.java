package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import composite.Classe;
import composite.Relacionamento;

class TC04 {

	@Test
	void testCriarAssocicao() {
		Classe c = new Classe(null);
		assertNotNull(c.getNome());
		assertEquals(c.getNome(), "Cliente");
		c.setMultiplicidade("1 .. 1");
		c.setNavegabilidade("nao navegavel");
		assertEquals(c.getMultiplicidade(), "1 .. 1");
		assertEquals(c.getNavegabilidade(), "nao navegavel");
		
		Classe c2 = new Classe(null);
		assertNotNull(c2.getNome());
		assertEquals(c2.getNome(), "Produto");
		c2.setMultiplicidade("1 .. N");
		c2.setNavegabilidade("navegavel");
		assertEquals(c2.getMultiplicidade(), "1 .. N");
		assertEquals(c2.getNavegabilidade(), "navegavel");
		
		Relacionamento rela = new Relacionamento(c, c2, "Associacao", null, "direita" , "1 .. N");
		assertNotNull(rela.getNome());
		assertEquals(rela.getNome(), "Compra");
		assertTrue(rela.getElementos().contains(c));
		assertTrue(rela.getElementos().contains(c2));
		assertEquals(rela.getTipoRelacionamento(), "Associacao");
		assertEquals(rela.getDirecaoLeitura(), "direita");
		assertEquals(rela.getMultiplicidade(), "1 .. N");
		
		/*O nome não esta sendo atribuido ao relacionamento pois ha a declaracao de um atributo nome
         * tanto na classe Relacionamento quanto na sua classe pai Componente e o nome dado e atribuído a
         * variavel da classe pai*/
		
		rela.desenha();
	}

}
