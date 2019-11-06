package composite;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementoComposto extends Elemento{

	private List<Elemento> filhos;
	
	public ElementoComposto(String nome) {
		super(nome);
		this.filhos = new ArrayList<Elemento>();
	}
	
	@Override
	public void desenha() {
		for (Elemento elemento : filhos) {
			elemento.desenha();
		}
	}
	
	public void addFilho(Elemento e) {
		filhos.add(e);
	}

}
