import org.junit.Assert;
import org.junit.Test;

public class FornecedorTest {
	private helper geradorCaracters = new helper();
	
	@Test
	public void deveTestarAcriacaoDeFornecedorValido() {
		
		Endereco enderecoFornecedor = new Endereco(1, "Rua Biribiri", "12", "Bairro andromeda", null, "123456", "Alvorada", "RS");
		Contato contatoFornecedor = new Contato(1, "osvaldo@gmail.com", "123456789");
		Fornecedor fornecedorTeste = new Fornecedor(1, contatoFornecedor, enderecoFornecedor, "Pastelaria 007", "123456789");
		
		Assert.assertEquals(1, fornecedorTeste.getId(), 1);
		Assert.assertEquals(contatoFornecedor, fornecedorTeste.getContato());
		Assert.assertEquals(enderecoFornecedor, fornecedorTeste.getEndereco());
		Assert.assertEquals("Pastelaria 007", fornecedorTeste.getNomeFantasia());
		Assert.assertEquals("123456789", fornecedorTeste.getCnpj());
	}
	
	@Test
	public void deveTestarAcriacaoDeFornecedorNullo() {
		
		try {
			new Fornecedor(null, null, null, null);
		} catch (Exception e) {
			
			Assert.assertEquals("[informe um contato, informe um endereco, informe um Nome Fantasia, "
					+ "informe um cnpj]", e.getMessage());
		}
	}
	
	@Test
	public void deveTestarAcriacaoDeFornecedorPreenchidoVazio() {
		
		try {
			new Fornecedor(null, null, " ", " ");
		} catch (Exception e) {
			Assert.assertEquals("[informe um contato, informe um endereco, informe um Nome Fantasia, "
					+ "informe um cnpj]", e.getMessage());
		}
	}
	
	@Test
	public void deveTestarAFornecedorDeClienteComNumeroMaximoDeCaracter() {
		
		String nome = geradorCaracters.gerador(101);
		String cpf = geradorCaracters.gerador(21);
		
		try {
			new Fornecedor(null, null, nome, cpf);
		} catch (Exception e) {
			
			Assert.assertEquals("[informe um contato, informe um endereco, "
					+ "Numero maximo de caracter eh 100 para o nome fantasia, "
					+ "Numero maximo de caracter eh 20 para o cnpj]", e.getMessage());
		}
	}
}
