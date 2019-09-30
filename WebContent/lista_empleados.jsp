<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Empleados</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="https://unpkg.com/notie/dist/notie.min.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto+Mono" rel="stylesheet">
	 <style>
    /* override styles here */
    .notie-container {
      box-shadow: none;
    }
 
	div {
		text-align: center;
	}
	
	table {
		margin: 0 auto;
	}
	
	table td {
		text-align: center;
	}
	</style>
	<style>
      body {
        margin: 0;
        padding: 0;
        font-family: 'Roboto Mono', monospace;
        background-color: #E8E8E8;
      }
      .div-ext {
        height: 100vh;
        width: 100%;
        display: table;
        text-align: center;
      }
      .div-int {
        display: table-cell;
        vertical-align: middle;
      }
      button {
        cursor: pointer;
        font-size: 18px;
        border: 2px solid #000;
        border-radius: 2px;
        background-color: transparent;
        outline: 0;
        margin: 2px;
        margin-top: 4px;
        padding: 5px;
      }
      a {
        color: #585858;
      }
    </style>

</head>
<body>
<script>
notie.alert({ text: 'Info!' })
</script>
	<div class="card">
		<h5 class="card-header">Crud Empleados</h5>
		<div class="card-body">
			<h5 class="card-title">Lista de Empleados</h5>
			<p class="card-text">Administración de Empleados</p>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#ModalRegForm">Registrar</button>
		</div>
		<p align="left">
			<b>Crud Por Luis Serrano</b>
		</p>
	</div>
	<div>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Nro Carné</th>
					<th scope="col">Primer Nombre</th>
					<th scope="col">Primer Apellido</th>
					<th scope="col">Género</th>
					<th scope="col">Fecha Nac</th>
					<th scope="col">Fecha Registro</th>
					<th scope="col">Opciones</th>
				</tr>
			</thead>
			<tbody id="ttbody">

				<%  
   		/*Edad emp_edad = new Edad(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");*/
		
		
		%>
				<c:forEach var="tempEmp" items="${LISTAEMPLEADOS}">
				
				<!-- Link Para cada Empleado con su campo clave -->
				
				<c:url var = "linkTemp" value = "ControladorEmpleados">				
					<c:param name = "orden" value="cargar"></c:param>
					<c:param name = "id_card1" value = "${tempEmp.id_card}"></c:param>												
				</c:url>
				<!-- Link para eliminar mis registros -->
				<c:url var = "linkElim" value = "ControladorEmpleados">				
					<c:param name = "orden" value="eliminar"></c:param>
					<c:param name = "id_card1" value = "${tempEmp.id_card}"></c:param>												
				</c:url>
				
					<tr class="menu">
						<td scope="row">${tempEmp.id_card}</td>
						<td>${tempEmp.first_name}</td>
						<td>${tempEmp.last_name}</td>
						<td>${tempEmp.gender}</td>
						<td id="edadd">${tempEmp.birth_date}</td>
						<td>${tempEmp.hire_date}</td>
						<td><div class="col text-center">							
								<a href="${linkTemp}" class="btn btn-info">Editar</a>
								&nbsp;
								<a type = "hidden" ></a>
								<a class="btn btn-danger" id="hola" onclick="confirm('${linkElim}')">Eliminar</a>
							</div></td>
					</tr>

				</c:forEach>
				<!-- ModalINICIO -->
				<!-- Modal HTML Markup -->
				<div id="ModalRegForm" class="modal fade">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title text-center">Registro</h1>
							</div>
							<div class="modal-body">

								<h1>Empleado Nuevo</h1>
								<form role="form" method="GET" action="ControladorEmpleados">
									<input type="hidden" name="orden" value="registrar"> <input
										type="hidden" name="_token" id="cod_empleados" value="">
									<div class="form-group">
										<label>Nombre:</label>
										<div>
											<input type="text" class="form-control input-lg"
												name="nombre" value="" placeholder="Ingrese Nombre" required>
										</div>
									</div>
									<div class="form-group">
										<label>Apellido:</label>
										<div>
											<input type="text" class="form-control input-lg"
												name="apellido" value="" placeholder="Ingrese Apellido"
												required>
										</div>
									</div>
									<div class="form-group">

										<div>
											<label>Nro Carné</label> <input type="text"
												class="form-control input-lg" name="id_card" value=""
												placeholder="Ingrese Nro Carné" maxlength="7" required>
										</div>
									</div>
									<div class="form-group">
										<label>Sexo:</label>
										<div>
											<select class="form-control" class="form-control input-lg"
												name="gender" id="exampleFormControlSelect1" required>
												<option value="M">M</option>
												<option value="F">F</option>
												<option value="O">Otro</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label>Fecha Nac:</label>
										<div>
											<input type="date" class="form-control input-lg" name="fecha"
												required>
										</div>
									</div>
									<div class="form-group">
										<div>
											<button type="submit" onclick=success() class="btn btn-success">
												Registrar</button>
											<a href="#" data-dismiss="modal" class="btn btn-warning">Close</a>
										</div>
									</div>
								</form>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->

				<!-- Modal FIN -->
			</tbody>
		</table>
	</div>

	<script src="https://unpkg.com/notie"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="https://unpkg.com/notie"></script>	
	<script>
	
	//Script para el boton cerrar modal
	$('#openBtn').click(function(){
		$('#ModalRegForm').modal({show:true})
	});

	$('#ModalRegForm').on('hide.bs.modal', function(e){
	  if( $('#block').is(':checked') ) {
	     e.preventDefault();
	     e.stopImmediatePropagation();
	     return false; 
	   }
	});
	
	</script>
	<script>
	
      notie.setOptions({
        alertTime: 2
      })

      function success () {
        notie.alert({ type: 1, text: 'Success!', time: 2 })
      }

      function warning () {
        notie.alert({ type: 2, text: 'Warning<br><b>with</b><br><i>HTML</i><br><u>included.</u>', time: 2 })
      }

      function error () {
        notie.alert({ type: 3, text: 'Error.', time: 2 })
      }

      function info () {
        notie.alert({ type: 4, text: 'Information.', time: 2 })
      }

      function force () {
        notie.force({
          type: 3,
          text: 'You cannot do that, sending you back.',
          buttonText: 'OK',
          callback: function () {
            notie.alert({ type: 3, text: 'Maybe when you\'re older...' })
          }
        })
      }

      function confirm (e) {
    	 let url_idcard = e;
  
        notie.confirm({
          text: '¿Estas Seguro que deseas eliminar este registro?</b>',
          cancelCallback: function () {
            notie.alert({ type: 1, text: 'Registro a salvo', time: 2 })
          },
          submitCallback: function () {
 
            notie.alert({ type: 3, text: 'Eliminando registro...', time: 2 })
            
            setTimeout(function() {
            	window.location.href = url_idcard ;         	
           }, 2000);
          }
        })
      }

      function input() {
        notie.input({
          text: 'Please enter your email address:',
          cancelCallback: function (value) {
            notie.alert({ type: 3, text: 'You cancelled with this value: ' + value, time: 2 })
          },
          submitCallback: function (value) {
              notie.alert({ type: 1, text: 'You entered: ' + value, time: 2 })
          },
          type: 'text',
          placeholder: 'name@example.com',
          spellcheck: 'false'
        })
      }

      function select() {
        notie.select({
          text: 'Demo item #1, owner is Jane Smith',
          cancelText: 'Close',
          cancelCallback: function () {
            notie.alert({ type: 5, text: 'Cancel!' })
          },
          choices: [
            {
              text: 'Share',
              handler: function () {
                notie.alert({ type: 1, text: 'Share item!' })
              }
            },
            {
              text: 'Open',
              handler: function () {
                notie.alert({ type: 1, text: 'Open item!' })
              }
            },
            {
              type: 2,
              text: 'Edit',
              handler: function () {
                notie.alert({ type: 2, text: 'Edit item!' })
              }
            },
            {
              type: 3,
              text: 'Delete',
              handler: function () {
                notie.alert({ type: 3, text: 'Delete item!' })
              }
            }
          ]
        })
      }

      function date() {
        notie.date({
          value: new Date(2015, 8, 27),
          cancelCallback: function (date) {
            notie.alert({ type: 3, text: 'You cancelled: ' + date.toISOString() })
          },
          submitCallback: function (date) {
            notie.alert({ type: 1, text: 'You selected: ' + date.toISOString() })
          }
        })
      }
    </script>
    <script>
    
 
    </script>
</body>
</html>