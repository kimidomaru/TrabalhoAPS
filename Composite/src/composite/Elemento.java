package composite;

public abstract class Elemento {

	private String nome;
	
	public Elemento(String nome){
		this.nome = nome;
	}
	
	//Get&Set
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public abstract void desenha();

	public String toString(){
		return this.nome;
	}
	
	//metodo para excluir o elemento

}
