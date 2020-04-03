package Punto7;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Random;

public class Aleatorio implements Tarea, Serializable{

	private static final long serialVersionUID = 1L;
	private int value;
	private int rango;
	
	public Aleatorio (int rango) {
		this.rango = rango;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getRango() {
		return rango;
	}

	public void setRango(int rango) {
		this.rango = rango;
	}

	public Object ejecutar() throws RemoteException {
		Random r = new Random();
		this.setValue(r.nextInt(this.getRango()*2)-this.getRango());
		return this;
	}

}
