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

	public Atributo(String tipo, String nome){
		super(nome);
		this.tipo = tipo;
	}

	@Override
	public void desenha() {
		
		if(this.modificadoresDeAcesso == "Default")
			if (super.getNome() == null) {
				System.out.println("Atributo Criado: ~ " + super.getNome() + " " + this.tipo);
			}			
		
		if(this.modificadoresDeAcesso == "Private")
			if (super.getNome() == null) {
				System.out.println("Atributo Criado: - " + super.getNome() + " " + this.tipo);
			}
			
		
		if(this.modificadoresDeAcesso == "Public")
			if (super.getNome() == null) {
				System.out.println("Atributo Criado: + " + super.getNome() + " " + this.tipo);
			}
			
		
		if(this.modificadoresDeAcesso == "Protected")
			if (super.getNome() == null) {
				System.out.println("Atributo Criado: # " + super.getNome() + " " + this.tipo);
			}
			

	}

}
