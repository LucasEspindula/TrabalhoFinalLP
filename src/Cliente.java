import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa implements Dominio<Integer>{
	
	private String nome;
	
	private String cpf;
	
	private int identificador;
	
	// CONSTRUTOR PARA UPDATE
	public Cliente(int identificador, Contato contato, Endereco endereco, String nome, String cpf) {
		super(contato, endereco);
		this.nome = nome;
		this.cpf = cpf;
		this.identificador = identificador;
		validacao();
	}
	
	// CONSTRUTOR PARA INSERT
	public Cliente(Contato contato, Endereco endereco, String nome, String cpf) {
		super(contato, endereco);
		this.nome = nome;
		this.cpf = cpf;
		validacao();
	}
	
	protected void validacao() {
		
		List<String> erros = new ArrayList<String>();
		
		erros.addAll(super.validacaoPessoa());
		
		if (nome == null || nome.isBlank()) {
			erros.add("informe um Nome");
		} else if (nome != null && nome.length() > 100) {
			erros.add("Numero maximo de caracter eh 100 para o nome");
		}
		
		if (cpf == null || cpf.isBlank()) {
			erros.add("informe um cpf");
		} else if (cpf != null && cpf.length() > 20) {
			erros.add("Numero maximo de caracter eh 20 para o cpf");
		}
		
		if (!erros.isEmpty()) {
			throw new IllegalArgumentException(erros.toString());
		}
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public Integer getId() {
		return identificador;
	}
}
