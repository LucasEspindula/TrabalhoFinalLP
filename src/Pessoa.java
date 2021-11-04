import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa {
	
	private Contato contato;
	
	private Endereco endereco;

	public Pessoa(Contato contato, Endereco endereco) {
		super();
		this.contato = contato;
		this.endereco = endereco;
		validacaoPessoa();
	}
	
	protected abstract void validacao();
	
	protected List<String> validacaoPessoa() {
		
		List<String> erros = new ArrayList<String>();
		
		if (contato == null) {
			erros.add("informe um contato");
		}
		
		if (endereco == null) {
			erros.add("informe um endereco");
		}
		return erros;
	}

	public Contato getContato() {
		return contato;
	}

	public Endereco getEndereco() {
		return endereco;
	}
}
