package composite;

import java.util.ArrayList;
import java.util.List;

public abstract class ComponenteComposto extends Componente{

	private List<Componente> filhos;
	
	public ComponenteComposto(String nome) {
		super(nome);
		this.filhos = new ArrayList<Componente>();
	}
	
	@Override
	public void desenha() {
		for (Componente elemento : filhos) {
			elemento.desenha();
		}
	}
	
	public void addFilho(Componente e) {
		filhos.add(e);
	}
	
	public List<Componente> getFilhos(){
		return this.filhos;
	}
	
	public void removerFilho(Componente e) {
		filhos.remove(e);
	}
}
