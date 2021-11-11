import org.junit.Assert;
import org.junit.Test;



public class ProdutoTest {
	private helper geradorCaracters = new helper();
	
	@Test
	public void deveCriarUmProdutoValido() {
		
		Produto produtoTest = new Produto("Teclado", "Teclado RGB", 20.00);
		
		Assert.assertEquals("Teclado", produtoTest.getNomeDoProduto());
		Assert.assertEquals("Teclado RGB", produtoTest.getDescricaoDoProduto());
		Assert.assertEquals(20.00, produtoTest.getValorUnitario(), 1);
	}
	// fornecedor, cliente, pedido, produto
	@Test
	public void deveTestarAcriacaoDeProdutoNullo() {
		
		try {
			new Produto(null, null, null);
		} catch (Exception e) {
			
			Assert.assertEquals("[informe um nome do produto, "
					+ "informe um valor valido para o produto]", e.getMessage());
						// descricao nao eh obrigatorio, entao nao gera mensagem de erro se = null ou = vazio
		}
	}
	
	@Test
	public void deveTestarAcriacaoDeProdutoPreenchidoVazio() {
		
		try {
			new Produto(" ", " ", 0.0);
		} catch (Exception e) {
			
			Assert.assertEquals("[informe um nome do produto, "
					+ "informe um valor valido para o produto]", e.getMessage());
			// descricao nao eh obrigatorio, entao nao gera mensagem de erro se = null ou = vazio
		}
	}
	
	@Test
	public void deveTestarAcriacaoDeUmProdutoComValorNegativo() {
		
		try {
			new Produto("TESTE", "TESTE", -1.0);
		} catch (Exception e) {
			
			Assert.assertEquals("[informe um valor valido para o produto]", e.getMessage());
		}
	}
	
	@Test
	public void deveTestarACriacaoDeProdutoComNumeroMaximoDeCaracter() {
		
		String nomeDoProduto = geradorCaracters.gerador(101);
		String descricaoDoProduto = geradorCaracters.gerador(501);
		
		try {
			new Produto(nomeDoProduto, descricaoDoProduto, 10.0);
		} catch (Exception e) {
			
			Assert.assertEquals("[Numero maximo de caracter eh 100 para nome do produto, "
					+ "Numero maximo de caracter eh 500 para descricao do produto]", e.getMessage());
		}
	}
}
