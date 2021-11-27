import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.Assert;

public class PedidoDAOtest {

	private PedidoDAO pedidoDAO;
	private Produto produtoTest;
	private Produto produtoTest2;
	private Fornecedor fornecedorTeste;
	private Cliente clienteTeste;

	@BeforeEach
	public void inicializar() {

		pedidoDAO = new PedidoDAO();

		// registra endereco e contato do fornecedor
		Endereco enderecoFornecedor = new Endereco("Rua Fornecedor", "12", "Bairro andromeda", null, "123456",
				"PortoAlegre", "RS");
		Contato contatoFornecedor = new Contato("Fornecedor@gmail.com", "123456789");

		// registra endereco e contato do cliente
		Endereco enderecoCliente = new Endereco("Rua Cliente", "13", "Bairro fenix", null, "12345", "Alvorada", "RS");
		Contato contatoCliente = new Contato("Cliente@gmail.com", "123456789");

		// insere fornecedor
		fornecedorTeste = new Fornecedor(1, contatoFornecedor, enderecoFornecedor, "Pastelaria 007", "123456789");
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		fornecedorDAO.add(fornecedorTeste);

		// insere cliente
		clienteTeste = new Cliente(1, contatoCliente, enderecoCliente, "Mario", "123456789");
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.add(clienteTeste);

		// insere produto
		produtoTest = new Produto(1, "Teclado", "Teclado RGB", 20.00);
		produtoTest2 = new Produto(2, "Mouse", "Mouse RGB", 15.00);
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.add(produtoTest);
		produtoDAO.add(produtoTest2);

		Item item = new Item(produtoTest, 5);
		Item item2 = new Item(produtoTest2, 2);

		// insere pedido
		Pedido novoPedido = new Pedido(fornecedorTeste, clienteTeste, 10.00, item);
		novoPedido.adicionarItem(item2);

		pedidoDAO.add(novoPedido);
	}

	@AfterEach
	public void finalizarTestes() {
		pedidoDAO.trundatePedido();
	}
	
	@Test
	public void deveBuscarPorTodosOsPedidos() {
		pedidoDAO = new PedidoDAO();
		
		List<Pedido> pedidosBuscados = pedidoDAO.buscar();
		
		Assert.assertEquals(2, pedidosBuscados.size());
	}

	@Test
	public void deveBuscarPedidoPorId() {

		Pedido pedidoEncontrado = pedidoDAO.buscarPorId(1);

		// endereco fornecedor
		Assert.assertEquals("Rua Fornecedor", pedidoEncontrado.getFornecedor().getEndereco().getRua());
		Assert.assertEquals("12", pedidoEncontrado.getFornecedor().getEndereco().getNumero());
		Assert.assertEquals("Bairro andromeda", pedidoEncontrado.getFornecedor().getEndereco().getBairro());
		Assert.assertEquals(null, pedidoEncontrado.getFornecedor().getEndereco().getComplemento());
		Assert.assertEquals("123456", pedidoEncontrado.getFornecedor().getEndereco().getCep());
		Assert.assertEquals("PortoAlegre", pedidoEncontrado.getFornecedor().getEndereco().getCidade());
		Assert.assertEquals("RS", pedidoEncontrado.getFornecedor().getEndereco().getEstado());

		// contato fornecedor
		Assert.assertEquals("Fornecedor@gmail.com", pedidoEncontrado.getFornecedor().getContato().getEmail());
		Assert.assertEquals("123456789", pedidoEncontrado.getFornecedor().getContato().getTelefone());

		// endereco cliente
		Assert.assertEquals("Rua Cliente", pedidoEncontrado.getCliente().getEndereco().getRua());
		Assert.assertEquals("13", pedidoEncontrado.getCliente().getEndereco().getNumero());
		Assert.assertEquals("Bairro fenix", pedidoEncontrado.getCliente().getEndereco().getBairro());
		Assert.assertEquals(null, pedidoEncontrado.getCliente().getEndereco().getComplemento());
		Assert.assertEquals("123456", pedidoEncontrado.getCliente().getEndereco().getCep());
		Assert.assertEquals("Alvorada", pedidoEncontrado.getCliente().getEndereco().getCidade());
		Assert.assertEquals("RS", pedidoEncontrado.getCliente().getEndereco().getEstado());

		// contato cliente
		Assert.assertEquals("Cliente@gmail.com", pedidoEncontrado.getCliente().getContato().getEmail());
		Assert.assertEquals("123456789", pedidoEncontrado.getCliente().getContato().getTelefone());

		// fornecedor
		Assert.assertEquals(1, pedidoEncontrado.getFornecedor().getId(), 1);
		Assert.assertEquals("Pastelaria 007", pedidoEncontrado.getFornecedor().getNomeFantasia());
		Assert.assertEquals("123456789", pedidoEncontrado.getFornecedor().getCnpj());

		// cliente
		Assert.assertEquals(1, pedidoEncontrado.getCliente().getId(), 1);
		Assert.assertEquals("Mario", pedidoEncontrado.getCliente().getNome());
		Assert.assertEquals("123456789", pedidoEncontrado.getCliente().getCpf());
		
		// produto 1
		var item = pedidoEncontrado.getItens().get(0);
		Assert.assertEquals(1, item.getProduto().getId(), 1);
		Assert.assertEquals("Mouse", item.getProduto().getNomeDoProduto());
		Assert.assertEquals("Mouse RGB", item.getProduto().getDescricaoDoProduto());
		Assert.assertEquals(15.00, item.getProduto().getValorUnitario(), 1);

		// item produto 1
		Assert.assertEquals(2, item.getQuantidade());
		
		// produto 2
		item = pedidoEncontrado.getItens().get(1);
		Assert.assertEquals(2, item.getProduto().getId(), 1);
		Assert.assertEquals("Teclado", item.getProduto().getNomeDoProduto());
		Assert.assertEquals("Teclado RGB", item.getProduto().getDescricaoDoProduto());
		Assert.assertEquals(20.00, item.getProduto().getValorUnitario(), 1);
		
		// item produto 2
		Assert.assertEquals(5, item.getQuantidade());

		// pedido
		Assert.assertEquals(1, pedidoEncontrado.getId(), 1);
		Assert.assertEquals(10.00, pedidoEncontrado.getValorFrete(), 1);
		Assert.assertEquals(140, pedidoEncontrado.getValorTotal(), 1);
		Assert.assertEquals(130, pedidoEncontrado.getValorTotalItens(), 1);
		Assert.assertNotNull(pedidoEncontrado.getDataCompra());
	}
}
