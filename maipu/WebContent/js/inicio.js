 $(document).ready(function(){  		
	
	 
	$("#invisible").hide();
	$("#resultado").hide();
	
	$("#crearmeCuenta").click(function (e) {
		e.preventDefault();
		restaurarValores();
		if($("#invisible").is(":visible")){
			$("#invisible").hide();
			$("#textoBoton").text("Ingresar");
			$("#textoCambiarOpcion").text("A\u00fan no est\u00e1s registrado?");
		}
		else{
			$("#invisible").show();
			$("#invisible").css("visibility",'visible');
			$("#textoBoton").text("Registrarme");
			$("#textoCambiarOpcion").text("Ingresar al sistema");
		}

	});
	
	function restaurarValores(){
		$("#resultLogueo").text("");
		$("#resultado").text("");
		$("#resultUser").text("");
		$("#resultMail").text("");
	}
	
	function isEmail(email) {
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
	}
	
	function chequearCampos(usuario,contrasena,mail,checkMail){
		if($.trim(usuario) === "" || usuario.length === 0){
			$("#usuario").focus();
			$("#resultUser").text("Debe ingresar un nombre de usuario");
			return false;
		}else{
			if($.trim(contrasena) === "" || contrasena.length === 0){
				$("#contrasena").focus();
				$("#resultLogueo").text("Debe ingresar una contrase\u00f1a");
				return false;
			}
			else{
				if(checkMail){
					if(!(isEmail(mail))){
						$("#resultMail").focus();
						$("#resultMail").text("Debe ingresar un mail v\u00e1lido");
						return false;
					}		
				}
				return true;
			}
		}
	}
	 
	$("#botonConfirmar").click(function (e) {
		e.preventDefault();
		restaurarValores();
		var usuario = $("#usuario").val();
		var contrasena = $("#contrasena").val();
		if($("#invisible").is(":visible")){
			var mail = $("#mail").val();
			$("#resultado").show();
			if(!(chequearCampos(usuario,contrasena,mail,true)))
				return false;
			$("#resultado").css("visibility",'visible');
			$("#resultado").html(" <p class='help-block text-success'><img src='img/loading.gif'><font color=gray>Registrando...</font></p>");
			$.ajax({  
				  type: "POST",  
				  url: "registrarse",  
				  data: {
					  usuario: usuario,
					  contrasena: contrasena,
					  mail: mail,
				  },
				  success: function(response){
					  if(response.split("separador")[0].indexOf("exito")>-1)
						  $("#resultado").text("Usuario registrado con \u00e9xito!");
					  else{
						  $("#resultado").text("");
						  if(response.split("separador")[0].indexOf("usuario")>-1)
							  $("#resultUser").text("Ya existe un usuario con ese nombre");
						  else $("#resultMail").text("Ya existe un usuario con ese e-mail");
					  }
					  
	      	      },
			  });   
		}else{
			if(!(chequearCampos(usuario,contrasena,"a",false)))
				return false;
			else{
					$("#resultLogueo").html(" <img src='img/loading.gif'><font color=gray>Ingresando...</font>");
					$.ajax({  
						  type: "POST",  
						  url: "checkUsuario",  
						  data: {
							  usuario: usuario,
							  contrasena: contrasena,
						  },
						  success: function(response){
							  if(response.split("separador")[0].indexOf("error")>-1){
								  $("#resultLogueo").text("Usuario o contrase\u00f1a inv\u00e1lidos");
							  }
							  else {
								  $("#formRegistrarse").submit();
							  }
			      	      },
					  });  
			}
		}
		
	});
 });