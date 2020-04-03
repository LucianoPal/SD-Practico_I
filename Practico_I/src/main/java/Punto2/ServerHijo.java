package Punto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHijo implements Runnable{
	
	Socket cliente;
	
	public ServerHijo(Socket cliente) {
		this.cliente = cliente;
	}

	public static void main(String[] args) {
		
	}

	public void run() {
		BufferedReader input;
		try {
			input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter output = new PrintWriter(cliente.getOutputStream(), true);
			
			String mensaje = input.readLine();
			System.out.println("El cliente ha enviado: " + mensaje);
			mensaje = "'" + mensaje + "'" + ", soy el servidor";
			output.println(mensaje);
			System.out.println("El servidor respondio");
			cliente.close();
			System.out.println("Se cerro la conexion con el cliente " + cliente.getInetAddress() + ":" + cliente.getPort());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
