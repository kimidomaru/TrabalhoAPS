package composite;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

	/* 	O usuario tera um menu com algumas possiveis. De comeco sera possivel apenas criar um diagrama.
		Em seguida sera possivel criar Classe/Inteface, salvar e sair.
		Ao criar uma classe/interface, sera possivel fazer adicoes em seu escopo, e assim adiante.
		Sendo possivel, sempre, salvar e sair. E a partir da criacao, deve ser possivel fazer a exclussao.
		O metodo Undo e Agreggate tem que ser implementados.
	 */
	

	public static void menu(boolean algoaberto){			//menu que ira se adaptar as possibilidades de criacao, exclusao, etc
		Scanner entrada;
		int opcao = -1;
		if(algoaberto){

		} else {
			System.out.println("Digite o numero da opcao desejada:");
			System.out.println("1 - Criar um diagrama");
			System.out.println("2 - Abrir um diagrama");
			System.out.println("0 - Sair");
			
			do{			//repete ate ter uma opcao valida
				entrada = new Scanner(System.in);
				try {
					opcao = entrada.nextInt();
					
					if(opcao < 0 || opcao > 2) {
						throw new InputMismatchException();
					}
				}
				catch (InputMismatchException e) {
					System.out.println("Opcao invalida!");
					System.out.println("Digite o numero da opcao desejada:");
					System.out.println("1 - Criar um diagrama");
					System.out.println("2 - Abrir um diagrama");
					System.out.println("0 - Sair");
				}
				
			}while(opcao!=1 && opcao!=2 && opcao!=0);

			if(opcao==1){
				System.out.println("Criar diagrama");
				criarElemento("diagrama");
			} else if(opcao==2){
				System.out.println("Abrir diagrama");
				abreDiagrama();
			} else if(opcao==0)
				System.exit(0);
			}
	}

	public static void criarElemento(String oquecriar){	
		switch(oquecriar){
			case "diagrama":
				Diagrama d = new Diagrama("Teste");
				//d.desenha();
				break;
			case "classe":
				Classe c = new Classe("Teste");
				c.desenha();
				break;
			case "interface":
				break;
			case "atributo":
				break;
			case "metodo":
				break;
			case "parametro":
				break;
			case "relacionamento":
				break;
		}
	}

	public static List<Elemento> abreDiagrama(){			//abre um arquivo contendo um diagrama e passa os elementos para uma lista de elementos
		
		//-----------------------------VERIFICAR ISSO AQUI DEPOIS---------------------------------------
		
		List<Elemento> teste = new ArrayList<Elemento>();
		return teste;
	}

	public static void main(String[] args) {

		boolean algoaberto = false;
		System.out.println("Instrucoes iniciais:");
		System.out.println("");
		menu(algoaberto);
	/*	Classe c = new Classe();
		Atributo a = new Atributo();
		Atributo b = new Atributo();
		Metodo m = new Metodo();
		c.addFilho(a);
		c.addFilho(b);
		c.addFilho(m);
		
		
		Classe c1 = new Classe();
		Atributo a1 = new Atributo();
		Metodo m1 = new Metodo();
		c1.addFilho(a1);
		c1.addFilho(m1);
		
		Diagrama d = new Diagrama();
		d.addFilho(c);
		d.addFilho(c1);
		
		d.desenha();*/


		
	}

}
