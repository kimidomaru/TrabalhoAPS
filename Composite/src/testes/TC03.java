package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import composite.Atributo;
import composite.Interface;
import composite.Metodo;
import composite.Parametro;

public class TC03 {

	@Test
	public void testCriarInterface() {
		Interface i = new Interface(null);
	    assertNotNull(i.getNome());
	    assertEquals(i.getNome(), "Depositante");

	    Atributo atr = new Atributo(null, "String", "default");
	    assertNotNull(atr.getNome());
	    assertEquals(atr.getNome(), "nomeDepositante");
	    
	    Metodo met = new Metodo(null, "double", "protected");
	    assertNotNull(met.getNome());
	    assertEquals(met.getNome(), "deposita");
	    
	    Parametro par = new Parametro("String", "nomeDepositante");

	    met.addFilho(par);
	    i.addFilho(atr);
	    i.addFilho(met);

	    assertTrue(i.getFilhos().contains(atr));
	    assertTrue(i.getFilhos().contains(met));
	    assertTrue(met.getFilhos().contains(par));
	    
	    i.desenha();
	}
}

