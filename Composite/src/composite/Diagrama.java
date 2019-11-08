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
		System.out.println("Diagrama "+ super.getNome() + " foi criado!");
	}

	@Override
	public void addFilho(Componente e) {
		if(e instanceof Atributo || e instanceof Metodo)
			super.addFilho(e);
		else
			throw new IllegalArgumentException("Filho de tipo invalido!");
	}

	//metodo para salvar o diagrama
}
