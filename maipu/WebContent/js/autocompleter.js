var criterio = $("#criterio").val();

$(document).ready(function() {
	
		$("#criterio").change(function(e) {
			e.preventDefault();
			criterio = $(this).val();
			if($(this).val()=="Nombre")
				$("#search").attr("placeholder","Ingrese el nombre del comercio a buscar");
			else{
				if($(this).val()=="Descripcion")
					$("#search").attr("placeholder","Ingrese la descripcion del comercio a buscar");
				else $("#search").attr("placeholder","Ingrese la direccion del comercio a buscar");
			}
		});
		
		
        $(function() {
                $("#search").autocomplete({
                source : function(request, response) {
                        $.ajax({
                                url : "searchAction",
                                type : "POST",
                                data : {
                                        term : request.term.toLowerCase(),
                                        criterio:criterio
                                },
                                dataType : "json",
                                success : function(jsonResponse) {
                                        response(jsonResponse.list);
                                }
                        });
                        },
                  select: function (e, ui) {
                	  /** aca deberia llamarse al action que muestre la info de
                	   * la opcion elegida; para eso se accede con ui.item.value
                	   */
                   },
                });
        });
});