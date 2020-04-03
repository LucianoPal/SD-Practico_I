package Punto5;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String[] args) {
		try {
			Registry servidorRMI = LocateRegistry.createRegistry(9000);
			System.out.println("Servicio RMI Iniciado");
			
			ServerImplementer si = new ServerImplementer();
			System.out.println("Implementador Instanciado");
			
			IntRemote servicio = (IntRemote) UnicastRemoteObject.exportObject(si, 8000);
			System.out.println("Servicio de clima asociado a un puerto");
			
			servidorRMI.rebind("info-clima", servicio);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
