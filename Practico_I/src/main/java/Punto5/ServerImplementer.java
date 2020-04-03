package Punto5;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

public class ServerImplementer implements IntRemote{

	public String getClima() throws RemoteException {
		ArrayList<String> climas = new ArrayList<String>();
		climas.add("Soleado");
		climas.add("Despejado");
		climas.add("Nublado");
		climas.add("Lluvioso");
		climas.add("Tormentoso");
		climas.add("Ventoso");
		
		Random r = new Random();
		int random = r.nextInt(6);
		int temp = r.nextInt(46)-10;
		String clima = climas.get(random) + " " + temp + " °C";
		return clima;
	}

}
