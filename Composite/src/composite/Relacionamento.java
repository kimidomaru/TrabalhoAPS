package composite;

import java.util.ArrayList;
import java.util.List;

public class Relacionamento extends Componente{
    private String tipoRelacionamento;
	private String direcaoLeitura;
	private String multiplicidade;
	//private String navegabilidade;
    List<Componente> elementos = new ArrayList(2);
    
    
    public String getMultiplicidade() {
		return multiplicidade;
	}

	public void setMultiplicidade(String multiplicidade) {
		this.multiplicidade = multiplicidade;
	}

	public String getTipoRelacionamento() {
		return tipoRelacionamento;
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

    
	public Relacionamento(Componente elemento1, Componente elemento2, String tipoRelacionamento, String nome, String direcaoLeitura, String multiplicidade) {
		super(nome);
		elementos.add(elemento1);
        elementos.add(elemento2);
		this.tipoRelacionamento = tipoRelacionamento;
		this.direcaoLeitura = direcaoLeitura;
		this.multiplicidade = multiplicidade;
	}
	
	@Override
	public void desenha() {
		if(this.direcaoLeitura.equals("Direita"))
			if(multiplicidade.equals("1...1")) {
				System.out.println(elementos.get(0).getNome() + " [1]" + "--(" + this.tipoRelacionamento + ")-->" + "[1] " + elementos.get(1).getNome());
			} else if(multiplicidade.equals("1...n")) {
				System.out.println(elementos.get(0).getNome() + " [1]" + "--(" + this.tipoRelacionamento + ")-->" + "[N] " + elementos.get(1).getNome());
			} else if(multiplicidade.equals("n...1")) {
				System.out.println(elementos.get(0).getNome() + " [N]" + "--(" + this.tipoRelacionamento + ")-->" + "[1] " + elementos.get(1).getNome());
			} else if(multiplicidade.equals("n...n")) {
				System.out.println(elementos.get(0).getNome() + " [N]" + "--(" + this.tipoRelacionamento + ")-->" + "[N] " + elementos.get(1).getNome());
			}
			
		
		if(this.direcaoLeitura.equals("Esquerda"))
			if(multiplicidade.equals("1...1")) {
				System.out.println(elementos.get(0).getNome() + " [1]" + "<--(" + this.tipoRelacionamento + ")--" + "[1] " + elementos.get(1).getNome());
			} else if(multiplicidade.equals("1...n")) {
				System.out.println(elementos.get(0).getNome() + " [1]" + "<--(" + this.tipoRelacionamento + ")--" + "[N] " + elementos.get(1).getNome());
			} else if(multiplicidade.equals("n...1")) {
				System.out.println(elementos.get(0).getNome() + " [N]" + "<--(" + this.tipoRelacionamento + ")--" + "[1] " + elementos.get(1).getNome());
			} else if(multiplicidade.equals("n...n")) {
				System.out.println(elementos.get(0).getNome() + " [N]" + "<--(" + this.tipoRelacionamento + ")--" + "[N] " + elementos.get(1).getNome());
			}
			
		
		if(this.direcaoLeitura.equals("Ambos")) {
			if(multiplicidade.equals("1...1")) {
				System.out.println(elementos.get(0).getNome() + " [1]" + "<--(" + this.tipoRelacionamento + ")-->" + "[1] " + elementos.get(1).getNome());
			} else if(multiplicidade.equals("1...n")) {
				System.out.println(elementos.get(0).getNome() + " [1]" + "<--(" + this.tipoRelacionamento + ")-->" + "[N] " + elementos.get(1).getNome());
			} else if(multiplicidade.equals("n...1")) {
				System.out.println(elementos.get(0).getNome() + " [N]" + "<--(" + this.tipoRelacionamento + ")-->" + "[1] " + elementos.get(1).getNome());
			} else if(multiplicidade.equals("n...n")) {
				System.out.println(elementos.get(0).getNome() + " [N]" + "<--(" + this.tipoRelacionamento + ")-->" + "[N] " + elementos.get(1).getNome());
			}
		}
		
	}
	
}