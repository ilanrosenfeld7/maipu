package tests;

import java.util.ArrayList;

import clases.Empresa;
import clases.Usuario;
import daos.EmpresaDao;
import daos.FactoryDao;
import daos.UsuarioDao;

public class TestUsuario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsuarioDao usuarioDao = FactoryDao.getUsuarioDao();
		Usuario prueba = new Usuario("prueba", "prueba", "prueba@gmail.com");
		usuarioDao.guardar(prueba);
		
		ArrayList<String> etiquetas = new ArrayList<>();
		etiquetas.add("ferreteria");
		etiquetas.add("herramientas");
		etiquetas.add("la plata");
		Empresa empresa1 = new Empresa("Ferreteria gonza", "ferreteria", "56 n°123", "444444", "ferreteria completa 7x24", etiquetas, 3, prueba);
		
		etiquetas = new ArrayList<>();
		etiquetas.add("restaurant");
		etiquetas.add("comida rapida");
		etiquetas.add("la plata");
		Empresa empresa2 = new Empresa("Mc Donals", "restaurant", "47 n°456", "33333", "el mejor lugar de comida rapida", etiquetas, 10, prueba);
		
		Empresa empresa3 = new Empresa("Mostaza", "restaurant", "49 n° 123", "22222", "no te comas el verso", etiquetas, 7, prueba);
		
		EmpresaDao empresaDao = FactoryDao.getEmpresaDao();
		empresaDao.guardar(empresa1);
		empresaDao.guardar(empresa2);
		empresaDao.guardar(empresa3);
	}

}
