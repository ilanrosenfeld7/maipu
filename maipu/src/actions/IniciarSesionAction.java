package actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import daos.UsuarioDao;

public class IniciarSesionAction extends ActionSupport{
	private String usuario, contrasena;
	
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("usuario", usuario);
		return "success";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
