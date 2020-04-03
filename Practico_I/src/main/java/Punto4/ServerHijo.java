package Punto4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerHijo implements Runnable{

	private Socket cliente;
	private ArrayList<String> colaDeMensajes;
	
	public ServerHijo(Socket cliente, ArrayList<String> colaDeMensajes) {
		this.cliente = cliente;
		this.colaDeMensajes = colaDeMensajes;
	}

	public void run() {
		BufferedReader input;
		try {
			input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter output = new PrintWriter(cliente.getOutputStream(), true);
			
			while (!cliente.isClosed()) {
				String mensaje = input.readLine();
				int opcion = Integer.parseInt(mensaje.substring(0,1));
				String id;
				String mensajeRta;
				switch (opcion) {
					case 1:
						System.out.println("El servidor envio un mensaje");
						mensaje = mensaje.substring(mensaje.indexOf("|")+1, mensaje.length());
						colaDeMensajes.add(mensaje);
					break;
					case 2:
						System.out.println("El servidor recibio una consulta");
						id = mensaje.substring(mensaje.indexOf("|")+1, mensaje.length());
						mensajeRta = new String();
						if (!colaDeMensajes.isEmpty()) {
							for (int i = 0; i < colaDeMensajes.size(); i++) {
								String element = colaDeMensajes.get(i);
								String destId = element.substring(0, element.indexOf("|"));
								String msg = element.substring(element.indexOf("|")+1, element.length());
								if (id.equals(destId)) {
									mensajeRta += msg + "|";								
								}
							}
						}
						mensajeRta += "No hay mensajes|";
						output.println(mensajeRta);
						System.out.println("El servidor envio mensajes");
					break;
					case 3:
						System.out.println("El servidor recibio una consulta");
						id = mensaje.substring(mensaje.indexOf("|")+1, mensaje.length());
						String msg = "No habia mensajes";
						if (!colaDeMensajes.isEmpty()) {
							int i = 0;
							while (i < colaDeMensajes.size()) {
								String element = colaDeMensajes.get(i);
								String destId = element.substring(0, element.indexOf("|"));
								if (id.equals(destId)) {
									colaDeMensajes.remove(i);
									msg = "Se borraron los mensajes";
								}
								else {
									i = i + 1;
								}
							}
						}
						output.println(msg);
						System.out.println("En el servidor " + msg);
					break;
					case 0:
						cliente.close();
						System.out.println("Se cerro la conexion con el cliente " + cliente.getInetAddress() + ":" + cliente.getPort());
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
