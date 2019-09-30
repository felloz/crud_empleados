package crud_empleados.lserrano.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletEmpleados
 */
@WebServlet("/ServletEmpleados")
public class ServletEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Definir o establecer el DataSource
	
		@Resource(name="jdbc/empleados")
		private DataSource lserranoPool;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEmpleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//Crear el objeto PrintWriter
		
		PrintWriter salida = response.getWriter();
		response.setContentType("text/plain");
		
		//Crear Conexion con DB
		
		Connection miConexion = null;
		
		Statement miStatement = null;
		
		ResultSet miResultset = null;
		
		try {
			miConexion   = lserranoPool.getConnection();
			
			String miSql = "SELECT * FROM employees LIMIT 100";
			
			miStatement  = miConexion.createStatement();
			
			miResultset  =  miStatement.executeQuery(miSql); 
			
			while(miResultset.next()) {
				int emp_no       = miResultset.getInt("emp_no");
				String first_name = miResultset.getString("first_name");
				String last_name  = miResultset.getString("last_name");
				Date hire_date    = miResultset.getDate("hire_date");
				salida.println("Numero de empleado: "       + emp_no);
				salida.println("Primer Nombre: " + first_name);
				salida.println("Apellido: " + last_name);
				salida.println("Fecha de contratación: " + hire_date);	
				salida.println("------------------");
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
