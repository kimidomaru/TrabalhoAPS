package composite;

import java.util.ArrayList;
import java.util.List;

public class Interf extends ElementoComposto {
	List<Atributo> atributos = new ArrayList();
	List<Metodo> metodos = new ArrayList();
	private String multiplicidade = null;
	private String navegabilidade = null;
	private String modificadorDeAcesso = null;

	//Get&Set
	public String getMultiplicidade() {
		return this.multiplicidade;
	}

	public void setMultiplicidade(String multiplicidade) {
		this.multiplicidade = multiplicidade;
	}

	public String getNavegabilidade() {
		return this.navegabilidade;
	}

	public void setNavegabilidade(String navegabilidade) {
		this.navegabilidade = navegabilidade;
	}

	public String getModificadorDeAcesso() {
		return this.modificadorDeAcesso;
	}

	public void setModificadorDeAcesso(String modificadorDeAcesso) {
		this.modificadorDeAcesso = modificadorDeAcesso;
	}

	public Interf(String nome){
		super(nome);
	}

	@Override
	public void desenha() {
		super.desenha();
		//código para desenhar a própria classe (borda, delimitadores, etc)
		System.out.println("Desenhou classe");
	}
	
	@Override
	public void addFilho(Elemento e) {
		if(e instanceof Atributo || e instanceof Metodo)
			super.addFilho(e);
		else
			throw new IllegalArgumentException("Filho de tipo inválido!");
	}

}

