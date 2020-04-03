package Punto3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private static ArrayList<String> colaDeMensajes = new ArrayList<String>();
	
	public static void main(String[] args) {
		try {
			int puerto = 9000;
	    	ServerSocket socket = new ServerSocket(puerto);
			System.out.println("Servidor iniciado en el puerto " + puerto);
			
//			System.out.println("Aprete 'q' para salir");
			
			while (true) {
				Socket cliente = socket.accept();
				System.out.println("Se ha conectado un cliente: " + cliente.getInetAddress().getCanonicalHostName() + ":" + cliente.getPort());
				
				ServerHijo hijo = new ServerHijo(cliente, colaDeMensajes);
				
				Thread shThread = new Thread(hijo);
				shThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
