package composite;


public class Diagrama extends ComponenteComposto{

	public Diagrama(){
		super();
		desenha();
	}
	
	@Override
	public void desenha() {
		super.desenha();
		//desenha o diagrama
		System.out.println("\nDiagrama "+ super.getNome() + " foi criado!\n");
	}

	@Override
	public void addFilho(Componente e) {
		if(e instanceof Atributo || e instanceof Metodo || e instanceof Classe)
			super.addFilho(e);
		else
			throw new IllegalArgumentException("\nFilho de tipo invalido!\n");
	}

	//metodo para salvar o diagrama
}
