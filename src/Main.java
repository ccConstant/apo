import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		//Controller c = new Controller();
		//c.afficherAccueil();
		SimulationForestFire simul=new SimulationForestFire() ;
		simul.init_simulation(1, 5, 5, 6,true, 0, 0, "Sud",0.8 );
		simul.getAutomate().print();
		System.out.println("SUIVANT");
		simul.rechargement();
		System.out.println("SUIVANT");
		simul.rechargement();
		System.out.println("SUIVANT");
		simul.rechargement();
		System.out.println("SUIVANT");
		simul.rechargement();
		System.out.println("SUIVANT");
		simul.rechargement();
		
	}

}
