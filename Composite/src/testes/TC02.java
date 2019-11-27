package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import composite.Atributo;
import composite.Classe;
import composite.Metodo;
import composite.Parametro;

public class TC02 {

	@Test
	public void testCriarClasse() {
		Classe c = new Classe(null);
	    assertNotNull(c.getNome());
	    assertEquals(c.getNome(), "Cliente");

	    Atributo atr1 = new Atributo(null, "int", "public");
	    assertNotNull(atr1.getNome());
	    assertEquals(atr1.getNome(), "id");
	    
	    Atributo atr2 = new Atributo(null, "double", "private");
	    assertNotNull(atr2.getNome());
	    assertEquals(atr2.getNome(), "saldo");
	 
	    Metodo met = new Metodo(null, "void", "public");
	    assertNotNull(met.getNome());
	    assertEquals(met.getNome(), "criaCliente");
	    
	    Parametro par1 = new Parametro("int", "id");
	    Parametro par2 = new Parametro("double", "quantia");
	    
	    met.addFilho(par1);
	    met.addFilho(par2);
	    c.addFilho(atr1);
	    c.addFilho(atr2);
	    c.addFilho(met);

	    assertTrue(c.getFilhos().contains(atr1));
	    assertTrue(c.getFilhos().contains(atr2));
	    assertTrue(c.getFilhos().contains(met));
	    assertTrue(met.getFilhos().contains(par1));
	    assertTrue(met.getFilhos().contains(par2));
	    
	    c.desenha();
	}

}
