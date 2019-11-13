package composite;

import java.util.ArrayList;
import java.util.List;

public class Relacionamento extends Componente{
    private String tipoRelacionamento;
    private String nome;
	private String direcaoLeitura;
	//private String navegabilidade;
    List<Componente> elementos = new ArrayList(2);
    
    
    public String getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDirecaoLeitura() {
		return direcaoLeitura;
	}

	public void setDirecaoLeitura(String direcaoLeitura) {
		this.direcaoLeitura = direcaoLeitura;
	}

	/*public String getNavegabilidade() {
		return navegabilidade;
	}

	public void setNavegabilidade(String navegabilidade) {
		this.navegabilidade = navegabilidade;
	}*/

	public List<Componente> getElementos() {
		return elementos;
	}

	public void setTipoRelacionamento(String tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

	public void setElementos(List<Componente> elementos) {
		this.elementos = elementos;
	}

    
	public Relacionamento(Componente elemento1, Componente elemento2, String tipoRelacionamento, String nome, String direcaoLeitura) {
		super(nome);
		elementos.add(elemento1);
        elementos.add(elemento2);
		this.tipoRelacionamento = tipoRelacionamento;
		//this.nome = nome;
		this.direcaoLeitura = direcaoLeitura;
		//this.navegabilidade = navegabilidade;
	}
	
	@Override
	public void desenha() {
		
		/*if(this.navegabilidade == "Direita" )
			System.out.println( elementos.get(0).getNome() + " ----> " + elementos.get(1).getNome() );
		
		if(this.navegabilidade == "Esquerda")
			System.out.println( elementos.get(0).getNome() + " <---- " + elementos.get(1).getNome() );
		
		if(this.navegabilidade == "Ambos")
			System.out.println( elementos.get(0).getNome() + " ---- " + elementos.get(1).getNome() );
		*/
		if(this.direcaoLeitura == "Direita")
			System.out.println( elementos.get(0).getNome() +" --(" + this.tipoRelacionamento + ")--> " + elementos.get(1).getNome() );
		
		if(this.direcaoLeitura == "Esquerda")
			System.out.println( elementos.get(1).getNome() +" <--(" + this.tipoRelacionamento + ")-- " + elementos.get(0).getNome() );
		
		if(this.direcaoLeitura == "Ambos")
			System.out.println( elementos.get(0).getNome() +" <--(" + this.tipoRelacionamento + ")--> " + elementos.get(1).getNome() );
		
	}
	
}