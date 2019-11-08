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

	public Metodo(){
		super();
	}

	@Override
	public void desenha() {
		System.out.println("Desenhou método");
	}

	@Override
	public void addFilho(Componente e) {
		if(e instanceof Atributo || e instanceof Metodo)
			super.addFilho(e);
		else
			throw new IllegalArgumentException("Filho de tipo inválido!");
	}	
	

}
