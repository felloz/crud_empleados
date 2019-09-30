package crud_empleados.lserrano.com;

import java.sql.*;
import java.util.*;
import java.util.Date;

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
		//Recorro todo el array generado por la consultt
		while(miResultset.next()) {
			
			int emp_no        = miResultset.getInt("id_emp");
			String id_card    = miResultset.getString("id_card");
			String first_name = miResultset.getString("first_name");
			String last_name  = miResultset.getString("last_name");
			String gender  	  = miResultset.getString("gender");
			Date hire_date    = miResultset.getDate("hire_date");
			Date birth_date   = miResultset.getDate("birth_date");
			
 			
			Empleados empleadoi = new Empleados(emp_no, id_card, first_name, last_name, gender, hire_date, birth_date);
			
			empleados.add(empleadoi);
			
		}
		
		return empleados;
		}finally {
			miStatement.close();
			miConexion.close();
		}
		
		
	}

	public void registrar_nuevo_empleado(Empleados nuevo_empleado) throws Exception {
		// TODO Auto-generated method stub
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
			miStatement.setString(1, nuevo_empleado.getId_card());
			miStatement.setString(2, nuevo_empleado.getFirst_name());
			miStatement.setString(3, nuevo_empleado.getLast_name());
			miStatement.setString(4, nuevo_empleado.getGender());
			java.util.Date utilDate = nuevo_empleado.getBirth_date();
			java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
			miStatement.setDate(5, fechaConvertida);
			//Ejecutar el Query
			miStatement.execute();
			
		}finally{
			miStatement.close();
			miConexion.close();
		}
		
		
		
	}

	public Empleados getEmpleado(String id_card) throws Exception {
		// TODO Auto-generated method stub
		//Declaracion de variables
		Empleados unEmpleado = null;
		
		Connection miConexion = null;
		
		PreparedStatement miStatement = null;
		
		ResultSet miResultset = null;
		
		String nro_carnet = id_card;
		
		try {
			//Crea la conexion con la base de datos		
			miConexion = datosPrincipales.getConnection();
			
			//Busca el empleado con el id_card a traves de una query			
			String query = "SELECT * FROM empleados WHERE id_card = ?";	
			
			//Prepara la query
			miStatement = miConexion.prepareStatement(query);
			
			//Establece parametros
			miStatement.setString(1, nro_carnet);		
			
			//Ejecuta la Query
			miResultset = miStatement.executeQuery();
			
			//Obtiene los datos
			if(miResultset.next()) {
				
				
				String id_card1    = miResultset.getString("id_card");
				String first_name = miResultset.getString("first_name");
				String last_name  = miResultset.getString("last_name");
				String gender  	  = miResultset.getString("gender");
				Date hire_date    = miResultset.getDate("hire_date");
				Date birth_date   = miResultset.getDate("birth_date");
				
	 			
				unEmpleado = new Empleados(id_card1, first_name, last_name, gender, hire_date, birth_date);
				
			}else {
				throw new Exception("No se encontró el empleado con el Nro de Carné: " + nro_carnet);
			}
			
		} finally{
			// TODO Auto-generated catch block
			miStatement.close();
			miConexion.close();
		}
		
		
		
		return unEmpleado;
	}

	public void editar_empleado(Empleados empleado_editar) throws Exception {
		// TODO Auto-generated method stub
		
		//Creamos la conexion
		
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		
		try {
			
			miConexion = datosPrincipales.getConnection();
			String query = "UPDATE empleados SET first_name = ?, last_name = ?, gender = ?, birth_date = ? WHERE id_card = ?";
			miStatement = miConexion.prepareStatement(query);
			
			miStatement.setString(1, empleado_editar.getFirst_name());
			miStatement.setString(2, empleado_editar.getLast_name());
			miStatement.setString(3, empleado_editar.getGender());
			java.util.Date utilDate = empleado_editar.getBirth_date();
			java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
			miStatement.setDate(4, fechaConvertida);
			miStatement.setString(5, empleado_editar.getId_card());
			miStatement.execute();
			
		}finally{
			miStatement.close();
			miConexion.close();			
		}
		
	}

	public void eliminar_registro(String id_card) throws Exception {
		// TODO Auto-generated method stub
		
		//Inicializamos las variables
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		
		try {
			miConexion = datosPrincipales.getConnection();
			String query = "UPDATE empleados SET status = ? WHERE id_card = ?";
			miStatement = miConexion.prepareStatement(query);
			
			miStatement.setString(1, "0");
			miStatement.setString(2, id_card);
			miStatement.execute();
		}finally{
			miStatement.close();
			miConexion.close();
		}
		
	}

}
