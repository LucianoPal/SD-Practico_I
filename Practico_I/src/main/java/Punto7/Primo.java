package Punto7;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Primo implements Tarea, Serializable{
	private static final long serialVersionUID = 1L;
	private int value;
	private boolean primo = true;
	
	public Primo(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isPrimo() {
		return primo;
	}

	public void setPrimo(boolean primo) {
		this.primo = primo;
	}

	public Object ejecutar() throws RemoteException {
		int i = 2;
		
		while (this.primo && i < this.value) {
			if (this.value % i == 0) {
				this.setPrimo(false);
			}
			i++;
		}
		return this;
	}

}