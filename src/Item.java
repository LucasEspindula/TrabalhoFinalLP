import java.util.ArrayList;
import java.util.List;

public class Item {
	
	private Produto produto;
	
	private int quantidade;

	public Item(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		validacao();
	}
	
	protected void validacao() {
		
		List<String> erros = new ArrayList<String>();
		
		if (produto == null) {
			erros.add("informe um produto valido");
		}
		
		if (quantidade <= 0) {
			erros.add("informe uma quantidade valida para o item");
		}

		if (!erros.isEmpty()) {
			throw new IllegalArgumentException(erros.toString());
		}
	}
	
	public double getValorTotal() {
		return produto.getValorUnitario() * quantidade;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}
}
