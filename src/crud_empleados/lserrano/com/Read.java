package crud_empleados.lserrano.com;

import java.util.List;

import javax.jws.WebService;

//Establecemos el webservice
@WebService
public interface Read {
	
	public Empleados detalleEmpleado(String idCard) throws Exception;

}
