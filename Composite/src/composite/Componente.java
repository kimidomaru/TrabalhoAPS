package composite;

import java.util.Scanner;

public abstract class Componente {

	private String nome;
	private boolean mensagemCriado;
	
	public Componente(String nome){
		if(nome == null) {
			this.mensagemCriado = true;
			System.out.print("Digite o nome: ");
			Scanner entrada = new Scanner(System.in);
			String nome2 = entrada.nextLine();
			this.nome = nome2;
			//entrada.close();
		} else {
			this.nome = nome;
		}
		
	}
	
	//Get&Set
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean getMensagemCriado() {
		return this.mensagemCriado;
	}

	public void setMensagemCriado(boolean mensagemCriado) {
		this.mensagemCriado = mensagemCriado;
	}
	
	
	public abstract void desenha();

	public String toString(){
		return this.nome;
	}


}
