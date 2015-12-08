<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vista usuario</title>
</head>
<body>
	
	Aca iria tu vista nico
	Bienvenido <% 	out.print(session.getAttribute("usuario")); %>
	
	<form name="formCerrarSesion" id="formCerrarSesion" action="cerrarSesion">
		<button type="submit" id="botonSalir" >
                 <span><s:text name="cerrarSesion" /></span>
         </button>
	</form>
	
</body>
</html>