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
		// TODO Auto-generated method stub
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
					
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "editarEmpleado":
			try {
				editarEmpleados(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "eliminar":
			try {
				eliminarEmpleado(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:
			obtenerEmpleados(request, response);
				
		}		
		
	}
	
	


	private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//Obtengo el parametro
		String id_card = request.getParameter("id_card1");
		
		//"Eliminamos" el registro
		modeloEmpleados.eliminar_registro(id_card);
		
		//Refrescamos la pagina
		obtenerEmpleados(request, response);
	}




	private void editarEmpleados(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//Recibir los datos del formulario
		String id_card    = request.getParameter("id_card");
		String first_name = request.getParameter("nombre");
		String last_name  = request.getParameter("apellido");
		String gender     = request.getParameter("genero");
		/**
		 * Para poder capturar el input fecha debemos utilizar la Clase SimpleDateFormat
		 */
		SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy-MM-dd");
		Date birth_date = null;
		
		try {
			birth_date = formato_fecha.parse(request.getParameter("fecha"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		//Crear Objeto o instanciar Empleados
		Empleados empleado_editar = new Empleados(id_card, first_name, last_name, gender, birth_date, birth_date);
		
		//Actualizar la base de datos con la informacion recibida
		modeloEmpleados.editar_empleado(empleado_editar);
		//Regresar a la lista de empleados con la informacion actualizada
		obtenerEmpleados(request, response);
		
	}




	private void cargarEmpleados(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		// Leer el id_card de la url		
		String id_card = request.getParameter("id_card1");
		
		
		// Enviar los datos obtenidos de la url(id_card) al Modelo
		Empleados unEmpleado = modeloEmpleados.getEmpleado(id_card);
		
		// Colocar el atributo correspondiente al id_card
		request.setAttribute("CARNET_EMPLEADO", unEmpleado);
		
		// Enviar Empleado al Formulario de editar
		RequestDispatcher dispatcher = request.getRequestDispatcher("/editar.jsp");
		
		dispatcher.forward(request, response);
								
	}




	private void registrarEmpleados(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		//Recibimos la informacion del form
		String id_card    = request.getParameter("id_card");
		String first_name = request.getParameter("nombre");
		String last_name  = request.getParameter("apellido");
		String gender     = request.getParameter("gender");
		/**
		 * Para poder capturar el input fecha debemos utilizar la Clase SimpleDateFormat
		 */
		SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy-MM-dd");
		Date birth_date = null;
		
		try {
			birth_date = formato_fecha.parse(request.getParameter("fecha"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Creamos objeto o instanciamos Empleados sin id_emp
		Empleados nuevo_empleado = new Empleados(id_card, first_name, last_name, gender, birth_date, birth_date);
		
		//Enviar el objeto al modelo y registrarlo
		try {
			modeloEmpleados.registrar_nuevo_empleado(nuevo_empleado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		//Y refrescamos la pagina Empleados
		
		obtenerEmpleados(request, response);
	}


	private void obtenerEmpleados(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
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
