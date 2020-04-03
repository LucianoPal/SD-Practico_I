package Punto7;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Registry cliente = LocateRegistry.getRegistry("localhost", 9000);
			System.out.println("Cliente conectado");
			
			IntRemote ir = (IntRemote) cliente.lookup("ejecutar-tarea");
			
			boolean salir = false;
			while (!salir) {
				System.out.println("MENU");
				System.out.println("1. Pi");
				System.out.println("2. Random");
				System.out.println("3. Primo");
				System.out.println("0. Salir");
				System.out.println("Ingrese una opcion");
				Scanner scanner = new Scanner(System.in);
				char op = scanner.next().charAt(0);
				Integer opcion =  (int) op;
				String ingreso;
				boolean string = false;
				long inicio;
				long fin;
				long tiempo;
				
				switch(opcion) {
					case 49:
						System.out.println("Ingrese un número entre 0 y 15 para determinar la precisión del número pi");
						ingreso = new Scanner(System.in).nextLine();
						int precision = -1;
						
						do {
							string = isString(ingreso);
							
							if (string) {
								System.err.println("Error: Ingrese un número entre 0 y 15");
								ingreso = new Scanner(System.in).nextLine();
							}
							else {
								precision = Integer.parseInt(ingreso);
							}
							string = false;
						} while (precision < 0 || precision >15);
						
						inicio = System.currentTimeMillis();
						Pi pi = new Pi(precision);
						pi = (Pi) ir.getResultado(pi);
						fin = System.currentTimeMillis();
						tiempo = fin - inicio;
						System.err.println("tiempo de respuesta: " + tiempo);
						
						System.out.println("Value: " + String.valueOf(pi.getValue()) + "\n\n");
					break;
					case 50:
						System.out.println("Ingrese un número para determinar la rango del número aleatorio");
                        ingreso = new Scanner(System.in).nextLine();
                        int rango = 0;
						
                        do {
							string = false;
							string = isString(ingreso);
							
                        	if (string) {
								System.err.println("Error: Ingrese un número");
								ingreso = new Scanner(System.in).nextLine();
							}
							else {
								rango = Integer.parseInt(ingreso);
		                        rango = Math.abs(rango);
							}
                        } while (string);
                        
                        inicio = System.currentTimeMillis();
                        Aleatorio aleatorio = new Aleatorio(rango);
                        aleatorio = (Aleatorio) ir.getResultado(aleatorio);
						fin = System.currentTimeMillis();
						tiempo = fin - inicio;
						System.err.println("tiempo de respuesta: " + tiempo);
						
                        System.out.println("Value: " + String.valueOf(aleatorio.getValue()) + "\n\n");
                    break;
					case 51:
						System.out.println("Ingrese un número para determinar si es primo");
						ingreso = new Scanner(System.in).nextLine();
						int value = 0;
						
						do {
							string = false;
							string = isString(ingreso);
							
                        	if (string) {
								System.err.println("Error: Ingrese un número");
								ingreso = new Scanner(System.in).nextLine();
							}
							else {
								value = Integer.parseInt(ingreso);
		                        value = Math.abs(value);
							}
                        } while (string);
                        
                        inicio = System.currentTimeMillis();
                        Primo primo = new Primo(value);
						primo = (Primo) ir.getResultado(primo);
						fin = System.currentTimeMillis();
						tiempo = fin - inicio;
						System.err.println("tiempo de respuesta: " + tiempo);
						
                        if (primo.isPrimo()) {
							System.out.println("El númnero ingresado es primo\n\n");
						}
						else {
							System.out.println("El númnero ingresado no es primo\n\n");
						}
					break;
					case 48:
						salir = true;
					break;
					default:
						System.out.println("Debe ingresar una opción válida\n");
					break;
				}
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean isString(String ingreso) {
		boolean string = false;
		int letra;
		int i = 0;
		if ((((int) ingreso.charAt(i)) < 48 || ((int) ingreso.charAt(i)) > 57) && ((int) ingreso.charAt(i)) != 45) {
			string = true;
		}
		i++;
		while (i < ingreso.length() && !string) {
			letra = (int) ingreso.charAt(i);
			
			if (letra < 48 || letra > 57) {
				string = true;
			}
			i++;
		}
		return string;
	}
	
}