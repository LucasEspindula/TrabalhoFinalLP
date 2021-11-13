import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

			// ENDERECO CLIENTE
			String insertEnderecoDoCliente = "INSERT INTO endereco (cep, estado, cidade, rua, bairro, numero, complemento) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatementEnderecoDoCliente = conexao.prepareStatement(insertEnderecoDoCliente,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatementEnderecoDoCliente.setString(1, model.getCliente().getEndereco().getCep());
			preparedStatementEnderecoDoCliente.setString(2, model.getCliente().getEndereco().getEstado());
			preparedStatementEnderecoDoCliente.setString(3, model.getCliente().getEndereco().getCidade());
			preparedStatementEnderecoDoCliente.setString(4, model.getCliente().getEndereco().getRua());
			preparedStatementEnderecoDoCliente.setString(5, model.getCliente().getEndereco().getBairro());
			preparedStatementEnderecoDoCliente.setString(6, model.getCliente().getEndereco().getNumero());
			preparedStatementEnderecoDoCliente.setString(7, model.getCliente().getEndereco().getComplemento());
			preparedStatementEnderecoDoCliente.execute();

			long enderecoIdDoCliente = 0;
			try (ResultSet generatedKeys = preparedStatementEnderecoDoCliente.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					enderecoIdDoCliente = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

			// CLIENTE
			String SQLcliente = "INSERT INTO cliente (id_endereco, nome, cpf, email, telefone) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conexao.prepareStatement(SQLcliente,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, enderecoIdDoCliente);
			preparedStatement.setString(2, model.getCliente().getNome());
			preparedStatement.setString(3, model.getCliente().getCpf());
			preparedStatement.setString(4, model.getCliente().getContato().getEmail());
			preparedStatement.setString(5, model.getCliente().getContato().getTelefone());
			preparedStatement.execute();

			// ENDERECO FORNECEDOR
			String insertEnderecoDoFornecedor = "INSERT INTO endereco (cep, estado, cidade, rua, bairro, numero, complemento) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatementEnderecoDoFornecedor = conexao
					.prepareStatement(insertEnderecoDoFornecedor, Statement.RETURN_GENERATED_KEYS);
			preparedStatementEnderecoDoFornecedor.setString(1, model.getFornecedor().getEndereco().getCep());
			preparedStatementEnderecoDoFornecedor.setString(2, model.getFornecedor().getEndereco().getEstado());
			preparedStatementEnderecoDoFornecedor.setString(3, model.getFornecedor().getEndereco().getCidade());
			preparedStatementEnderecoDoFornecedor.setString(4, model.getFornecedor().getEndereco().getRua());
			preparedStatementEnderecoDoFornecedor.setString(5, model.getFornecedor().getEndereco().getBairro());
			preparedStatementEnderecoDoFornecedor.setString(6, model.getFornecedor().getEndereco().getNumero());
			preparedStatementEnderecoDoFornecedor.setString(7, model.getFornecedor().getEndereco().getComplemento());
			preparedStatementEnderecoDoFornecedor.execute();

			long enderecoIdDoFornecedor = 0;
			try (ResultSet generatedKeys = preparedStatementEnderecoDoFornecedor.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					enderecoIdDoFornecedor = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

			// FORNECEDOR
			String SQLfornecedor = "INSERT INTO fornecedor (id_endereco, nomeFantasia, cnpj, email, telefone) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement preparedStatementFornecedor = conexao.prepareStatement(SQLfornecedor,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatementFornecedor.setLong(1, enderecoIdDoFornecedor);
			preparedStatementFornecedor.setString(2, model.getFornecedor().getNomeFantasia());
			preparedStatementFornecedor.setString(3, model.getFornecedor().getCnpj());
			preparedStatementFornecedor.setString(4, model.getFornecedor().getContato().getEmail());
			preparedStatementFornecedor.setString(5, model.getFornecedor().getContato().getTelefone());
			preparedStatementFornecedor.execute();

			// PEDIDO
			String SQLpedido = "INSERT INTO pedido (id_fornecedor, id_cliente, valorTotalItens, valorFrete, valorTotal, dataCompra) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatementPedido = conexao.prepareStatement(SQLpedido,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatementPedido.setLong(1, enderecoIdDoFornecedor);
			preparedStatementPedido.setLong(2, enderecoIdDoCliente);
			preparedStatementPedido.setDouble(3, model.getValorTotalItens());
			preparedStatementPedido.setDouble(4, model.getValorFrete());
			preparedStatementPedido.setDouble(5, model.getValorTotal());
			preparedStatementPedido.setObject(6, model.getDataCompra());
			preparedStatementPedido.execute();
			
			long pedidoIdDoItem = 0;
			try (ResultSet generatedKeys = preparedStatementPedido.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					pedidoIdDoItem = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

			for (Item itens : model.getItens()) {

				String SQLproduto = "INSERT INTO produto (nome, descricao, valorUnitario) VALUES(?, ?, ?)";
				PreparedStatement preparedStatementProduto = conexao.prepareStatement(SQLproduto,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatementProduto.setObject(1, itens.getProduto().getNomeDoProduto());
				preparedStatementProduto.setString(2, itens.getProduto().getDescricaoDoProduto());
				preparedStatementProduto.setDouble(3, itens.getProduto().getValorUnitario());
				preparedStatementProduto.execute();

				long produtoIdDoItem = 0;
				try (ResultSet generatedKeys = preparedStatementProduto.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						produtoIdDoItem = generatedKeys.getLong(1);
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}

				String SQLitem = "INSERT INTO item (id_produto, id_pedido, quantidade) VALUES(?, ?, ?)";
				PreparedStatement preparedStatementItem = conexao.prepareStatement(SQLitem,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatementItem.setLong(1, produtoIdDoItem);
				preparedStatementItem.setLong(2, pedidoIdDoItem);
				preparedStatementItem.setDouble(3, itens.getQuantidade());
				preparedStatementItem.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Pedido> buscar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
