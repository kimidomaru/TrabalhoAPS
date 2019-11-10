package composite;


public class Diagrama extends ComponenteComposto{

	public Diagrama(String nome){
		super(nome);
		desenha();
	}
	
	@Override
	public void desenha() {
		super.desenha();
		//desenha o diagrama
		if(super.getNome() == null) {
			System.out.println("\nDiagrama "+ super.getNome() + " foi criado!\n");
		} else { //no futuro nao vai ter esse else
			System.out.println("teste diagrama");
		}
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
