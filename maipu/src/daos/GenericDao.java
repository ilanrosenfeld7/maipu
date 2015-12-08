package daos;

import java.util.ArrayList;
import java.util.HashMap;

public interface GenericDao<T> {
	public void guardar(T elemento);
	public ArrayList<T> recuperarTodos();
	public void actualizar(T elemento);
	public T recuperar(int id);
	public ArrayList<T> recuperar(int page,HashMap<String, Object> parametros, int elementsPerBlock);
	public int cantidad(HashMap<String, Object> parametros);
	public void eliminar(int id);
}
