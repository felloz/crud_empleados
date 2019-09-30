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

<style>
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

</head>
<body>
	<div class="card">
		<h5 class="card-header">Crud Empleados</h5>
		<div class="card-body">
			<h5 class="card-title">Lista de Empleados</h5>
			<p class="card-text">Administración de Empleados</p>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#ModalLoginForm">Registrar</button>
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

					<tr class="menu">
						<td scope="row">${tempEmp.id_card}</td>
						<td>${tempEmp.first_name}</td>
						<td>${tempEmp.last_name}</td>
						<td>${tempEmp.gender}</td>
						<td id="edadd">${tempEmp.birth_date}</td>
						<td>${tempEmp.hire_date}</td>
						<td><div class="col text-center">							
								<button type="button" id="${tempEmp.id_card}" class="btn btn-primary" data-toggle="modal"
				data-target="#ModalEditForm">Editar</button>
								<%= " " %>
								<input type="button" class="btn btn-danger" value="Eliminar">
							</div></td>
					</tr>

				</c:forEach>
				<!-- ModalINICIO REGISTRAR -->
				<!-- Modal HTML Markup -->
				<div id="ModalLoginForm" class="modal fade">
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
											<button type="submit" class="btn btn-success">
												Registrar</button>
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
				<!-- ModalINICIO EDITAR -->
				<!-- Modal HTML Markup -->
				<div id="ModalEditForm" class="modal fade">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title text-center">Editar</h1>
							</div>
							<div class="modal-body">

								<h1>Nombre Empleado</h1>
								<form role="form" method="GET" action="ControladorEmpleados">
									<input type="hidden" name="orden" value="editar">
									<input type="hidden" name="id_card" value="${CARNET_EMPLEADO.id_card}">
									<div class="form-group">
										<label>Nombre:</label>
										<div>
											<input type="text" class="form-control input-lg"
												name="nombre" value="${tempEmp.id_card}" placeholder="Ingrese Nombre" required>
										</div>
									</div>
									<div class="form-group">
										<label>Apellido:</label>
										<div>
											<input type="text" class="form-control input-lg"
												name="apellido" value="${CARNET_EMPLEADO.id_card}" placeholder="Ingrese Apellido"
												required>
										</div>
									</div>
									<div class="form-group">

										<div>
											<label>Nro Carné</label> <input type="text"
												class="form-control input-lg" name="id_card" value="${CARNET_EMPLEADO.id_card}"
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
											<input type="date" class="form-control input-lg" value="${CARNET_EMPLEADO.id_card}" name="fecha"
												required>
										</div>
									</div>
									<div class="form-group">
										<div>
											<button type="submit" class="btn btn-success">
												Registrar</button>
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
	<script>

	val elementList = $(".menu");
	for (var i = 1; i <= list.length; i++) {
	    elementList[i].attr("id", "edadd" + i);
	}
	let fecha = document.getElementById("edaddd").innerHTML;
	console.log(fecha);
	
	container = document.getElementById('ttbody');
	items = container.getElementsByTagName('tr');
	console.log(items.length);
	
	for(i = 0; i < items.length; i++){
		
	}

	/*for (var j = 0; j < items.length; j++) {
	    if (items[j].href === 'http://www.msn.com') {
	        anchor = items[j];
	        break;
	    }
	}*/

    var hoy = new Date();
    var cumpleanos = new Date(fecha);
    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }
    document.getElementById("edadd").innerHTML = edad;
    console.log(edad);
    //document.getElementById("edaddd").style.display = "none";

</script>
</body>
</html>