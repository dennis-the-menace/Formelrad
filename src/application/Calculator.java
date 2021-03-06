package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Berechnet das Formelrad
 * @author Peter Rutschmann
 * @version 13.09.2018
 */
public class Calculator {
	private int ConflictVariable2;
	private double leistung;
	private double spannung;
	private double strom;
	private double widerstand;
	
	public Calculator(double leistung, double spannung, double strom, double widerstand) {
		super();
		this.leistung = leistung;
		this.spannung = spannung;
		this.strom = strom;
		this.widerstand = widerstand;
	}
	
	public double getLeistung() {
		return leistung;
	}
	
	public double getSpannung() {
		return spannung;
	}

	public double getStrom() {
		return strom;
	}

	public double getWiderstand() {
		return widerstand;
	}

	@Override
	public String toString() {
		return "Calculator [leistung=" + leistung + 
				", spannung=" + spannung + 
				", strom=" + strom + 
				", widerstand="	+ widerstand + "]";
	}

	public void calculate() {
		/* Hier auf Grund der vorhanden Werte entscheiden
		 * welche Methode unten aufgerufen werden muss.
		 */
		int inputFieldCounter = 0;
		
		if(leistung == 0) {
			if(spannung != 0 && strom != 0) {
				leistung = pAusUundI(spannung, strom);				
			} else if(spannung != 0 && widerstand != 0) {
				leistung = pAusUundR(spannung, strom);
			} else if(widerstand != 0 && strom != 0) {
				leistung = pAusRundI(widerstand, strom);
			}
		} else {
			inputFieldCounter++;
		}
		
		if(spannung == 0) {
			if(leistung != 0 && strom != 0) {
				spannung = uAusPUndI(leistung, strom);				
			} else if(leistung != 0 && widerstand != 0) {
				spannung = uAusPUndR(leistung, widerstand);				
			} else if(strom != 0 && widerstand != 0) {
				spannung = uAusIUndR(strom, widerstand);				
			}
		} else {
			inputFieldCounter++;
		}
		
		if(strom == 0) {
			if(leistung != 0 && spannung != 0) {
				strom = iAusPundU(leistung, spannung);
			} else if(leistung != 0 && widerstand != 0) {
				strom = iAusPundR(leistung, widerstand);
			} else if(spannung != 0 && widerstand != 0) {
				strom = iAusUundR(spannung, widerstand);
			}
		} else {
			inputFieldCounter++;
		}
		
		if (widerstand == 0)
			if (strom != 0 && spannung != 0)
				widerstand = rAusIundU(strom, spannung);
			else if (strom != 0 && leistung != 0)
				widerstand = rAusIundP(strom, leistung);
			else if (spannung != 0 && leistung != 0)
				widerstand = rAusUundP(spannung, leistung);
		else
			inputFieldCounter++;
		
		if(inputFieldCounter > 2) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Schau, ein Informationsdialog");
			alert.setContentText("Achtung, mehr als zwei InputFelder ausgef�llt, die Werte in den Inputfeldern k�nnten deshalb verf�lscht sein. Bitte genau zwei Felder ausf�llen.");
			alert.showAndWait();
		}
		
	}
	
	/* Hier die Methoden mit den Formlen hinzuf�gen
	 */
	
	// Formeln f�r p (Leistung)
	public double pAusUundI(double u, double i) {
		double p = u*i;
		System.out.println("P aus U und I: " + p);
		return p;
	}
	
	public double pAusUundR(double u, double r) {
		double p = u * u / r;
		System.out.println("P aus U und R: " + p);
		return p;
	}
	
	public double pAusRundI(double r, double i) {
		double p = i * i * r;
		System.out.println("P aus R und I: " + p);
		return p;
	}
	
	// Formeln f�r u (Spannung)
	public double uAusPUndI(double p, double i) {
		double u = p / i;
		System.out.println("U aus P und I: " + u);
		return u;
	}
	
	public double uAusPUndR(double p, double r) {
		double u = Math.sqrt(p * r);
		System.out.println("U aus P und R: " + u);
		return u;
	}
	
	public double uAusIUndR(double i, double r) {
		double u = i * r;
		System.out.println("U aus R und I: " + u);
		return u;
	}
	
	// Formeln f�r i (Stromst�rke)
	public double iAusPundU(double p, double u) {
		double i = p / u;
		System.out.println("I aus P und U: " + i);
		return i;
	}
	
	public double iAusPundR(double p, double r) {
		double i = Math.sqrt(p / r);
		System.out.println("I aus P und R: " + i);
		return i;
	}
	
	public double iAusUundR(double u, double r) {
		double i = u / r;
		System.out.println("I aus R und U: " + i);
		return i;
	}

	// Formeln fuer r (Widerstand)
	public double rAusIundU(double i, double u) {
		double r = u / i;
		System.out.println("R aus I und U: " + r);
		return r;
	}

	public double rAusIundP(double i, double p) {
		double r = p / i * i;
		System.out.println("R aus P und I: " + r);
		return r;
	}

	public double rAusUundP(double u, double p) {
		double r = u * u / p;
		System.out.println("R aus P und U: " + r);
		return r;
	}
}
