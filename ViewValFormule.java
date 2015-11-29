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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewValFormule extends Application {

	ModelValFormule model;
	Timeline lijn;
	Circle cir;
	Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		model = new ModelValFormule(5, 0.001, 9.81);
		model.setSchermHoogte(500);
		
		lijn = new Timeline (new KeyFrame(Duration.millis(model.geTijdsEenheid() * 1000), e -> verplaatsBal()));
		lijn.setCycleCount(Timeline.INDEFINITE);
		
		
		Pane pane = new Pane();
		cir = new Circle();
		cir.setStroke(Color.BLACK);
		cir.setRadius(20);
		cir.centerXProperty().bind(pane.widthProperty().divide(2));
		cir.setCenterY(0);
		
		
		
		
		
		Button but = new Button("Knoppie");
		but.setOnAction(e -> lijn.play());
		
		pane.getChildren().add(cir);
		pane.getChildren().add(but);
		
		scene = new Scene(pane, 500,500);
		
		
		primaryStage.setTitle("De valformule applicatie");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public void setModelValFormule(ModelValFormule model){
		this.model = model;
	}
	
	public void verplaatsBal(){
		cir.setCenterY(model.getNextSchermPositie());
//		cir.setCenterY(cir.getCenterY() + 5);
		
		if (model.getGevallenAfstand() >= model.getMaximaleAfstand()){
			model.bounce();
		}
		
		if (model.getSchermHoogte() != scene.getHeight()){
			model.setSchermHoogte((int) scene.getHeight());
			System.out.println(scene.getHeight());
			System.out.println("de gevallen afstand in meters is: " + model.getGevallenAfstand());
			System.out.println("De snelheid km/pu is: " + ((model.getValSnelheid() * 3600) / 1000));
		}
		
		
		
	}
	

}
