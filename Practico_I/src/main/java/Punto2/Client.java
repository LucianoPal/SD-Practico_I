package Punto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Socket servidor = new Socket("127.0.0.1", 9000);
			
			System.out.println("Cliente iniciado");
			BufferedReader input = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
			PrintWriter output = new PrintWriter(servidor.getOutputStream(), true);
			
			System.out.println("Ingrese el mensaje");
			Scanner scn = new Scanner(System.in);
			String msj = scn.nextLine();
			scn.close();
			
			msj = "Mensaje del cliente: " + msj + ", puerto: " + servidor.getLocalPort();
			System.out.println(msj);
			
			Date dInicio = new Date();
			output.println(msj);
			
			String mensajeRta = input.readLine();
			Date dFinal = new Date();
			
			long tiempoInicio = dInicio.getTime();
			long tiempoFinal = dFinal.getTime();
			long resultado = tiempoFinal - tiempoInicio;
			System.err.println("\n" + dInicio.toString() + "  " + dFinal.toString());
			System.err.println("diferencia: " + resultado + "\n");
			
			System.out.println("Mensaje de respuesta: " + mensajeRta);
			
			servidor.close();
			System.out.println("Se cerro la conexion con el servidor");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
