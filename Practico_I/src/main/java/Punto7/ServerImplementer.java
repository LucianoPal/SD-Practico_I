package Punto7;

import java.rmi.RemoteException;

public class ServerImplementer implements IntRemote{
	
	public Object getResultado(Tarea t) throws RemoteException {
		return t.ejecutar();
	}
}
