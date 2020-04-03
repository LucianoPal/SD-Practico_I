package Punto6;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
		try {
			Registry clientRMI = LocateRegistry.getRegistry("localhost",9000);
			System.out.println("Cliente conectado");
			
			IntRemote ir = (IntRemote) clientRMI.lookup("vectores");
			
			ArrayList<Integer> v1 = new ArrayList<Integer>();
			ArrayList<Integer> v2 = new ArrayList<Integer>();
			
			for (int i = 0; i < 5; i++) {
				Random random = new Random();
				Integer n1= random.nextInt(10);
				Integer n2= random.nextInt(10);		
				v1.add(n1); 
				v2.add(n2);
			}
			
			System.out.println("Vectores:");
			System.out.println("V1: " +v1.toString());
			System.out.println("V2: " +v2.toString() + "\n");
			
			ArrayList<Integer> resultado;
			
			boolean salir = false;
			while (!salir) {
				
				System.out.println("MENU");
				System.out.println("1 - Suma");
				System.out.println("2 - Resta");
				System.out.println("3 - Obtener nuevos vectores");
				System.out.println("0 - Salir");
				System.out.println("Ingrese una opcion");
				Scanner scanner = new Scanner(System.in);
				char op = scanner.next().charAt(0);
				Integer opcion =  (int) op;
				long inicio;
				long fin;
				long tiempo;
				
				switch(opcion) {
					case 49:
						inicio = System.currentTimeMillis();
						resultado = ir.sumaVectores(v1, v2);
						fin = System.currentTimeMillis();
						tiempo = fin - inicio;
						System.err.println("tiempo de respuesta: " + tiempo);
						
						System.out.println("Vectores al volver del servidor");
						System.out.println("V1: " + v1.toString());
						System.out.println("V2: " + v2.toString());
						System.out.println("Vector suma: " + resultado.toString() + "\n");
					break;
					case 50:
						inicio = System.currentTimeMillis();
						resultado = ir.restaVectores(v1, v2);
						fin = System.currentTimeMillis();
						tiempo = fin - inicio;
						System.err.println("tiempo de respuesta: " + tiempo);
						
						System.out.println("Vectores al volver del servidor");
						System.out.println("V1: " + v1.toString());
						System.out.println("V2: " + v2.toString());
						System.out.println("Vector resta: " + resultado.toString() + "\n");
						
					break;
					case 51:
						v1.clear();
						v2.clear();
						
						for (int i = 0; i < 5; i++) {
							Random random = new Random();
							Integer n1= random.nextInt(10);
							Integer n2= random.nextInt(10);		
							v1.add(n1); 
							v2.add(n2);
						}
						
						System.out.println("\nVectores:");
						System.out.println("V1: " +v1.toString());
						System.out.println("V2: " +v2.toString() + "\n");
					break;
					case 48:
						salir=true;
					break;
					default:
						System.out.println("Ingrese una opcion válida\n");
					break;
				}
			}
			System.out.println("Programa Finalizado");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
