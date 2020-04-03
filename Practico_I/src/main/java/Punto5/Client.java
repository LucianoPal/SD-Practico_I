package Punto5;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	
	public static void main(String[] args) {
		try {
			Registry cliente = LocateRegistry.getRegistry("localhost", 9000);
			System.out.println("Cliente conectado");
			
			IntRemote ir = (IntRemote) cliente.lookup("info-clima");
			long inicio = System.currentTimeMillis();
			
			String clima = ir.getClima();
			long fin = System.currentTimeMillis();
			long tiempo = fin - inicio;
			System.err.println("tiempo de respuesta: " + tiempo + "\n");
			
			System.out.println("Clima en la region del servidor: " + clima);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
