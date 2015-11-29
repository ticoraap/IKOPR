package ikopr_valapplicatie;

public class ModelValFormule {
	
	// Variablen voor model
	private double maximaleAfstand;
	private double gevallenAfstand = 0;
	private double tijdsEenheidSeconden;
	private double constanteValSnelheid;
	private double valSnelheid = 0;
	private int schermhoogte;
	private boolean animatieklaar = false;

	// constructor default met 100 meter om de 0.1 sec update, en een valsnelheid van 9.81m/s
	public ModelValFormule(){
		this(100.0, 0.1, 9.81);
	}
	
	// constructor voor alleen de maximale afstand
	public ModelValFormule(double maximaleAfstand) {
		this(maximaleAfstand, 0.1, 9.81);	
	}
	
	// constructor voor de maximale afstand en tijdseenheden
	public ModelValFormule(double maximaleAfstand,double tijdsEenheidSeconden) {
		this(maximaleAfstand, tijdsEenheidSeconden, 9.81);
		
	}
	
	// constructor voor de maximale afstand, tijdseenheden en de valsnelheid
	public ModelValFormule(double maximaleAfstand,double tijdsEenheidSeconden, double constanteValSnelheid ) {
		this.maximaleAfstand = maximaleAfstand;
		this.tijdsEenheidSeconden = tijdsEenheidSeconden;
		this.constanteValSnelheid = constanteValSnelheid;
	}
	
	// Geeft de positie op de Y as voor het vallend object, 
	// dit relatief aan de hoogte van het venster
	// waarbij de schermhoogte wel opgegeven moet zijn
	public int getNextSchermPositie(){
		
		// De schermhoogte moet opgegeven zijn voor deze methode, anders bericht en quit
		if(this.schermhoogte == 0){
			System.out.println("De schermhoogte is niet ingesteld voor het ModelValFormule object, doe dit met de methode setSchermHoogte(int)");
			System.exit(0);
		}
		
		// Volgende stap en bereken de locatie van het object relatief aan de schermhoogte
		nextStepFormule();
		int relatiefAanSchermhoogte = (int) (this.gevallenAfstand / this.maximaleAfstand * this.schermhoogte);
		if(relatiefAanSchermhoogte >= this.schermhoogte){
			this.animatieklaar = true;
		}
		return relatiefAanSchermhoogte;
	}
	
	// Zet de valsnelheid in de min, Voorbeeld: 100 is dan -100, 55 is dan -55
	public void bounce(){
		this.valSnelheid = valSnelheid - (valSnelheid * 2);
	}
	
	// -- De methodes hierna zijn voor het berekenen van de valformule
	// De nieuwe afstand is de afstand + de snelheid met tijd vermenigvuldigd
	private double nieuweAfstand(double afstand, double snelheid, double tijd){
		double nieuweAfstand = afstand + (snelheid * tijd);
		return nieuweAfstand;
	}
	// De nieuwe snelheid is de oude snelheid met daarbij de valsnelheid vermenigvuldigd met de tijdseenheid
	private double nieuweSnelheid(double valsnelheid, double constantSnelheid, double tijd){
		double nieuweSnelheid = valsnelheid + (constantSnelheid * tijd);
		return nieuweSnelheid;
	}
	
	// De methode die een update uitvoert
	public void nextStepFormule(){
		gevallenAfstand = nieuweAfstand(gevallenAfstand, valSnelheid, tijdsEenheidSeconden);
		valSnelheid = nieuweSnelheid(valSnelheid,constanteValSnelheid,tijdsEenheidSeconden);
	}
	

	// -- De setters
	public void setSchermHoogte(int YAsVensterhoogte){
		this.schermhoogte = YAsVensterhoogte;
	}
	
	// -- De getters
	public double getGevallenAfstand(){
		return this.gevallenAfstand;
	}
	
	public double getValSnelheid(){
		return this.valSnelheid;
	}
	
	public boolean animatieVoltooid(){
		return this.animatieklaar;
	}
	
	public double getTijdsEenheid(){
		return this.tijdsEenheidSeconden;
	}
	
	public double getMaximaleAfstand(){
		return this.maximaleAfstand;
	}
	
	public int getSchermHoogte(){
		return this.schermhoogte;
	}
}
