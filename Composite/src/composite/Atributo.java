package composite;

public class Atributo extends Componente {

	private String tipo;
	private String modificadoresDeAcesso = "Default";

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
		this.modificadoresDeAcesso = modificadores;
	}

	public Atributo(String tipo){
		super();
		this.tipo = tipo;
	}

	@Override
	public void desenha() {
		
		if(this.modificadoresDeAcesso == "Default")
			System.out.println("Atributo Criado: ~ " + super.getNome() + " " + this.tipo);
		
		if(this.modificadoresDeAcesso == "Private")
			System.out.println("Atributo Criado: - " + super.getNome() + " " + this.tipo);
		
		if(this.modificadoresDeAcesso == "Public")
			System.out.println("Atributo Criado: + " + super.getNome() + " " + this.tipo);
		
		if(this.modificadoresDeAcesso == "Protected")
			System.out.println("Atributo Criado: # " + super.getNome() + " " + this.tipo);

	}

}
