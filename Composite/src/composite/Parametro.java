package composite;

public class Parametro extends Componente{

    private String tipo;

    //Get&Set
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

    public Parametro(String tipo, String nome){
        super(nome);
        this.tipo = tipo;
    }

	@Override
	public void desenha() {
		System.out.println("Parametro desenhado!");
	}
	
	

}