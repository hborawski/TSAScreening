
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActorFactory;

/**
 * Main method that runs the program.
 * It creates all the passengers and sends them through document check.
 * @author Anshul
 */
public class Main {
	private static int count = 0;
	/**
	 * Create passengers and add them to document check
	 * @param checkPoint common document check
	 * @param num total number of passengers to enter TSA screening
	 */
	public static void addPassengers(ActorRef checkPoint, int num){
		for(int i=0; i<num; i++){
			count++;
			checkPoint.tell(new PassengerEnters(count));
		}			
	}
	/**
	 * Main method that runs the program
	 * @param args
	 */
	public static void main(String args[]){
		
		int totalPassengers= 3; //total number of passengers can be set here
		final int totalLines = 2; // total number of lines at TSA screening can be set here
		 
		 final ActorRef documentCheck = Actors.actorOf(new UntypedActorFactory(){
				@Override
				public Actor create(){
					return new DocumentCheck(totalLines);
				}
			});
		 documentCheck.start();
		 
		 addPassengers(documentCheck, totalPassengers);
		 System.out.println("End of day. Jail passengers transfered to permanent detention.");
	}
}
