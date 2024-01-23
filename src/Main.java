import java.util.Random;

public class Main {

	public static void main(String[] args) {
		//Controller c = new Controller();
		//c.afficherAccueil();
		SimulationLifeGame simul=new SimulationLifeGame() ;
		simul.init_simulation(10, 10);
		simul.getAutomate().print();
		System.out.println("SUIVANT");
		simul.rechargement();
		
		
	}

}
