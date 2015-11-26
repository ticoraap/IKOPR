package ikopr_valapplicatie;

public class Start {

	public static void main(String[] args) throws InterruptedException {
		ModelValFormule formule = new ModelValFormule(800.0, 0.1, 9.81);
		formule.setSchermHoogte(600);
		
		while (!formule.animatieVoltooid()){
			formule.nextStepFormule();
			System.out.println("de gevallen afstand in pixels is: " + formule.getNextSchermPositie());
			System.out.println("Valsnelheid: " + formule.getValSnelheid());
			System.out.println("Valafstand: " + formule.getGevallenAfstand());
			Thread.sleep(100);

		}
		
	}

}
