package Punto6;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IntRemote extends Remote{
	
	public ArrayList<Integer> sumaVectores (ArrayList<Integer> v1, ArrayList<Integer> v2) throws RemoteException;
	public ArrayList<Integer> restaVectores (ArrayList<Integer> v1, ArrayList<Integer> v2) throws RemoteException;
	
}
