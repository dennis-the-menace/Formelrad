package application;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Formelrad Application
 * @author Peter Rutschmann
 * @version 13.09.2018
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			// Creating an image
			Image image = new Image(new FileInputStream("bin\\application\\formelradelektronik.gif"));
			ImageView imageView = new ImageView(image);
			imageView.setX(10);
			imageView.setY(10);
			imageView.setFitHeight(300);
			imageView.setFitWidth(300);
			imageView.setPreserveRatio(true);
			root.getChildren().add(imageView);

			Label lbleistung = new Label("Leistung:");
			lbleistung.relocate(10, 285);
			lbleistung.setFont(Font.font(15));
			root.getChildren().add(lbleistung);

			TextField txLeistung = new TextField();
			txLeistung.relocate(100, 285);
			txLeistung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txLeistung);

			Label lblSpannung = new Label("Spannung:");
			lblSpannung.relocate(10, 325);
			lblSpannung.setFont(Font.font(15));
			root.getChildren().add(lblSpannung);

			TextField txSpannung = new TextField();
			txSpannung.relocate(100, 325);
			txSpannung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txSpannung);

			Label lblStrom = new Label("Strom:");
			lblStrom.relocate(10, 365);
			lblStrom.setFont(Font.font(15));
			root.getChildren().add(lblStrom);

			TextField txStrom = new TextField();
			txStrom.relocate(100, 365);
			txStrom.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txStrom);

			Label lblWiderstand = new Label("Widerstand:");
			lblWiderstand.relocate(10, 405);
			lblWiderstand.setFont(Font.font(15));
			root.getChildren().add(lblWiderstand);

			TextField txWiderstand = new TextField();
			txWiderstand.relocate(100, 405);
			txWiderstand.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txWiderstand);

			Button btnBerechnen = new Button();
			btnBerechnen.relocate(100, 445);
			btnBerechnen.setText("Berechnen");
			root.getChildren().add(btnBerechnen);
			
			btnBerechnen.setOnAction(e -> {			
				/*
				 * Idee:
				 * Jedes Textfeld zuerst auf leer pr�fen.
				 * Wenn nicht leer, dann parseen sonst
				 * den Wert dann auf 0 setzen (MagicNumber???)
				 * sodann Calculator aufrufen.
				 * 
				 */
				double leistung, spannung, strom, widerstand;
				String leistungText = txLeistung.getText();
				String spannungsText = txSpannung.getText();
				String stromText = txStrom.getText();
				String widerstandText = txWiderstand.getText();

				txLeistung.setStyle("-fx-text-fill: black");
				txSpannung.setStyle("-fx-text-fill: black");
				txStrom.setStyle("-fx-text-fill: black");
				txWiderstand.setStyle("-fx-text-fill: black");

				System.out.println(leistungText);
				if(leistungText.equals("")) {
					txLeistung.setStyle("-fx-text-fill: red");
					leistung = 0;
				} else {
					leistung = Double.parseDouble(leistungText);
				} 
				
				if (spannungsText.equals("")) {
					txSpannung.setStyle("-fx-text-fill: red");
					spannung = 0;
				} else {
					spannung = Double.parseDouble(spannungsText);
				}
				
				if (stromText.equals("")) {
					txStrom.setStyle("-fx-text-fill: red");
					strom = 0;
				} else {
					strom = Double.parseDouble(stromText);
				}
				
				if (widerstandText.equals("")) {
					txWiderstand.setStyle("-fx-text-fill: red");
					widerstand = 0;
				} else {
					widerstand = Double.parseDouble(widerstandText);
				}
				
				Calculator myCalculator = new Calculator(leistung, spannung, strom, widerstand);
				System.out.print("Vorher:  ");
				System.out.println(myCalculator.toString());
				myCalculator.calculate();
				System.out.print("Nachher: ");
				System.out.println(myCalculator.toString());
					
				txLeistung.setText(Double.toString(myCalculator.getLeistung()));
				txSpannung.setText(Double.toString(myCalculator.getSpannung()));
				txStrom.setText(Double.toString(myCalculator.getStrom()));
				txWiderstand.setText(Double.toString(myCalculator.getWiderstand()));
			});

			Button btnZuruecksetzen = new Button();
			btnZuruecksetzen.relocate(200, 445);
			btnZuruecksetzen.setText("Zur�cksetzen");
			root.getChildren().add(btnZuruecksetzen);

			btnZuruecksetzen.setOnAction(e -> {

				// set color back to black
				txLeistung.setStyle("-fx-text-fill: black");
				txSpannung.setStyle("-fx-text-fill: black");
				txStrom.setStyle("-fx-text-fill: black");
				txWiderstand.setStyle("-fx-text-fill: black");

				// reset value
				txLeistung.setText("");
				txSpannung.setText("");
				txStrom.setText("");
				txWiderstand.setText("");
			});

			Scene scene = new Scene(root, 330, 490);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Formelrad");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
