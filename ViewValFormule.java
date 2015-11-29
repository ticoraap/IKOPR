package ikopr_valapplicatie;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ViewValFormule extends Application {

	ModelValFormule model;
	Timeline lijn;
	Circle cir;
	Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		// Een model, en de schermgroote gezet
		model = new ModelValFormule(5, 0.001, 9.81);
		model.setSchermHoogte(500);
		
		// Een TimeLine object die de methode verplaatsbal uitvoert,
		// die methode moet de volgnede keer een methode van de controller zijn
		lijn = new Timeline (new KeyFrame(Duration.millis(model.getTijdsEenheid() * 1000), e -> verplaatsBal()));
		lijn.setCycleCount(Timeline.INDEFINITE);
		
		// Een pane en een cirkel.
		Pane pane = new Pane();
		cir = new Circle();
		cir.setStroke(Color.BLACK);
		cir.setRadius(20);
		// De cirkel positie is de helft van de schermbreedte
		cir.centerXProperty().bind(pane.widthProperty().divide(2));
		cir.setCenterY(0);
		
		// Dat kinderachtige knopje
		Button but = new Button("Knoppie");
		// En als je er op drukt begint het TimeLine object.
		but.setOnAction(e -> lijn.play());
		
		// Die cirkel en kinderachtige knop op de pane
		pane.getChildren().add(cir);
		pane.getChildren().add(but);
		
		// voeg de pane toe aan de scene
		scene = new Scene(pane, 500,500);
		
		// Stage een naam geven
		primaryStage.setTitle("De valformule applicatie");
		// En voeg de scene toe aan de Stage
		primaryStage.setScene(scene);
		// En dan mag het beginnen
		primaryStage.show();		
	}

	// De methode die steeds door de TimeLine wordt aangeroepen
	public void verplaatsBal(){
		
		// Geef de bal een nieuwe locatie op het scherm, door de waarden te krijgen van de modelmethode
		cir.setCenterY(model.getNextSchermPositie());

		// Dit laat de bal stuiteren, een modelmethode
		if (model.getGevallenAfstand() >= model.getMaximaleAfstand()){
			model.bounce();
		}
		
		// Dit maakt het scherm resisable, dus tijdens de animatie kan je het scherm hoger en lager maken
		// Als het model achterloopt op de scene.getHeight dan -
		if (model.getSchermHoogte() != scene.getHeight()){
			// - krijgt de model een nieuwe schermhoogte
			model.setSchermHoogte((int) scene.getHeight());
			
			// Als je het scherm resized zie je deze info, kan in pricipe weg
			System.out.println(scene.getHeight());
			System.out.println("de gevallen afstand in meters is: " + model.getGevallenAfstand());
			System.out.println("De snelheid km/pu is: " + ((model.getValSnelheid() * 3600) / 1000));
		}
		
		
		
	}
	

}
