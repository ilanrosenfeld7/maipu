package clases;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Empresa {
	@Id
	@GeneratedValue
	@Column(name="ID_EMPRESA")
	private int id;
	
	private String nombre, rubro, direccion, telefono, descripcion;
	private ArrayList<String> etiquetas;
	
	@ManyToOne
	@JoinColumn(name="USUARIO")
	private Usuario usuario;
	
	/**LA CALIDAD DEBERÍA SER UN BALANCE ENTRE CALIDAD DEL LOCAL MÁS PLATA QUE INVIERTE PARA FIGURAR
	 * A MAYOR CALIDAD, MAYOR LA CHANCE DE QUE FIGURE EN LA PRIMERA BÚSQUEDA **/
	private int calidad;
	
	public Empresa(){
		
	}
	
	public Empresa(String nombre, String rubro, String direccion,
			String telefono, String descripcion, ArrayList<String> etiquetas, int calidad, Usuario usuario) {
		super();
		this.nombre = nombre;
		this.rubro = rubro;
		this.direccion = direccion;
		this.telefono = telefono;
		this.descripcion = descripcion;
		this.etiquetas = etiquetas;
		this.calidad=calidad;
		this.usuario=usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRubro() {
		return rubro;
	}
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ArrayList<String> getEtiquetas() {
		return etiquetas;
	}
	public void setEtiquetas(ArrayList<String> etiquetas) {
		this.etiquetas = etiquetas;
	}
	public int getCalidad() {
		return calidad;
	}
	public void setCalidad(int calidad) {
		this.calidad = calidad;
	}
	
	
}
