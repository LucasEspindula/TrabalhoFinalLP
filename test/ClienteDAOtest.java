import org.junit.Test;

public class ClienteDAOtest {

	@Test
	public void deveSalvarUmCliente() {
		
		ClienteDAO clienteDAOteste = new ClienteDAO();
		
		Endereco enderecoTeste = new Endereco("Rua Biribiri", "12", "Bairro andromeda", null, "123456", "Alvorada", "RS");
		
		Contato contatoTeste = new Contato("mariobros@gmail.com", "123456789");
		
		Cliente clienteTeste = new Cliente(1, contatoTeste, enderecoTeste, "Mario", "123456789");
		clienteDAOteste.add(clienteTeste);
	}
}
