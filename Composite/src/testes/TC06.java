package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import composite.Classe;
import composite.Relacionamento;

class TC06 {

	@Test
	void testCriarComposicao() {
		Classe c = new Classe(null);
		assertNotNull(c.getNome());
		assertEquals(c.getNome(), "Roda");
		c.setMultiplicidade("1 .. N");
		c.setNavegabilidade("navegavel");
		assertEquals(c.getMultiplicidade(), "1 .. N");
		assertEquals(c.getNavegabilidade(), "navegavel");
		
		Classe c2 = new Classe(null);
		assertNotNull(c2.getNome());
		assertEquals(c2.getNome(), "Carro");
		c2.setMultiplicidade("1 .. 1");
		c2.setNavegabilidade("navegavel");
		assertEquals(c2.getMultiplicidade(), "1 .. 1");
		assertEquals(c2.getNavegabilidade(), "navegavel");
		
		Relacionamento rela = new Relacionamento(c, c2, "Composicao", null, "esquerda" ,"N .. 1");
		assertNotNull(rela.getNome());
		assertEquals(rela.getNome(), "Utiliza");
		assertTrue(rela.getElementos().contains(c));
		assertTrue(rela.getElementos().contains(c2));
		assertEquals(rela.getTipoRelacionamento(), "Composicao");
		assertEquals(rela.getDirecaoLeitura(), "esquerda");
		assertEquals(rela.getMultiplicidade(), "N .. 1");
		
		/*O nome não esta sendo atribuido ao relacionamento pois ha a declaracao de um atributo nome
         * tanto na classe Relacionamento quanto na sua classe pai Componente e o nome dado e atribuído a
         * variavel da classe pai*/
		
		rela.desenha();
	}

}
