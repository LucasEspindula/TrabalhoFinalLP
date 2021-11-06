import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	private LocalDateTime dataCompra;
	
	private Fornecedor fornecedor;
	
	private Cliente cliente;
	
	private Double valorTotal;
	
	private Double valorTotalItens;
	
	private Double valorFrete;
	
	private List<Item> itens;

	public Pedido(Fornecedor fornecedor, Cliente cliente, Double valorTotal, Double valorTotalItens, 
			Double valorFrete, List<Item> itens) {
		super();
		this.dataCompra = LocalDateTime.now();
		this.fornecedor = fornecedor;
		this.cliente = cliente;
		
		this.valorTotal = valorTotal;
		this.valorTotalItens = valorTotalItens;
		
		this.valorFrete = valorFrete;
		this.itens = itens;
	}
	
	protected void validacaoEndereco() {
		
		List<String> erros = new ArrayList<String>();
		
//		if (rua == null || rua.isBlank()) {
//			erros.add("informe uma rua");
//		} else if (rua != null && rua.length() != 100) {
//			erros.add("Numero maximo de caracter eh 100 para rua");
//		} 
//		
//		if (numero.length() != 6) {
//			erros.add("Numero maximo de caracter eh 6 para o numero");
//		}
//		
//		if (bairro == null || bairro.isBlank()) {
//			erros.add("informe um bairro");
//		} else if (bairro != null && bairro.length() != 50) {
//			erros.add("Numero maximo de caracter eh 50 para o bairro");
//		} 
//		
//		if (complemento.length() != 120) {
//			erros.add("Numero maximo de caracter eh 120 para o complemento");
//		}
//		
//		if (cep == null || cep.isBlank()) {
//			erros.add("informe um cep");
//		} else if (cep != null && cep.length() != 10) {
//			erros.add("Numero maximo de caracter eh 10 para o cep");
//		} 
//		
//		if (cidade == null || cidade.isBlank()) {
//			erros.add("informe uma cidade");
//		} else if (cidade != null && cidade.length() != 50) {
//			erros.add("Numero maximo de caracter eh 50 para a cidade");
//		}
//		
//		if (estado == null || estado.isBlank()) {
//			erros.add("informe um estado");
//		} else if (estado != null && estado.length() != 2) {
//			erros.add("Numero maximo de caracter eh 2 para o estado");
//		}
		
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
		return valorTotal;
	}

	public Double getValorTotalItens() {
		return valorTotalItens;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public List<Item> getItens() {
		return itens;
	}
}
