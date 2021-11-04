import java.util.ArrayList;
import java.util.List;

public class Endereco {
	
	private String rua;
	
	private String numero;
	
	private String bairro;
	
	private String complemento;
	
	private String cep;
	
	private String cidade;
	
	private String estado;

	public Endereco(String rua, String numero, String bairro, String complemento, String cep, String cidade, String estado) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		validacaoEndereco();
	}
	
	protected void validacaoEndereco() {
		
		List<String> erros = new ArrayList<String>();
		
		if (rua == null || rua.isBlank()) {
			erros.add("informe uma rua");
		} else if (rua != null && rua.length() != 100) {
			erros.add("Numero maximo de caracter eh 100");
		} 
		
		if (numero.length() != 6) {
			erros.add("Numero maximo de caracter eh 6");
		}
		
		if (bairro == null || bairro.isBlank()) {
			erros.add("informe um bairro");
		} else if (bairro != null && bairro.length() != 50) {
			erros.add("Numero maximo de caracter eh 50");
		} 
		
		if (complemento.length() != 120) {
			erros.add("Numero maximo de caracter eh 120");
		}
		
		if (cep == null || cep.isBlank()) {
			erros.add("informe um cep");
		} else if (cep != null && cep.length() != 10) {
			erros.add("Numero maximo de caracter eh 10");
		} 
		
		if (cidade == null || cidade.isBlank()) {
			erros.add("informe uma cidade");
		} else if (cidade != null && cidade.length() != 50) {
			erros.add("Numero maximo de caracter eh 50");
		}
		
		if (estado == null || estado.isBlank()) {
			erros.add("informe um estado");
		} else if (estado != null && estado.length() != 2) {
			erros.add("Numero maximo de caracter eh 2");
		}
		
		if (!erros.isEmpty()) {
			throw new IllegalArgumentException(erros.toString());
		}
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}
}
