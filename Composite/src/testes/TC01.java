package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import composite.Diagrama;

public class TC01 {

	@Test
	public void testCriarDiagrama() {
		/*
		 * Caso o construtor de Diagrama receba um nome nulo, que e o que ocorre na
		 * execucao do programa geral, ele pede que o usuario digite um nome, e o 
		 * atributo 'mensagemCriado' e ativado. Isso permite que o diagrama seja 
		 * exibido com o metodo 'desenha()'. Assim, caso o construtor receba como
		 * parametro um valor nao nulo, o metodo desenha() nao imprime nada. 
		 * 
		 */
		Diagrama d = new Diagrama(null);
		assertNotNull(d.getNome());
		assertEquals(d.getNome(), "d1");
	}
}
