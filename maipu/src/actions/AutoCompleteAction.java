package actions;

import java.util.ArrayList;
import java.util.HashMap;

import clases.Empresa;

import com.opensymphony.xwork2.Action;

import daos.EmpresaDao;
import daos.FactoryDao;
import daos.UsuarioDao;

public class AutoCompleteAction implements Action {
    // Received via Ajax request
    private String term,criterio;
    // Returned as responce
    private ArrayList<String> list;
    private EmpresaDao daoEmpresa;

    public String execute() {
            try {
                    HashMap<String, Object> parametros = new HashMap<String, Object>();
                    parametros.put(criterio.toLowerCase(), term);
                    ArrayList<Empresa> empresasDeseadas = daoEmpresa.recuperar(0,parametros, 5);
                    list = new ArrayList<String>();
	                for(Empresa e: empresasDeseadas){
	                	list.add(e.getNombre()+" - "+e.getDireccion()+" - "+e.getDescripcion());
	                }
            } catch (Exception e) {
                    System.err.println(e.getMessage());
            }
            return SUCCESS;
    }
    
    public ArrayList<String> getList() {
    	return list;
    }

    public void setList(ArrayList<String> list) {
    	this.list = list;
    }

    public String getTerm() {
    	return term;
    }

    public void setTerm(String term) {
    	this.term = term;
    }

	public EmpresaDao getDaoEmpresa() {
		return daoEmpresa;
	}

	public void setDaoEmpresa(EmpresaDao daoEmpresa) {
		this.daoEmpresa = daoEmpresa;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

}
