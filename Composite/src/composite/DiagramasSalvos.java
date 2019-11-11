package composite;

import java.util.ArrayList;
import java.util.List;

public class DiagramasSalvos {
	static List<ComponenteComposto> salvos = new ArrayList<ComponenteComposto>();
	
	public static void quickSave(ComponenteComposto comp) {
		salvos.add(comp);
		//mostrar();
	}
	
	public static void mostrar() {
		System.out.println("-------HISTORICO---------");
		
		for(int i = 0; i<salvos.size(); i++) {
			
			String tipoComponente = salvos.get(i).getClass().getSimpleName();
			System.out.println(tipoComponente+" " + salvos.get(i));
			System.out.println();
			
			if(salvos.get(i).getFilhos().size() > 0) {
				for(int j=0; j < salvos.get(i).getFilhos().size(); j++) {
					System.out.println("\t" + salvos.get(i).getFilhos().get(j));
				}
			}
			
			System.out.println();
		}
	}
	
	public static ComponenteComposto undo() {
		int ultimo = salvos.size() - 1;
		String tipoElemento, nomeElemento = "";
		//REMOVENDO a ultima posicao da lista
		if(salvos.get(ultimo).getFilhos().size() > 0) {
			//se tiver filhos, pegar o ultimo filho
			int tmp = salvos.get(ultimo).getFilhos().size() - 1;
			tipoElemento = salvos.get(ultimo).getFilhos().get(tmp).getClass().getSimpleName();
			nomeElemento = salvos.get(ultimo).getFilhos().get(tmp).getNome();
		}
		else {
			tipoElemento = salvos.get(ultimo).getClass().getSimpleName();
			nomeElemento = salvos.get(ultimo).getNome();
		}
		//System.out.println("\n_____"+tipo + " "+ salvos.get(ultimo).getNome()+ " removido(a)!_____\n");
		System.err.println("\n"+tipoElemento + " "+ nomeElemento+ " removido(a)!\n");
		salvos.remove(ultimo);
		if(ultimo > 0) {
			ultimo--;
			return salvos.get(ultimo);
		}
		return null;
	}
}