//AUTOCOMPLETE TABELA
/*$(document).ready(function() {
	
	// DO GET
	$.ajax({
		type : "GET",
		url : "getCidades",
		success: function(result){
			$.each(result, function(i, cidade){
				
				var customerRow = '<tr>' +
									'<td>' + cidade.id + '</td>' +
									'<td>' + cidade.nome.toUpperCase() + '</td>' +
								  '</tr>';
				
				$('#customerTable tbody').append(customerRow);
				
	        });
			
			$( "#customerTable tbody tr:odd" ).addClass("info");
			$( "#customerTable tbody tr:even" ).addClass("success");
		},
		error : function(e) {
			alert("ERROR: ", e);
			console.log("ERROR: ", e);
		}
	});
	
	// do Filter on View
	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    $("#customerTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
	    });
	});
});*/

//AUTOCOMPLETE INPUT
$(document).ready(function() {
	
	$("input").blur(function(){  
		$('#customerList').hide();
	});  

	 $("#inputFilter").focus(function(){  
		// DO GET
		$.ajax({
			type : "GET",
			url : "autocompletecidades",
			success: function(result){
				$.each(result, function(i, cidade){
					
					var customerItem = '<li class="list-group-item">' +
											cidade.nome.toUpperCase() + //' - ' + cidade.uf.toUpperCase() + //JSON.stringify(cidade) +
										"</li>";
					$('#customerList').show();	
					$('#customerList').append(customerItem);
		        });
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});  
    });
	
	// do Filter on View
	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    $("#customerList li").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
	    });
	});
});
