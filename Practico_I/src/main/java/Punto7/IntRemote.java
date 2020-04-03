package Punto7;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntRemote extends Remote{
	public Object getResultado(Tarea t) throws RemoteException;
}