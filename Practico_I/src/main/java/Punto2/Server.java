package Punto2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			int puerto = 9000;
	    	ServerSocket socket = new ServerSocket(puerto);
			System.out.println("Servidor iniciado en el puerto " + puerto);
			
//			System.out.println("Aprete 'q' para salir");
			
			while (true) {
				Socket cliente = socket.accept();
				System.out.println("Se ha conectado un cliente: " + cliente.getInetAddress().getCanonicalHostName() + ":" + cliente.getPort());
				
				ServerHijo hijo = new ServerHijo(cliente);
				
				Thread shThread = new Thread(hijo);
				shThread.start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
