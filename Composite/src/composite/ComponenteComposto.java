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
		int indice = verificaFilhoJaExiste(e.getNome(), e.getClass().getSimpleName());
		if(indice == -1){
			filhos.add(e);
		}
		else{
			filhos.remove(indice);
			filhos.add(e);
		}
	}
	
	public List<Componente> getFilhos(){
		return this.filhos;
	}
	
	public void removerFilho(String nomeComponente, String tipoComponente) {
		int indice = verificaFilhoJaExiste(nomeComponente, tipoComponente);
		if(indice != -1){
			filhos.remove(indice);
		}
	}
	
	public int verificaFilhoJaExiste(String nomeComponente, String tipoComponente){
		int indice = -1;
		for(int i = 0; i < filhos.size(); i++){
			if(filhos.get(i).getNome().equals(nomeComponente) && filhos.get(i).getClass().getSimpleName().equals(tipoComponente)){
				indice = i;
				break;
			}
		}
		return indice;
	}
}
