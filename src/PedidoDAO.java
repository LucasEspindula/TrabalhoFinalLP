import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements GenericDAO<Pedido, Integer> {

	private Connection conexao;

	public PedidoDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ecommerce";
			conexao = DriverManager.getConnection(url, "root", "21317046");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Pedido model) {
		try {

			String SQLpedido = "INSERT INTO pedido (id_fornecedor, id_cliente, valorTotalItens, valorFrete, valorTotal, "
					+ "dataCompra) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatementPedido = conexao.prepareStatement(SQLpedido,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatementPedido.setLong(1, model.getFornecedor().getId());
			preparedStatementPedido.setLong(2, model.getCliente().getId());
			preparedStatementPedido.setDouble(3, model.getValorTotalItens());
			preparedStatementPedido.setDouble(4, model.getValorFrete());
			preparedStatementPedido.setDouble(5, model.getValorTotal());
			preparedStatementPedido.setObject(6, model.getDataCompra());
			preparedStatementPedido.execute();

			long idDoPedido = 0;
			try (ResultSet generatedKeys = preparedStatementPedido.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idDoPedido = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

			for (Item itens : model.getItens()) {

				try {
					String SQLitem = "INSERT INTO item (id_produto, id_pedido, quantidade) VALUES(?, ?, ?)";
					PreparedStatement preparedStatementItem = conexao.prepareStatement(SQLitem);
					preparedStatementItem.setLong(1, itens.getProduto().getId());
					preparedStatementItem.setLong(2, idDoPedido);
					preparedStatementItem.setDouble(3, itens.getQuantidade());
					preparedStatementItem.execute();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void trundatePedido() {

		try {
			List<String> truncates = new ArrayList<>();
			truncates.add("truncate cliente");
			truncates.add("truncate fornecedor");
			truncates.add("truncate produto");
			truncates.add("truncate item");
			truncates.add("truncate pedido");

			for (String sql : truncates) {

				PreparedStatement preparedStatement = conexao.prepareStatement(sql);
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Pedido> buscar() {
		
		List<Pedido> pedidosSalvos = new ArrayList<Pedido>();
		
		try {
			String SQL = "select *, pr.nome as nomeProduto from pedido p "
					+ "inner join fornecedor f on f.id_fornecedor = p.id_fornecedor "
					+ "inner join cliente c on c.id_cliente = p.id_cliente "
					+ "inner join item i on i.id_pedido = p.id_pedido "
					+ "inner join produto pr on pr.id_produto = i.id_produto "
					+ " order by pr.nome";

			PreparedStatement preparedStatementbuscarid = conexao.prepareStatement(SQL);
			ResultSet rs = preparedStatementbuscarid.executeQuery();

			int idPedido = 0;
			Double ValorFrete = 0.0;
			LocalDateTime dataCompra = null;
			Fornecedor fornecedorTeste = null;
			Cliente clienteTeste = null;

			List<Item> itens = new ArrayList<>();

			while (rs.next()) {

				int idFornecedor = rs.getInt("id_fornecedor");
				String nomeFornecedor = rs.getString("nomeFantasia");
				String cnpj = rs.getString("cnpj");
				String email_fr = rs.getString("email_fr");
				String telefone_fr = rs.getString("telefone_fr");
				String rua_fr = rs.getString("rua_fr");
				String numero_fr = rs.getString("numero_fr");
				String bairro_fr = rs.getString("bairro_fr");
				String complemento_fr = rs.getString("complemento_fr");
				String cep_fr = rs.getString("cep_fr");
				String cidade_fr = rs.getString("cidade_fr");
				String estado_fr = rs.getString("estado_fr");

				Endereco enderecoFornecedor = new Endereco(rua_fr, numero_fr, bairro_fr, complemento_fr, cep_fr,
						cidade_fr, estado_fr);
				Contato contatoFornecedor = new Contato(email_fr, telefone_fr);
				fornecedorTeste = new Fornecedor(idFornecedor, contatoFornecedor, enderecoFornecedor, nomeFornecedor,
						cnpj);

				int idCliente = rs.getInt("id_cliente");
				String nome_cl = rs.getString("nome_cl");
				String cpf = rs.getString("cpf");
				String email_cl = rs.getString("email_cl");
				String telefone_cl = rs.getString("telefone_cl");
				String rua_cl = rs.getString("rua_cl");
				String numero_cl = rs.getString("numero_cl");
				String bairro_cl = rs.getString("bairro_cl");
				String complemento_cl = rs.getString("complemento_cl");
				String cep_cl = rs.getString("cep_fr");
				String cidade_cl = rs.getString("cidade_cl");
				String estado_cl = rs.getString("estado_cl");

				Endereco enderecoCliente = new Endereco(rua_cl, numero_cl, bairro_cl, complemento_cl, cep_cl, cidade_cl,
						estado_cl);
				Contato contatoCliente = new Contato(email_cl, telefone_cl);
				clienteTeste = new Cliente(idCliente, contatoCliente, enderecoCliente, nome_cl, cpf);

				int idProduto = rs.getInt("id_produto");
				String nomeProduto = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Double valorUnitario = rs.getDouble("valorUnitario");

				Produto produtoTest = new Produto(idProduto, nomeProduto, descricao, valorUnitario);

				int quantidade = rs.getInt("quantidade");

				Item itemTest = new Item(produtoTest, quantidade);
				itens.add(itemTest);

				idPedido = rs.getInt("id_pedido");
				ValorFrete = rs.getDouble("valorFrete");

				dataCompra = rs.getObject("dataCompra", LocalDateTime.class);
				
				Pedido pedidos = new Pedido(idPedido, fornecedorTeste, clienteTeste, ValorFrete, dataCompra, itens);
				pedidosSalvos.add(pedidos);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pedidosSalvos;
	}

	@Override
	public Pedido buscarPorId(Integer id) {

		try {
			String SQL = "select *, pr.nome as nomeProduto from pedido p "
					+ "inner join fornecedor f on f.id_fornecedor = p.id_fornecedor "
					+ "inner join cliente c on c.id_cliente = p.id_cliente "
					+ "inner join item i on i.id_pedido = p.id_pedido "
					+ "inner join produto pr on pr.id_produto = i.id_produto " + "where p.id_pedido = " + id
					+ " order by pr.nome";

			PreparedStatement preparedStatementbuscarid = conexao.prepareStatement(SQL);
			ResultSet rs = preparedStatementbuscarid.executeQuery();

			int idPedido = 0;
			Double ValorFrete = 0.0;
			LocalDateTime dataCompra = null;
			Fornecedor fornecedorTeste = null;
			Cliente clienteTeste = null;

			List<Item> itens = new ArrayList<>();

			while (rs.next()) {

				int idFornecedor = rs.getInt("id_fornecedor");
				String nomeFornecedor = rs.getString("nomeFantasia");
				String cnpj = rs.getString("cnpj");
				String email_fr = rs.getString("email_fr");
				String telefone_fr = rs.getString("telefone_fr");
				String rua_fr = rs.getString("rua_fr");
				String numero_fr = rs.getString("numero_fr");
				String bairro_fr = rs.getString("bairro_fr");
				String complemento_fr = rs.getString("complemento_fr");
				String cep_fr = rs.getString("cep_fr");
				String cidade_fr = rs.getString("cidade_fr");
				String estado_fr = rs.getString("estado_fr");

				Endereco enderecoFornecedor = new Endereco(rua_fr, numero_fr, bairro_fr, complemento_fr, cep_fr,
						cidade_fr, estado_fr);
				Contato contatoFornecedor = new Contato(email_fr, telefone_fr);
				fornecedorTeste = new Fornecedor(idFornecedor, contatoFornecedor, enderecoFornecedor, nomeFornecedor,
						cnpj);

				int idCliente = rs.getInt("id_cliente");
				String nome_cl = rs.getString("nome_cl");
				String cpf = rs.getString("cpf");
				String email_cl = rs.getString("email_cl");
				String telefone_cl = rs.getString("telefone_cl");
				String rua_cl = rs.getString("rua_cl");
				String numero_cl = rs.getString("numero_cl");
				String bairro_cl = rs.getString("bairro_cl");
				String complemento_cl = rs.getString("complemento_cl");
				String cep_cl = rs.getString("cep_fr");
				String cidade_cl = rs.getString("cidade_cl");
				String estado_cl = rs.getString("estado_cl");

				Endereco enderecoCliente = new Endereco(rua_cl, numero_cl, bairro_cl, complemento_cl, cep_cl, cidade_cl,
						estado_cl);
				Contato contatoCliente = new Contato(email_cl, telefone_cl);
				clienteTeste = new Cliente(idCliente, contatoCliente, enderecoCliente, nome_cl, cpf);

				int idProduto = rs.getInt("id_produto");
				String nomeProduto = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Double valorUnitario = rs.getDouble("valorUnitario");

				Produto produtoTest = new Produto(idProduto, nomeProduto, descricao, valorUnitario);

				int quantidade = rs.getInt("quantidade");

				Item itemTest = new Item(produtoTest, quantidade);
				itens.add(itemTest);

				idPedido = rs.getInt("id_pedido");
				ValorFrete = rs.getDouble("valorFrete");

				dataCompra = rs.getObject("dataCompra", LocalDateTime.class);

			}
			return new Pedido(idPedido, fornecedorTeste, clienteTeste, ValorFrete, dataCompra, itens);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
