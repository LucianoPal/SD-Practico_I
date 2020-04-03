package Punto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import org.json.JSONObject;

public class Client {
	
	public static void main(String[] args) {
		try {
			System.out.println("Ingrese el ID del cliente");
			Scanner scn = new Scanner(System.in);
			String clienteID = scn.nextLine();
			
			Socket servidor = new Socket("127.0.0.1", 9000);
			System.out.println("Cliente iniciado");
			
			boolean salir = true;
			PrintWriter output;
			BufferedReader input;
			String mensajeRta;
			while (salir) {
				System.out.println("Ingrese la operacion que desea realizar");
				System.out.println("1 - Enviar");
				System.out.println("2 - Leer");
				System.out.println("0 - Salir");
				char op = scn.next().charAt(0);
				int opcion = (int) op;
				switch (opcion) {
					case 49:
						System.out.println("Ingrese el ID del cliente que desea enviarle un mensaje");
						String id = new Scanner(System.in).nextLine();
						System.out.println("Ingrese el asunto del msj: ");
						String asunto = new Scanner(System.in).nextLine();
						System.out.println("Ingrese el cuerpo del msj: ");
						String cuerpo = new Scanner(System.in).nextLine();
						
						String mensaje = new JSONObject()
								.put("asunto",asunto)
								.put("cuerpo",cuerpo).toString();
						
						if (mensaje == null) {
							System.err.println("Debe ingresar un mensaje\n");
						}
						else {
							output = new PrintWriter(servidor.getOutputStream(), true);
							mensaje = "1|" + id + "|" + mensaje;
							output.println(mensaje);
							System.out.println("Mensaje enviado\n");
						}
					break;
					case 50:
						output = new PrintWriter(servidor.getOutputStream(), true);
						mensaje = "2|" + clienteID;
						
						long inicio = System.currentTimeMillis();
						output.println(mensaje);
						
						input = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
						mensajeRta = input.readLine();
						long fin = System.currentTimeMillis();
						long tiempo = fin - inicio;

				        System.err.println(tiempo + " ");
						
						String msg = mensajeRta.substring(0, mensajeRta.indexOf("|"));
						mensajeRta = mensajeRta.substring(mensajeRta.indexOf("|")+1, mensajeRta.length());
						int cantidad = 1;
						while (!msg.equals("No hay mensajes")) {
							JSONObject json = new JSONObject(msg);
							System.out.println("\n\tMensaje " + cantidad);
							System.out.println("Asunto:\n\t" + json.get("asunto"));
							System.out.println("Cuerpo:\n\t" + json.get("cuerpo"));
							cantidad++;
							msg = mensajeRta.substring(0, mensajeRta.indexOf("|"));
							mensajeRta = mensajeRta.substring(mensajeRta.indexOf("|")+1, mensajeRta.length());
						}
						if (cantidad == 1) {
							System.out.println(msg);
						}
						System.out.println("\n");
					break;
					case 48:
						output = new PrintWriter(servidor.getOutputStream(), true);
						mensaje = "0|";
						output.println(mensaje);
						servidor.close();
						System.out.println("Se cerró la conexion con el servidor");
						salir = false;
					break;
					default:
						System.out.println("Debe ingresar una opción válida\n");
					break;
				}
			}
			scn.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
