package daos;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import clases.EMF;
import clases.Usuario;


public class UsuarioDao implements GenericDao<Usuario>{

	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		EntityManager e = EMF.getEMF().createEntityManager();
		EntityTransaction etx = e.getTransaction();
		etx.begin();
		e.persist(usuario);
		e.flush();
		etx.commit();
		e.close();
	}

	@Override
	public ArrayList<Usuario> recuperarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Usuario usuario) {
		EntityManager e = EMF.getEMF().createEntityManager();
		e.getTransaction().begin();

		e.merge(usuario);
		e.getTransaction().commit();
	}
	

	public boolean existe(String usuario, String contrasena) {
		EntityManager e = EMF.getEMF().createEntityManager();
		Query consulta = e
				.createQuery("select count(*) from Usuario u where u.usuario=? and u.contrasena=?");
		consulta.setParameter(1, usuario);
		consulta.setParameter(2, contrasena);
		boolean existe = ((Long) (consulta.getSingleResult())) > 0;
		e.close();
		return existe;
	}
	
	public boolean existeUsuario(String usuario) {
		EntityManager e = EMF.getEMF().createEntityManager();
		Query consulta = e
				.createQuery("select count(*) from Usuario u where u.usuario=?");
		consulta.setParameter(1, usuario);
		boolean existe = ((Long) (consulta.getSingleResult())) > 0;
		e.close();
		return existe;
	}
	
	public boolean existeMail(String mail) {
		EntityManager e = EMF.getEMF().createEntityManager();
		Query consulta = e
				.createQuery("select count(*) from Usuario u where u.mail=?");
		consulta.setParameter(1, mail);
		boolean existe = ((Long) (consulta.getSingleResult())) > 0;
		e.close();
		return existe;
	}

	@Override
	public Usuario recuperar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Usuario> recuperar(int page,
			HashMap<String, Object> parametros, int elementsPerBlock) {
		// TODO Auto-generated method stub
		return null;
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
