package composite;

import java.util.ArrayList;
import java.util.List;

public class Metodo extends ElementoComposto{

	private String tipoRetorno = "void";
	private String modificadoresDeAcesso = "default";

	//Get&Set
	public String getTipoRetorno() {
		return this.tipoRetorno;
	}

	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public String getModificadoresDeAcesso() {
		return this.modificadoresDeAcesso;
	}

	public void setModificadoresDeAcesso(String modificadoresDeAcesso) {
		this.modificadoresDeAcesso = modificadoresDeAcesso;
	}

	public Metodo(String nome){
		super(nome);
	}

	@Override
	public void desenha() {
		System.out.println("Desenhou método");
	}

	@Override
	public void addFilho(Elemento e) {
		if(e instanceof Atributo || e instanceof Metodo)
			super.addFilho(e);
		else
			throw new IllegalArgumentException("Filho de tipo inválido!");
	}	
	

}
