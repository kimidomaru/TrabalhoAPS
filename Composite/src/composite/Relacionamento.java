package composite;

import java.util.ArrayList;
import java.util.List;

public class Relacionamento extends Elemento{
    private String tipo;
    private String direcao;
    List<Elemento> elementos = new ArrayList(2);

    public Relacionamento(Elemento elemento1, Elemento elemento2, String direcao, String nome){
        super(nome);
        elementos.add(elemento1);
        elementos.add(elemento2);
        this.direcao = direcao;
    }
    
    //Get&Set
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDirecao() {
		return this.direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
	
	@Override
	public void desenha() {
		System.out.println("Desenhou relacionamento");
	}
	
}