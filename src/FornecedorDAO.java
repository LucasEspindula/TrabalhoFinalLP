import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FornecedorDAO implements GenericDAO<Fornecedor, Integer> {

	private Connection conexao;

	public FornecedorDAO() {
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
	public void add(Fornecedor model) {

		if (validaFornecedor(model.getCnpj())) {

			throw new IllegalArgumentException("Fornecedor jah registrado!");
		} else {

			try {

				String SQLfornecedor = "INSERT INTO fornecedor (nomeFantasia, cnpj, email_fr, telefone_fr, cep_fr, estado_fr, "
						+ "cidade_fr, rua_fr, bairro_fr, numero_fr, complemento_fr) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStatementFornecedor = conexao.prepareStatement(SQLfornecedor);
				preparedStatementFornecedor.setString(1, model.getNomeFantasia());
				preparedStatementFornecedor.setString(2, model.getCnpj());
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

	public boolean validaFornecedor(String cnpj) {

		try {

			String sql = "SELECT * FROM fornecedor where cnpj='" + cnpj + "'";
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			ResultSet resultado = preparedStatement.executeQuery();
			return resultado.next();

		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException.getMessage());
		}

	}

	@Override
	public List<Fornecedor> buscar() {
		return null;
	}

	@Override
	public Fornecedor buscarPorId(Integer id) {
		return null;
	}
}
