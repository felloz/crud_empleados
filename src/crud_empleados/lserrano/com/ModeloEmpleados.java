package crud_empleados.lserrano.com;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.sql.*;


public class ModeloEmpleados {
	
	private DataSource datosPrincipales;
	
	public ModeloEmpleados(DataSource datosPrincipales) {
		
		this.datosPrincipales=datosPrincipales;
		
	}
	
	public List<Empleados> getEmpleados() throws Exception{
		
		List<Empleados> empleados = new ArrayList<>();
		
		Connection miConexion = null;
		
		Statement miStatement = null;
		
		ResultSet miResultset = null;
		
		/**
		 * Establezco la conexion
		 */
		try {
		miConexion = datosPrincipales.getConnection();
		
		//Creo mi query
		String query = "SELECT * FROM empleados WHERE status = '1'";

		miStatement = miConexion.createStatement();
		
		//Ejecuto la query
		miResultset = miStatement.executeQuery(query);
		while(miResultset.next()) {
			
			String idCard    = miResultset.getString("id_card");
			String firstName = miResultset.getString("first_name");
			String lastName  = miResultset.getString("last_name");
			String gender  	  = miResultset.getString("gender");
			Date hireDate    = miResultset.getDate("hire_date");
			Date birthDate  = miResultset.getDate("birth_date");
						
			//Aplico formato a la fecha para poder convertirla a String
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
			//Declaro la fecha como tipo String
			String finalDate = formatter.format(birthDate);
			//Establezco un formato para la fecha a recibir(si, por segunda vez, así nos aseguramos que siempre reciba ese formato)
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaNac2 = java.time.LocalDate.parse(finalDate, fmt);
			LocalDate ahora = java.time.LocalDate.now();
			 
			Period periodo = Period.between(fechaNac2, ahora);
			/*
			 * Para obtener Años - Mes - día
			 * Años: getYears()
			 * Mes:  getMonths()
			 * Días: getDays()
			 */
			
			//En caso de que el empleado registrado sea un bebe menor de un año *_* <3_<3 O_O
			//Queda pendiente validar a nivel de front que no permita registrar personas menores de 18 años
			int edadInt = periodo.getYears();
			String edadString = Integer.toString(edadInt);
			if(edadInt < 1) {
				edadString = periodo.getMonths() + "M";
			}
			
			
			Empleados empleadoi = new Empleados(idCard, firstName, lastName, gender, hireDate, birthDate, edadString);
			
			empleados.add(empleadoi);
			
		}
		
		return empleados;
		}finally {
			if(miStatement != null) {
				miStatement.close();
				miConexion.close();
			}
		}
		
		
	}


	public void registrarNuevoEmpleado(Empleados nuevoEmpleado) throws Exception {
		// Registro un nuevo empleado
		//Creamos la conexión
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		try {
			miConexion = datosPrincipales.getConnection();
			//Creamos Query de Insert
			String query = "INSERT INTO empleados (id_card, first_name, last_name, gender, hire_date, birth_date) VALUES"
					+ "(?, ?, ?, ?, NOW(), ?)";
	
			miStatement = miConexion.prepareStatement(query);
			//Establecer parametros para los empleados
			miStatement.setString(1, nuevoEmpleado.getIdCard());
			miStatement.setString(2, nuevoEmpleado.getFirstName());
			miStatement.setString(3, nuevoEmpleado.getLastName());
			miStatement.setString(4, nuevoEmpleado.getGender());
			java.util.Date utilDate = nuevoEmpleado.getBirthDate();
			java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
			miStatement.setDate(5, fechaConvertida);
			//Ejecutar el Query
			miStatement.execute();
			
		}finally{
			if(miStatement != null) {
				miStatement.close();
				miConexion.close();
			}
		}
		
		
		
	}

	public Empleados getEmpleado(String idCard) throws Exception {
		// Obtengo el empleado a traves de su id
		//Declaracion de variables
		Empleados unEmpleado = null;
		
		Connection miConexion = null;
		
		PreparedStatement miStatement = null;
		
		ResultSet miResultset = null;
		
		String nroCarnet = idCard;
		
		try {
			//Crea la conexion con la base de datos		
			miConexion = datosPrincipales.getConnection();
			
			//Busca el empleado con el id_card a traves de una query			
			String query = "SELECT * FROM empleados WHERE id_card = ?";	
			
			//Prepara la query
			miStatement = miConexion.prepareStatement(query);
			
			//Establece parametros
			miStatement.setString(1, nroCarnet);		
			
			//Ejecuta la Query
			miResultset = miStatement.executeQuery();
			
			//Obtiene los datos
			if(miResultset.next()) {
				
				
				String idCard1    = miResultset.getString("id_card");
				String firstName = miResultset.getString("first_name");
				String lastName  = miResultset.getString("last_name");
				String gender  	  = miResultset.getString("gender");
				Date birthDate   = miResultset.getDate("birth_date");
				
	 			
				unEmpleado = new Empleados(idCard1, firstName, lastName, gender, birthDate);
				
			}else {
				throw new Exception("No se encontró el empleado con el Nro de Carné: " + nroCarnet);
			}
			
		} finally{
			// Cierro las conexiones
			if(miStatement != null) {
				miStatement.close();
				miConexion.close();
			}
		}
		
		
		
		return unEmpleado;
	}

	public void editarEmpleado(Empleados empleadoEditar) throws Exception {
		// Metodo para editar los empleados
		
		//Creamos la conexion
		
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		
		try {
			
			miConexion = datosPrincipales.getConnection();
			String query = "UPDATE empleados SET first_name = ?, last_name = ?, gender = ?, birth_date = ? WHERE id_card = ?";
			miStatement = miConexion.prepareStatement(query);
			
			miStatement.setString(1, empleadoEditar.getFirstName());
			miStatement.setString(2, empleadoEditar.getLastName());
			miStatement.setString(3, empleadoEditar.getGender());
			java.util.Date utilDate = empleadoEditar.getBirthDate();
			java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
			miStatement.setDate(4, fechaConvertida);
			miStatement.setString(5, empleadoEditar.getIdCard());
			miStatement.execute();
		}catch (Exception e){
				throw new ServletException(e);
		}finally{
			if(miStatement != null) {
				miStatement.close();
				miConexion.close();
			}		
		}
		
	}

	public void eliminarRegistro(String idCard) throws Exception {
		// Metodo para eliminar empleados, realmente no elimina, por seguridad solo cambia su estatus
		
		//Inicializamos las variables
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		
		try {
			miConexion = datosPrincipales.getConnection();
			String query = "UPDATE empleados SET status = ? WHERE id_card = ?";
			miStatement = miConexion.prepareStatement(query);
			
			miStatement.setString(1, "0");
			miStatement.setString(2, idCard);
			miStatement.execute();
		}finally{
			if(miStatement != null) {
				miStatement.close();
				miConexion.close();
			}
		}
		
	}

}
