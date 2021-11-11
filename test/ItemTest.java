import org.junit.Assert;
import org.junit.Test;

public class ItemTest {
	
	@Test
	public void deveTestarACriacaoDeUmItemValido() {
		
		Produto produtoTest = new Produto("Teclado", "Teclado RGB", 20.00);
		Item itemTest = new Item(produtoTest, 5);

		Assert.assertEquals(produtoTest, itemTest.getProduto());
		Assert.assertEquals(5, itemTest.getQuantidade());
		Assert.assertEquals(100, itemTest.getValorTotal(), 1);
	}
	
	@Test
	public void deveTestarACriacaoDeItemComProdutoNulloEQuantidadeNegativa() {
		
		try {
			new Item(null, -1);
		} catch (Exception e) {
			
			Assert.assertEquals("[informe um produto valido, "
					+ "informe uma quantidade valida para o item]", e.getMessage());
		}
	}
}
