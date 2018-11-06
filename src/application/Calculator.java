package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Berechnet das Formelrad
 * @author Peter Rutschmann
 * @version 13.09.2018
 */
public class Calculator {
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
		
		// Nur vorübergehen, bis Florian das Feature für die Berechnung für Wiederstand einbaut.
		if(widerstand != 0) {
			inputFieldCounter++;
		}
		
		if(inputFieldCounter > 2) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Schau, ein Informationsdialog");
			alert.setContentText("Achtung, mehr als zwei InputFelder ausgefüllt, die Werte in den Inputfeldern könnten deshalb verfälscht sein. Bitte genau zwei Felder ausfüllen.");
			alert.showAndWait();
		}
		
	}
	
	/* Hier die Methoden mit den Formlen hinzufügen
	 */
	
	// Formeln für p (Leistung)
	public double pAusUundI(double u, double i) {
		double p = u*i;
		return p;
	}
	
	public double pAusUundR(double u, double r) {
		double p = u * u / r;
		return p;
	}
	
	public double pAusRundI(double r, double i) {
		double p = i * i * r;
		return p;
	}
	
	// Formeln für u (Spannung)
	public double uAusPUndI(double p, double i) {
		double u = p / i;
		return u;
	}
	
	public double uAusPUndR(double p, double r) {
		double u = Math.sqrt(p * r);
		return u;
	}
	
	public double uAusIUndR(double i, double r) {
		double u = i * r;
		return u;
	}
	
	// Formeln für i (Stromstärke)
	public double iAusPundU(double p, double u) {
		double i = p / u;
		return i;
	}
	
	public double iAusPundR(double p, double r) {
		double i = Math.sqrt(p / r);
		return i;
	}
	
	public double iAusUundR(double u, double r) {
		double i = u / r;
		return i;
	}
}
