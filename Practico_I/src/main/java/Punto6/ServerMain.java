package Punto6;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {

	public static void main(String[] args) {

		try {
			Registry serverRMI = LocateRegistry.createRegistry(9000);
			System.out.println("Servicio RMI iniciado");
			
			ServerImplementer si = new ServerImplementer();
			System.out.println("Implementador iniciado");
			
			IntRemote serviceVectores = (IntRemote) UnicastRemoteObject.exportObject(si, 8000);
			System.out.println("Servicio asociado a un puerto");
			
			serverRMI.rebind("vectores", serviceVectores);
			System.out.println("bind de servicio realizado");
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
