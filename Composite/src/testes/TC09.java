package testes;

import org.junit.jupiter.api.Test;

import composite.Atributo;
import composite.Classe;
import composite.Controller;
import composite.Diagrama;
import composite.DiagramasSalvos;
import composite.Metodo;
import composite.Relacionamento;

public class TC09 {

	@Test
	public void testSalvarDiagrama() {
		Diagrama d = new Diagrama(null);
		Classe c1 = new Classe(null);
	    Atributo atr = new Atributo(null, "byte", "private");
	    Metodo met = new Metodo(null, "char", "public");
	    c1.setMultiplicidade("1 .. 1");
		c1.setNavegabilidade("nao navegavel");
	    
		Classe c2 = new Classe(null);
	    c2.setMultiplicidade("1 .. N");
		c2.setNavegabilidade("navegavel");
		
		
	    c1.addFilho(atr);
	    c1.addFilho(met);
	    d.addFilho(c1);
	    d.addFilho(c2);
	    
	    Relacionamento r = new Relacionamento(c1, c2, "Associacao", null, "direita" , "1 .. N");
	    Controller.relacionamentos.add(r);
	    
		Controller.elementoAberto = "diagrama";
		Controller.elementoCompostoAtual = d;
		DiagramasSalvos.salvarDiagrama(Controller.elementoAberto, Controller.elementoCompostoAtual, Controller.relacionamentos);
		Controller.elementoAberto = "inicial";
		Controller.abrirDiagrama();
	}

}

