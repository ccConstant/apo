import java.util.Random;

public class Main {

	public static void main(String[] args) {
		/*Controller c = new Controller();
		c.afficherAccueil();

		/*SimulationForestFire simul=new SimulationForestFire() ;
		simul.init_simulation(3, 10, 10, 8,true, 0.5, 0.2);
		simul.getAutoate().print();
		System.out.println("SUIVANT");
		simul.rechargement();
		System.out.println("SUIVANT");
		simul.rechargement();
		System.out.println("SUIVANT");
		simul.rechargement();
		System.out.println("SUIVANT");
		simul.rechargement();
		System.out.println("SUIVANT");
		simul.rechargement();*/

		 /* 
		 //pour tester le 1D
		 Simulation1D simul= new Simulation1D();
		 simul.init_simulation(1, 7,180);
		 simul.getAutomate().print();
		 System.out.println("SUIVANT");
		 simul.rechargement();
		*/

		 
		 //pour tester la regle de majorité
		 SimulationMajority simul = new SimulationMajority();
		 simul.init_simulation(1, 7);
		 simul.getAutomate().print();
		 System.out.println("SUIVANT");
		 simul.rechargement();
		 
		
	}

}
