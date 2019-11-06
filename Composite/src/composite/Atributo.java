package composite;

public class Atributo extends Elemento {

	private String tipo;
	private String modificadoresDeAcesso = "default";

	//Get&Set
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModificadoresDeAcesso() {
		return this.modificadoresDeAcesso;
	}

	public void setModificadoresDeAcesso(String modificadores) {
		this.modificadoresDeAcesso = modificadoresDeAcesso;
	}

	public Atributo(String tipo, String nome){
		super(nome);
		this.tipo = tipo;
	}

	@Override
	public void desenha() {
		System.out.println("Desenhou atributo");
	}

}
