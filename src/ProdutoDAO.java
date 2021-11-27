import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDAO implements GenericDAO<Produto, Integer> {

	private Connection conexao;

	public ProdutoDAO() {
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
	public void add(Produto model) {

		try {
			String SQLproduto = "INSERT INTO produto (nome, descricao, valorUnitario) VALUES(?, ?, ?)";
			PreparedStatement preparedStatementProduto = conexao.prepareStatement(SQLproduto);

			preparedStatementProduto.setObject(1, model.getNomeDoProduto());
			preparedStatementProduto.setString(2, model.getDescricaoDoProduto());
			preparedStatementProduto.setDouble(3, model.getValorUnitario());
			preparedStatementProduto.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Produto> buscar() {
		return null;
	}

	@Override
	public Produto buscarPorId(Integer id) {
		return null;
	}
}
