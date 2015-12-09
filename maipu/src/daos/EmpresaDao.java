package daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import clases.EMF;
import clases.Empresa;

public class EmpresaDao implements GenericDao<Empresa> {

	@Override
	public void guardar(Empresa empresa) {
		EntityManager e = EMF.getEMF().createEntityManager();
		EntityTransaction etx = e.getTransaction();
		etx.begin();
		e.persist(empresa);
		e.flush();
		etx.commit();
		e.close();
	}

	@Override
	public ArrayList<Empresa> recuperarTodos() {
		ArrayList<Empresa> resultado = null;
		EntityManager em = EMF.getEMF().createEntityManager();
		Query consulta = em
				.createQuery("select e from Empresa e");
		resultado = (ArrayList<Empresa>) consulta.getResultList();

		em.close();
		return resultado;
	}
	
	public ArrayList<String> recuperarNombresEmpresas() {
		ArrayList<String> resultado = null;
		ArrayList<Empresa> empresas = this.recuperarTodos();
		for(Empresa e: empresas){
			resultado.add(e.getNombre());
		}
		return resultado;
	}

	@Override
	public void actualizar(Empresa empresa) {
		EntityManager e = EMF.getEMF().createEntityManager();
		e.getTransaction().begin();
		e.merge(empresa);
		e.getTransaction().commit();
	}

	@Override
	public Empresa recuperar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**METODO QUE RECUPERA LAS EMPRESAS QUE CUMPLAN CON LOS PARAMETROS QUE SE LE ENVIA
	 * ELEMENTSPERBLOCK INDICA LA MAXIMA CANTIDAD DE EMPRESAS A RECUPERAR (PARA PAGINACION SOBRE TODO)
	 * PAGE ES LA PAGINA DE ESA PAGINACION **/
	@Override
	public ArrayList<Empresa> recuperar(int page,
			HashMap<String, Object> parametros, int elementsPerBlock) {

		ArrayList<Empresa> resultado = null;
		EntityManager em = EMF.getEMF().createEntityManager();
		String con = "select e from Empresa e";

		boolean hayWhere = false;
		
		if (parametros.containsKey("nombre")) {
			con += " where lower(e.nombre) like lower(:nombre)";
			hayWhere = true;
		}
		
		if (parametros.containsKey("rubro")) {
			if(!hayWhere){
				con += " where lower(e.rubro) like lower(:rubro)";
				hayWhere = true;
			} else con += " and lower(e.rubro) like lower(:rubro)";
		}
		
		if (parametros.containsKey("direccion")) {
			if(!hayWhere){
				con += " where lower(e.direccion) like lower(:direccion)";
				hayWhere = true;
			} else con += " and lower(e.direccion) like lower(:direccion)";
		}
		
		if (parametros.containsKey("descripcion")) {
			if(!hayWhere) con += " where lower(e.descripcion) like lower(:descripcion)";
			else con += " and lower(e.descripcion) like lower(:descripcion)";
		}

		/**ESTA PARTE ES PARA LA TABLA DESPLEGADA DE EMPRESAS, PODER ORDENAR POR EL CAMPO QUE SE DESEE 
		 * ORDEN TIENE EL ORDEN POR EL QUE SE DESEA ORDENAR PRIMARIAMENTE
		 * TODOS LOS ORDENES TIENE EL RESTO DE LOS ORDENES, LOS SECUNDARIOS**/
		try {
			String[] ordenes = ((String) parametros.get("todosLosOrdenes")).split(",");
			String orden = (String) parametros.get("orden");
	
			/**
			 * No se ha ordenado por nada aun, dejo el orden por defecto que es calidad
			 **/
			if ((orden == null) || (orden.isEmpty()))
				con += " order by calidad";
			else {
				/** Hay un orden principal **/
				String[] datos = orden.split("-");
				String atributo = datos[0];
				String condicion = datos[1].toUpperCase(); /** ESTO ES ASC O DESC **/
				con += " order by " + atributo + " " + condicion;
				String atributoPrincipal = atributo;
				for (int i = 0; i < ordenes.length; i++) {
					datos = ordenes[i].split("-");
					atributo = datos[0];
					condicion = datos[1].toUpperCase();
					if (!(atributo.equals(atributoPrincipal)))
						con += ", " + atributo + " " + condicion;
				}
			}
		} catch (NullPointerException e1) {
			con += " order by calidad";
		}
	
		Query consulta = em.createQuery(con);
		Iterator it = parametros.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Object valor = pair.getValue();
			String parametro = pair.getKey().toString();
			consulta.setParameter(parametro, "%" + valor + "%");
		}
		
		int pagi = page;
		consulta.setFirstResult(elementsPerBlock * (page - 1 + 1)); // offset
		consulta.setMaxResults(elementsPerBlock); // limit
		resultado = (ArrayList<Empresa>) consulta.getResultList();
		em.close();
		return resultado;
	}

	@Override
	public int cantidad(HashMap<String, Object> parametros) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

}
