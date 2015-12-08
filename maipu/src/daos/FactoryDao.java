package daos;

public class FactoryDao {
	public static UsuarioDao getUsuarioDao(){
		return new UsuarioDao();
	}
	public static EmpresaDao getEmpresaDao(){
		return new EmpresaDao();
	}
}
