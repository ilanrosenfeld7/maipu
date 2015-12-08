package actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import clases.Usuario;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import daos.UsuarioDao;

public class RegistrarseAction extends ActionSupport{
	
	private UsuarioDao daoUsuario;
	private String usuario, contrasena, mail;
	private String mensaje;
	
	public String execute() {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		
		if(daoUsuario.existeUsuario(usuario))
			mensaje="existe usuario";
		else{
			if(daoUsuario.existeMail(mail))
				mensaje="existe mail";
			else{
				Usuario u = new Usuario(usuario, contrasena, mail);
				daoUsuario.guardar(u);
				mensaje="exito";
			}
		}
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

	public UsuarioDao getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(UsuarioDao daoUsuario) {
		this.daoUsuario = daoUsuario;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
}