import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAO implements GenericDAO<Cliente, Integer> {

	private Connection conexao;

	public ClienteDAO() {
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
	public void add(Cliente model) {

		if (validaCliente(model.getCpf())) {

			throw new IllegalArgumentException("Cliente jah registrado!");
		} else {

			try {

				String SQLfornecedor = "INSERT INTO cliente (nome_cl, cpf, email_cl, telefone_cl, cep_cl, estado_cl, "
						+ "cidade_cl, rua_cl, bairro_cl, numero_cl, complemento_cl) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStatementFornecedor = conexao.prepareStatement(SQLfornecedor);
				preparedStatementFornecedor.setString(1, model.getNome());
				preparedStatementFornecedor.setString(2, model.getCpf());
				preparedStatementFornecedor.setString(3, model.getContato().getEmail());
				preparedStatementFornecedor.setString(4, model.getContato().getTelefone());
				preparedStatementFornecedor.setString(5, model.getEndereco().getCep());
				preparedStatementFornecedor.setString(6, model.getEndereco().getEstado());
				preparedStatementFornecedor.setString(7, model.getEndereco().getCidade());
				preparedStatementFornecedor.setString(8, model.getEndereco().getRua());
				preparedStatementFornecedor.setString(9, model.getEndereco().getBairro());
				preparedStatementFornecedor.setString(10, model.getEndereco().getNumero());
				preparedStatementFornecedor.setString(11, model.getEndereco().getComplemento());
				preparedStatementFornecedor.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean validaCliente(String cpf) {

		try {

			String sql = "SELECT * FROM cliente where cpf='" + cpf + "'";
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			ResultSet resultado = preparedStatement.executeQuery();
			return resultado.next();

		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException.getMessage());
		}
	}

	@Override
	public List<Cliente> buscar() {
		return null;
	}

	@Override
	public Cliente buscarPorId(Integer id) {
		return null;
	}
}
