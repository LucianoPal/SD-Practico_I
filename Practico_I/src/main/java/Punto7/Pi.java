package Punto7;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Pi implements Tarea, Serializable{
	private static final long serialVersionUID = 1L;
	private int precision;
	private Double value = Math.PI;

	public Pi(int precision) {
		this.setPrecision(precision);
	}

	public double getValue() {
		return value;
	}

	public void setValue(Double i) {
		this.value = i;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public Object ejecutar() throws RemoteException {
		String pi = value.toString();
		this.setValue(Double.parseDouble(pi.substring(0, (this.getPrecision()+2))));
		return this;
	}
	
}