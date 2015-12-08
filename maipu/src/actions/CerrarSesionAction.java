package actions;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CerrarSesionAction extends ActionSupport{
	public String execute() {
		Map<String, Object> session= ActionContext.getContext().getSession();
	    try{
	        session.remove("usuario");
	        SecurityContextHolder.clearContext();
	        return "success";
	    }catch(Exception ex){
	        return "input";
	    }
	}
}
