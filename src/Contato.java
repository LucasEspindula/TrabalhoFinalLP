import java.util.ArrayList;
import java.util.List;

public class Contato {
	
	private String email;
	
	private String telefone;

	public Contato(String email, String telefone) {
		super();
		this.email = email;
		this.telefone = telefone;
		validacaoContato();
	}
	
	protected void validacaoContato() {
		
		List<String> erros = new ArrayList<String>();
		
		if (email == null || email.isBlank()) {
			erros.add("informe um email");
		} else if (!email.contains("@") || !email.contains(".com")) {
			erros.add("informe um email que contenha @ e .com");
		} else if (email != null && email.length() > 100) {
			erros.add("Numero maximo de caracter eh 100 para email");
		}
		
		if (telefone == null || telefone.isBlank()) {
			erros.add("informe um telefone");
		} else if (telefone != null && telefone.length() > 20) {
			erros.add("Numero maximo de caracter eh 20 para telefone");
		}
		
		if (!erros.isEmpty()) {
			throw new IllegalArgumentException(erros.toString());
		}
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}
}
