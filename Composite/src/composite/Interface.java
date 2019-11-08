package composite;

public class Interface extends ComponenteComposto {

	private String multiplicidade = null;
	private String navegabilidade = null;
	private String modificadorDeAcesso = null;

	//Get&Set
	public String getMultiplicidade() {
		return this.multiplicidade;
	}

	public void setMultiplicidade(String multiplicidade) {
		this.multiplicidade = multiplicidade;
	}

	public String getNavegabilidade() {
		return this.navegabilidade;
	}

	public void setNavegabilidade(String navegabilidade) {
		this.navegabilidade = navegabilidade;
	}

	public String getModificadorDeAcesso() {
		return this.modificadorDeAcesso;
	}

	public void setModificadorDeAcesso(String modificadorDeAcesso) {
		this.modificadorDeAcesso = modificadorDeAcesso;
	}

	public Interface(){
		super();
		desenha();
	}

	@Override
	public void desenha() {
		super.desenha();
		System.out.println("Interface "+ super.getNome() + " foi criada!");
	}
	
	@Override
	public void addFilho(Componente e) {
		if(e instanceof Atributo || e instanceof Metodo)
			super.addFilho(e);
		else
			throw new IllegalArgumentException("Filho de tipo invalido");
	}

}

