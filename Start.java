package ikopr_valapplicatie;

import javafx.application.Application;

public class Start {

	public static void main(String[] args) throws InterruptedException {


		// Aanmaken View
		// De view maakt de model aan, kijk in view
		ViewValFormule view = new ViewValFormule();
		
		// dit kan ik niet uileggen, maar het werkt zo, de applicatie start
		Application.launch(view.getClass());
	}

}
