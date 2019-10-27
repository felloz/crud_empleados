package crud_empleados.lserrano.com;

import javax.xml.ws.Endpoint;

public class ReadPublish {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Endpoint.publish("http://localhost:1515/WS/Read", new ReadImp(null));

	}

}
