import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FornecedorDAO implements GenericDAO<Fornecedor, Integer>{

	private Connection conexao;
	
	public FornecedorDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ecommerce";
			conexao = DriverManager.getConnection (url, "root", "21317046");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void add(Fornecedor model) {
		try {
			String insertEndereco = "INSERT INTO endereco (cep, estado, cidade, rua, bairro, numero, complemento) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatementEndereco = conexao.prepareStatement(insertEndereco, Statement.RETURN_GENERATED_KEYS);
			preparedStatementEndereco.setString(1, model.getEndereco().getCep());
			preparedStatementEndereco.setString(2, model.getEndereco().getEstado());
			preparedStatementEndereco.setString(3, model.getEndereco().getCidade());
			preparedStatementEndereco.setString(4, model.getEndereco().getRua());
			preparedStatementEndereco.setString(5, model.getEndereco().getBairro());
			preparedStatementEndereco.setString(6, model.getEndereco().getNumero());
			preparedStatementEndereco.setString(7, model.getEndereco().getComplemento());
			preparedStatementEndereco.execute();
			
			long enderecoId = 0;
	        try (ResultSet generatedKeys = preparedStatementEndereco.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	enderecoId = generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			
			String SQL = "INSERT INTO fornecedor (id_endereco, nomeFantasia, cnpj, email, telefone) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conexao.prepareStatement(SQL);
			preparedStatement.setLong(1, enderecoId);
			preparedStatement.setString(2, model.getNomeFantasia());
			preparedStatement.setString(3, model.getCnpj());
			preparedStatement.setString(4, model.getContato().getEmail());
			preparedStatement.setString(5, model.getContato().getTelefone());
			preparedStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Fornecedor> buscar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fornecedor buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
