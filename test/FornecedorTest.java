import org.junit.Assert;
import org.junit.Test;

public class FornecedorTest {
	private helper geradorCaracters = new helper();
	
	@Test
	public void deveTestarAcriacaoDeFornecedorValido() {
		
		Endereco enderecoFornecedor = new Endereco("Rua Biribiri", "12", "Bairro andromeda", null, "123456", "Alvorada", "RS");
		Contato contatoFornecedor = new Contato("osvaldo@gmail.com", "123456789");
		new Fornecedor(contatoFornecedor, enderecoFornecedor, "Pastelaria 007", "123456789");
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
	public void deveTestarACriacaoDeClienteComNumeroMaximoDeCaracter() {
		
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
