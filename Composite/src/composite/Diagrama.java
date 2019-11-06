package composite;

import java.util.ArrayList;
import java.util.List;

public class Diagrama extends ElementoComposto{

	public Diagrama(String nome){
		super(nome);
		desenha();
	}
	
	@Override
	public void desenha() {
		super.desenha();
		//desenha o diagrama
		System.out.println("DIAGRAMA DESENHADO");
	}

	@Override
	public void addFilho(Elemento e) {
		if(e instanceof Atributo || e instanceof Metodo)
			super.addFilho(e);
		else
			throw new IllegalArgumentException("Filho de tipo invalido!");
	}

	//metodo para salvar o diagrama
}
