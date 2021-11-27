import java.util.ArrayList;
import java.util.List;

public class Produto implements Dominio<Integer>{
	
	private String nomeDoProduto;
	
	private String descricaoDoProduto;
	
	private Double valorUnitario;
	
	private int identificador;

	// CONSTRUTOR PARA INSERT
	public Produto(String nomeDoProduto, String descricaoDoProduto, Double valorUnitario) {
		this.nomeDoProduto = nomeDoProduto;
		this.descricaoDoProduto = descricaoDoProduto;
		this.valorUnitario = valorUnitario;
		validacao();
	}
	
	// CONSTRUTOR PARA UPDATE
	public Produto(int identificador, String nomeDoProduto, String descricaoDoProduto, Double valorUnitario) {
		this.nomeDoProduto = nomeDoProduto;
		this.descricaoDoProduto = descricaoDoProduto;
		this.valorUnitario = valorUnitario;
		this.identificador = identificador;
		validacao();
	}
	
	protected void validacao() {
		
		List<String> erros = new ArrayList<String>();
		
		if (nomeDoProduto == null || nomeDoProduto.isBlank()) {
			erros.add("informe um nome do produto");
		} else if (nomeDoProduto != null && nomeDoProduto.length() > 100) {
			erros.add("Numero maximo de caracter eh 100 para nome do produto");
		} 
		
		if (descricaoDoProduto != null && descricaoDoProduto.length() > 500) {
			erros.add("Numero maximo de caracter eh 500 para descricao do produto");
		} 
		
		if (valorUnitario == null || valorUnitario <= 0) {
			erros.add("informe um valor valido para o produto");
		}

		if (!erros.isEmpty()) {
			throw new IllegalArgumentException(erros.toString());
		}
	}

	public String getNomeDoProduto() {
		return nomeDoProduto;
	}

	public String getDescricaoDoProduto() {
		return descricaoDoProduto;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}
	
	public Integer getId() {
		return identificador;
	}
}
