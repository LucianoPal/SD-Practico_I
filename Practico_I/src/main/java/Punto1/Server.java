package Punto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	
	public static void main( String[] args )
    {
        try {
        	int puerto = 9000;
        	ServerSocket socket = new ServerSocket(puerto);
			System.out.println("Servidor iniciado en el puerto " + puerto);
			Socket cliente = socket.accept();
			System.out.println("Se ha conectado un cliente: " + cliente.getInetAddress().getCanonicalHostName() + ":" + cliente.getPort());
			
			BufferedReader input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter output = new PrintWriter(cliente.getOutputStream(), true);
			
			String mensaje = input.readLine();
			System.out.println("El cliente ha enviado: " + mensaje);
			mensaje = "'" + mensaje + "'" + ", soy el servidor";
			output.println(mensaje);
			System.out.println("El servidor respondio");
			cliente.close();
			System.out.println("Se cerro la conexion con el cliente");
			socket.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}
