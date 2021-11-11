import java.util.List;

public interface GenericDAO<T extends Dominio<K>, K> {
	
	public void add(T model);
	
	public List<T> buscar();
	
	public T buscarPorId(K id);
}
