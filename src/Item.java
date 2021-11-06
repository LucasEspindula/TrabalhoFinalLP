
public class Item {
	
	private Produto produto;
	
	private int quantidade;
	
	private Double valorTotalItem;

	public Item(Produto produto, int quantidade, Double valorTotalItem) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorTotalItem = valorTotalItem;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Double getValorTotalItem() {
		return valorTotalItem;
	}
}
