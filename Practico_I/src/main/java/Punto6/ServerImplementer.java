package Punto6;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServerImplementer implements IntRemote{

	public ArrayList<Integer> sumaVectores(ArrayList<Integer> v1, ArrayList<Integer> v2) throws RemoteException {
		ArrayList<Integer> v3 = new ArrayList<Integer>();
		
		for (int i = 0; i < v1.size(); i++) {
			v3.add(v1.get(i) + v2.get(i));
		}
		
		v1.clear();
		v1.add(0); //Error
		
		System.out.println("\nVectores en el servidor:");
		System.out.println("V1: " + v1.toString());
		System.out.println("V2: " + v2.toString());
		System.out.println("SUMA: " + v3.toString() + "\n");
		
		return v3;
	}

	public ArrayList<Integer> restaVectores(ArrayList<Integer> v1, ArrayList<Integer> v2) throws RemoteException {
		ArrayList<Integer> v3 = new ArrayList<Integer>();
		
		for (int i = 0; i < v1.size(); i++) {
			v3.add(v1.get(i) - v2.get(i));
		}
		
		v1.clear();
		v1.add(0); //Error
		
		System.out.println("\nVectores en el servidor:");
		System.out.println("V1: " + v1.toString());
		System.out.println("V2: " + v2.toString());
		System.out.println("");
		System.out.println("SUMA: " + v3.toString());
		
		return v3;
	}

}
