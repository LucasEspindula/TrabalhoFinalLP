import org.junit.Test;

public class FornecedorDAOtest {
	
	@Test
	public void deveSalvarUmFornecedor() {
		
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		
		Endereco enderecoFornecedor = new Endereco("Rua Fornecedor ", "12", "Bairro andromeda", null, "123456", "Alvorada", "RS");
		Contato contatoFornecedor = new Contato("Fornecedor@gmail.com", "123456789");
		
		Fornecedor fornecedorTeste = new Fornecedor(1, contatoFornecedor, enderecoFornecedor, "Pastelaria 007", "123456789");
		fornecedorDAO.add(fornecedorTeste);
	}
}
