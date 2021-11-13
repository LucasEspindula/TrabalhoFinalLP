import org.junit.Assert;
import org.junit.Test;


public class ClienteTest {
	private helper geradorCaracters = new helper();
	
	@Test
	public void deveTestarAcriacaoDeClienteValido() {
		
		Endereco enderecoOsvaldo = new Endereco(1, "Rua Biribiri", "12", "Bairro andromeda", null, "123456", "Alvorada", "RS");
		Contato contatoOsvaldo = new Contato(1, "osvaldo@gmail.com", "123456789");
		Cliente clienteTeste = new Cliente(1, contatoOsvaldo, enderecoOsvaldo, "Osvaldo Silva", "123456789");
		
		Assert.assertEquals(1, clienteTeste.getId(), 1);
		Assert.assertEquals(contatoOsvaldo, clienteTeste.getContato());
		Assert.assertEquals(enderecoOsvaldo, clienteTeste.getEndereco());
		Assert.assertEquals("Osvaldo Silva", clienteTeste.getNome());
		Assert.assertEquals("123456789", clienteTeste.getCpf());
	}
	
	@Test
	public void deveTestarAcriacaoDeClienteNullo() {
		
		try {
			new Cliente(null, null, null, null);
		} catch (Exception e) {
			Assert.assertEquals("[informe um contato, informe um endereco, informe um Nome, informe um cpf]", e.getMessage());
		}
	}
	
	@Test
	public void deveTestarAcriacaoDeClientePreenchidoVazio() {
		
		try {
			new Cliente(null, null, " ", " ");
		} catch (Exception e) {
			Assert.assertEquals("[informe um contato, informe um endereco, informe um Nome, informe um cpf]", e.getMessage());
		}
	}
	
	@Test
	public void deveTestarACriacaoDeClienteComNumeroMaximoDeCaracter() {
		
		String nome = geradorCaracters.gerador(101);
		String cpf = geradorCaracters.gerador(21);
		
		try {
			new Cliente(null, null, nome, cpf);
		} catch (Exception e) {
			
			Assert.assertEquals("[informe um contato, informe um endereco, "
					+ "Numero maximo de caracter eh 100 para o nome, "
					+ "Numero maximo de caracter eh 20 para o cpf]", e.getMessage());
		}
	}	
}
