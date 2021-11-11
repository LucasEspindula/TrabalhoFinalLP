import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	private LocalDateTime dataCompra;
	
	private Fornecedor fornecedor;
	
	private Cliente cliente;
	
	private Double valorFrete;
	
	private List<Item> itens = new ArrayList<>();

	public Pedido(Fornecedor fornecedor, Cliente cliente, Double valorFrete, Item itens) {
		this.dataCompra = LocalDateTime.now();
		this.fornecedor = fornecedor;
		this.cliente = cliente;
		this.valorFrete = valorFrete;
		adicionarItem(itens);
		validacao();
	}
	
	public void adicionarItem(Item item) {
		// methodo guarda chuva, impede do null entrar na lista
		
		if (item == null) {
			return;
		}
		this.itens.add(item);
	}
	
	protected void validacao() {
		
		List<String> erros = new ArrayList<String>();
		
		if (fornecedor == null) {
			erros.add("informe um fornecedor");
		}
		
		if (cliente == null) {
			erros.add("informe um cliente");
		}
		
		if (valorFrete == null || valorFrete < 0) {
			erros.add("informe o valor do frete");
		}
		
		if (itens.isEmpty()) {
			erros.add("informe pelomenos um item");
		}
		
		if (!erros.isEmpty()) {
			throw new IllegalArgumentException(erros.toString());
		}
	}

	public LocalDateTime getDataCompra() {
		return dataCompra;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Double getValorTotal() {
		return getValorTotalItens() + getValorFrete();
	}

	public Double getValorTotalItens() {
		double valorTotal = 0;		
		for (Item item : itens) {
			valorTotal += item.getValorTotal();
		}
		return valorTotal;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public List<Item> getItens() {
		return itens;
	}
}
