package clases;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	@Column(name="ID_USUARIO")
	private int id;
	
	private String usuario, contrasena, mail;
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval=true,mappedBy="usuario", fetch=FetchType.EAGER)
	private Collection<Empresa> empresas;

	public Usuario(){
		
	}
	
	public Usuario(String usuario, String contrasena, String mail) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.mail = mail;
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
	
	public void agregarEmpresa(Empresa empresa){
		this.empresas.add(empresa);
	}

	public Collection<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Collection<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	

	
}
