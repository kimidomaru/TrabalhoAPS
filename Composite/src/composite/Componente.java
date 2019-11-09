package composite;

import java.util.Scanner;

public abstract class Componente {

	private String nome;
	
	public Componente(){
		System.out.print("Digite o nome: ");
		Scanner entrada = new Scanner(System.in);
		String nome2 = entrada.nextLine();
		this.nome = nome2;
		//entrada.close();
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


}
