package crud_empleados.lserrano.com;

import java.sql.*;
import java.util.Date;
import javax.jws.WebService;
import javax.sql.DataSource;

@WebService(endpointInterface = "crud_empleados.lserrano.com.Read")
public class ReadImp implements Read {
	private DataSource datosPrincipales;
	
	public ReadImp(DataSource datosPrincipales) {
		
		this.datosPrincipales=datosPrincipales;
		
	}

	@Override
	public Empleados detalleEmpleado(String idCard) throws Exception {
		// TODO Auto-generated method stub
			
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

}
