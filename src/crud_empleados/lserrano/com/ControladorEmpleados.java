package crud_empleados.lserrano.com;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorEmpleados
 */
@WebServlet("/ControladorEmpleados")
public class ControladorEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Llamo la clase en un atributo
	private ModeloEmpleados modeloEmpleados;
	
	@Resource(name="jdbc/empleados")
	private DataSource lserranoPool;
	
	
	
	//Declaramos el metodo init donde arrancará la aplicación
	@Override
	public void init() throws ServletException {
		// Instancio la clase Modelo Empleados en modeloEmpleados
		super.init();
		
		try {
			modeloEmpleados = new ModeloEmpleados(lserranoPool);
		}catch(Exception e){
			
			throw new ServletException(e);
			
		}
						
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Reacibiré las peticiones de los formularios a traves del metodo doGet
					
		//Recibir Formulario de registro
		String accion = request.getParameter("orden");
		//De no enviarse el registro mostrar tabla empleados
		if(accion==null) accion ="listar";
		
		//Redirigir las acciones
		/**
		 * Aunque ya no es recomendados por buenas practicas el uso del comando switch case
		 * Lo uso solo con fines demostrativos de manejo del conocimiento, por que a pesar de
		 * que no se debe usar está presente en muchos sistemas
		 */
				
		switch (accion) {
		case "listar":
			obtenerEmpleados(request, response);
			break;
		case "registrar":
			registrarEmpleados(request, response);
			break;
		case "cargar":
			try {
				cargarEmpleados(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "editarEmpleado":
			try {
				editarEmpleados(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "eliminar":
			try {
				eliminarEmpleado(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			break;
			
		default:
			obtenerEmpleados(request, response);
				
		}		
		
	}
	
	private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Metodo para recibir la peticion de eliminar
		
		//Obtengo el parametro
		String idCard = request.getParameter("id_card1");
		
		//"Eliminamos" el registro
		modeloEmpleados.eliminarRegistro(idCard);
		
		//Refrescamos la pagina
		obtenerEmpleados(request, response);
	}




	private void editarEmpleados(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Metodo para recibir los parametros y enviarlos al metodo de la tabla
		
		//Recibir los datos del formulario
		String idCard    = request.getParameter("id_card");
		String firstName = request.getParameter("nombre");
		String lastName  = request.getParameter("apellido");
		String gender    = request.getParameter("genero");
		/**
		 * Para poder capturar el input fecha debemos utilizar la Clase SimpleDateFormat
		 */
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = null;
		
		try {
			birthDate = formatoFecha.parse(request.getParameter("fecha"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		//Crear Objeto o instanciar Empleados
		Empleados empleadoEditar = new Empleados(idCard, firstName, lastName, gender, birthDate);
		
		//Actualizar la base de datos con la informacion recibida
		modeloEmpleados.editarEmpleado(empleadoEditar);
		//Regresar a la lista de empleados con la informacion actualizada
		obtenerEmpleados(request, response);
		
	}




	private void cargarEmpleados(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Leer el id_card de la url		
		String idCard = request.getParameter("id_card1");
		
		
		// Enviar los datos obtenidos de la url(id_card) al Modelo
		Empleados unEmpleado = modeloEmpleados.getEmpleado(idCard);
		
		// Colocar el atributo correspondiente al id_card
		request.setAttribute("CARNET_EMPLEADO", unEmpleado);
		
		// Enviar Empleado al Formulario de editar
		RequestDispatcher dispatcher = request.getRequestDispatcher("/editar.jsp");
		
		dispatcher.forward(request, response);
								
	}




	private void registrarEmpleados(HttpServletRequest request, HttpServletResponse response) {
		// Metodo para recibir el formulario de registro
		
		//Recibimos la informacion del form
		String idCard    = request.getParameter("id_card");
		String firstName = request.getParameter("nombre");
		String lastName  = request.getParameter("apellido");
		String gender     = request.getParameter("gender");
		/**
		 * Para poder capturar el input fecha debemos utilizar la Clase SimpleDateFormat
		 */
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = null;
		
		try {
			birthDate = formatoFecha.parse(request.getParameter("fecha"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Creamos objeto o instanciamos Empleados sin id_emp
		Empleados nuevoEmpleado = new Empleados(idCard, firstName, lastName, gender, birthDate);
		
		//Enviar el objeto al modelo y registrarlo
		try {
			modeloEmpleados.registrarNuevoEmpleado(nuevoEmpleado);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		//Y refrescamos la pagina Empleados
		
		obtenerEmpleados(request, response);
	}


	private void obtenerEmpleados(HttpServletRequest request, HttpServletResponse response) {
		//Obtengo la lista de empleados desde el modelo
		List<Empleados> empleados;
		
		try {
			
			empleados = modeloEmpleados.getEmpleados();
			
		//Agrego la lista al request
			request.setAttribute("LISTAEMPLEADOS", empleados);
			
		//Envio el contenido al jsp
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/lista_empleados.jsp");
			
			miDispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	


}
