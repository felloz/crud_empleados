<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar</title>
</head>
<body>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Editar</title>
    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">



    <link rel="icon" href="Favicon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    
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
			<h5 class="card-title">Editar de Empleados</h5>
			<p class="card-text">Administración de Empleados</p>
			
		</div>
		<p align="left">
			<b>Crud Por Luis Serrano</b>
		</p>
</div>

<main class="my-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">Editar</div>
                        <div class="card-body">
                            <form name="form1" onsubmit="return validform()" action="ControladorEmpleados" method="GET">
                            <input type = "hidden" name="orden" value="editarEmpleado">
                            <input type = "hidden" name="id_card" value="${CARNET_EMPLEADO.id_card}">
                            	<div class="form-group row">
                                    <label for="full_name" class="col-md-4 col-form-label text-md-right">Nro Carné</label>
                                    <div class="col-md-6">
                                        <input type="text" id="id_card" class="form-control" name="" value ="${CARNET_EMPLEADO.id_card}" disabled>
                                    </div>
                                </div>
                            	
                                <div class="form-group row">
                                    <label for="full_name" class="col-md-4 col-form-label text-md-right">Nombre</label>
                                    <div class="col-md-6">
                                        <input type="text" id="first_name" class="form-control" name="nombre" value = "${CARNET_EMPLEADO.first_name}" required>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="email_address" class="col-md-4 col-form-label text-md-right">Apellido</label>
                                    <div class="col-md-6">
                                        <input type="text" id="last_name" class="form-control" name="apellido" value = "${CARNET_EMPLEADO.last_name}" required>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="user_name" class="col-md-4 col-form-label text-md-right">Genero</label>
                                    <div class="col-md-6">
                                    <select class="form-control" class="form-control"
												name="genero" id="exampleFormControlSelect1" required>
												<option value="${CARNET_EMPLEADO.gender}" selected>${CARNET_EMPLEADO.gender}</option>
												<option value="M">M</option>
												<option value="F">F</option>
												<option value="O">Otro</option>
									</select>
                                       <!--   <input type="text" id="genero" class="form-control" name="genero" value="${CARNET_EMPLEADO.gender}" required>-->
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="phone_number" class="col-md-4 col-form-label text-md-right">Fecha de Nacimiento</label>
                                    <div class="col-md-6">
                                        <input type="date" id="date" class="form-control" name="fecha" value="${CARNET_EMPLEADO.birth_date}" required>
                                    </div>
                                </div>                              

                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary"> 
                                        Enviar
                                        </button> <a class="btn btn-warning" href="ControladorEmpleados">Volver</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
            </div>
        </div>
    </div>

</main>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
</body>
</html>