package composite;


public class Diagrama extends ComponenteComposto{

	boolean mensagemCriado = false;
	
	public Diagrama(String nome){
		super(nome);
		desenha();
	}
	
	@Override
	public void desenha() {
		super.desenha();
		//desenha o diagrama
		if(super.getMensagemCriado()) {
			System.out.println("\nDiagrama "+ super.getNome() + " foi criado!");
		}
	}

	@Override
	public void addFilho(Componente e) {
		if(e instanceof Classe || e instanceof Interface)
			super.addFilho(e);
		else
			throw new IllegalArgumentException("\nFilho de tipo invalido!\n");
	}

	//metodo para salvar o diagrama
}
