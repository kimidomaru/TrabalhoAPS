package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import composite.Classe;
import composite.Relacionamento;

class TC07 {

	@Test
	void testCriarEspecializacao() {
		Classe c = new Classe(null);
		assertNotNull(c.getNome());
		assertEquals(c.getNome(), "Animal");
		c.setMultiplicidade("1 .. 1");
		c.setNavegabilidade("navegavel");
		assertEquals(c.getMultiplicidade(), "1 .. 1");
		assertEquals(c.getNavegabilidade(), "navegavel");
		
		Classe c2 = new Classe(null);
		assertNotNull(c2.getNome());
		assertEquals(c2.getNome(), "Gato");
		c2.setMultiplicidade("1 .. 1");
		c2.setNavegabilidade("nao navegavel");
		assertEquals(c2.getMultiplicidade(), "1 .. 1");
		assertEquals(c2.getNavegabilidade(), "nao navegavel");
		
		Relacionamento rela = new Relacionamento(c, c2, "Especializacao", null, "direita" ,"1 .. 1");
		assertNotNull(rela.getNome());
		assertEquals(rela.getNome(), "");
		assertTrue(rela.getElementos().contains(c));
		assertTrue(rela.getElementos().contains(c2));
		assertEquals(rela.getTipoRelacionamento(), "Especializacao");
		assertEquals(rela.getDirecaoLeitura(), "direita");
		assertEquals(rela.getMultiplicidade(), "1 .. 1");
		
		/*O nome não esta sendo atribuido ao relacionamento pois ha a declaracao de um atributo nome
         * tanto na classe Relacionamento quanto na sua classe pai Componente e o nome dado e atribuido a
         * variavel da classe pai*/
		
		rela.desenha();
	}

}
