package composite;


public class Metodo extends ComponenteComposto{

	private String tipoRetorno = "void";
	private String modificadoresDeAcesso = "default";

	//Get&Set
	public String getTipoRetorno() {
		return this.tipoRetorno;
	}

	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public String getModificadoresDeAcesso() {
		return this.modificadoresDeAcesso;
	}

	public void setModificadoresDeAcesso(String modificadoresDeAcesso) {
		this.modificadoresDeAcesso = modificadoresDeAcesso;
	}

	public Metodo(String nome){
		super(nome);
	}

	@Override
	public void desenha() {
		System.out.println("Desenhou metodo");
	}

	@Override
	public void addFilho(Componente e) {
		if(e instanceof Parametro)
			super.addFilho(e);
		else
			throw new IllegalArgumentException("Filho de tipo invalido!");
	}	
	

}
