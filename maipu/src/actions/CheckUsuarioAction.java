package actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import daos.UsuarioDao;

public class CheckUsuarioAction extends ActionSupport{
	private String usuario, contrasena, mensaje;
	private UsuarioDao daoUsuario;
	
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		
		if(daoUsuario.existe(usuario, contrasena)){
			mensaje="exito";
		}else mensaje="error";
		
		mensaje += "separador";
		try {
			out = response.getWriter();
			out.println(mensaje);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public UsuarioDao getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(UsuarioDao daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

}
