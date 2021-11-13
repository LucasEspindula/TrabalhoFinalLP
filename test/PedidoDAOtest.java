import org.junit.Test;

public class PedidoDAOtest {
	
	@Test
	public void deveSalvarUmPedido() {
		
		PedidoDAO pedidoTeste = new PedidoDAO();
		
		Endereco enderecoFornecedor = new Endereco(1, "Rua Fornecedor ", "12", "Bairro andromeda", null, "123456", "Alvorada", "RS");
		Contato contatoFornecedor = new Contato(1, "Fornecedor@gmail.com", "123456789");
		
		Endereco enderecoTeste = new Endereco(1, "Rua Biribiri", "12", "Bairro andromeda", null, "123456", "Alvorada", "RS");
		Contato contatoTeste = new Contato(1, "mariobros@gmail.com", "123456789");
		
		Fornecedor fornecedorTeste = new Fornecedor(1, contatoFornecedor, enderecoFornecedor, "Pastelaria 007", "123456789");
		Cliente clienteTeste = new Cliente(1, contatoTeste, enderecoTeste, "Mario", "123456789");
		
		Produto produtoTest = new Produto("Teclado", "Teclado RGB", 20.00);
		Item itemTest = new Item(produtoTest, 5);
		
		Pedido testePedido = new Pedido(fornecedorTeste, clienteTeste, 10.00, itemTest);
		pedidoTeste.add(testePedido);
	}
}
