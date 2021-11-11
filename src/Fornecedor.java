import java.util.ArrayList;
import java.util.List;

public class Fornecedor extends Pessoa implements Dominio<Integer>{
	
	private String nomeFantasia;
	
	private String cnpj;
	
	private int identificador;

	// CONSTRUTOR PARA UPDATE
	public Fornecedor(int identificador, Contato contato, Endereco endereco, String nomeFantasia, String cnpj) {
		super(contato, endereco);
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.identificador = identificador;
		validacao();
	}
	
	// CONSTRUTOR PARA INSERT
	public Fornecedor(Contato contato, Endereco endereco, String nomeFantasia, String cnpj) {
		super(contato, endereco);
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		validacao();
	}
	
	protected void validacao() {
		
		List<String> erros = new ArrayList<String>();
		
		erros.addAll(super.validacaoPessoa());
		
		if (nomeFantasia == null || nomeFantasia.isBlank()) {
			erros.add("informe um Nome Fantasia");
		} else if (nomeFantasia != null && nomeFantasia.length() > 100) {
			erros.add("Numero maximo de caracter eh 100 para o nome fantasia");
		}
		
		if (cnpj == null || cnpj.isBlank()) {
			erros.add("informe um cnpj");
		} else if (cnpj != null && cnpj.length() > 20) {
			erros.add("Numero maximo de caracter eh 20 para o cnpj");
		}
		
		if (!erros.isEmpty()) {
			throw new IllegalArgumentException(erros.toString());
		}
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Integer getId() {
		return identificador;
	}
}
