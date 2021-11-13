import org.junit.Test;
import java.util.List;
import org.junit.Assert;

public class PedidoTest {
	
	@Test
	public void deveTestarACriacaoDeUmPedidoValido() {
		
		Endereco enderecoFornecedor = new Endereco(1, "Rua Biribiri", "12", "Bairro andromeda", null, "123456", "Alvorada", "RS");
		Contato contatoFornecedor = new Contato(1, "osvaldo@gmail.com", "123456789");
		Fornecedor fornecedorTeste = new Fornecedor(1, contatoFornecedor, enderecoFornecedor, "Pastelaria 007", "123456789");
		
		Endereco enderecoCliente = new Endereco(1, "Rua Biribiri", "12", "Bairro andromeda", null, "123456", "Alvorada", "RS");
		Contato contatoCliente = new Contato(1, "osvaldo@gmail.com", "123456789");
		Cliente clienteTeste = new Cliente(1, contatoCliente, enderecoCliente, "Osvaldo Silva", "123456789");
		
		Produto produtoTest = new Produto("Teclado", "Teclado RGB", 20.00);
		Item itemTest = new Item(produtoTest, 5);
		
		Pedido pedidoTeste = new Pedido(fornecedorTeste, clienteTeste, 10.0, itemTest);
		
		List<Item> itensEsperados = List.of(itemTest);
		// compara lista com lista
		
		Assert.assertEquals(fornecedorTeste, pedidoTeste.getFornecedor());
		Assert.assertEquals(clienteTeste, pedidoTeste.getCliente());
		Assert.assertEquals(10.0, pedidoTeste.getValorFrete(), 1);
		Assert.assertEquals(itensEsperados, pedidoTeste.getItens());
		
		Assert.assertEquals(100, pedidoTeste.getValorTotalItens(), 1);
		Assert.assertEquals(110, pedidoTeste.getValorTotal(), 1);
	}
	
	@Test
	public void deveTestarACriacaoDeUmPedidoNullo() {
		
		try {
			new Pedido(null, null, null, null);
		} catch (Exception e) {
			
			Assert.assertEquals("[informe um fornecedor, informe um cliente, "
					+ "informe o valor do frete, informe pelomenos um item]", e.getMessage());
		}
	}
}
