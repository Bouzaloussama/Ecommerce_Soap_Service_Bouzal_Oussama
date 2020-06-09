package ws;

import javax.xml.ws.Endpoint;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Endpoint.publish("http://localhost:8583/",new CManager());		
		System.out.println("server");

	}

}
