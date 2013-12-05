
import akka.actor.ActorRef;

public class Main {
	private static int count = 0;
	/**
	 * Add passengers to each line
	 * @param line Line number
	 * @param num total number of passengers in the line
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
		
		int totalPassengers= 5; //total number of passengers
		
		
		 ActorRef documentActor = akka.actor.Actors.actorOf(DocumentCheck.class);
		 documentActor.start();
		 
		 addPassengers(documentActor, totalPassengers);
		 System.out.println("End of day. Jail passengers transfered to permanent detention.");
	}
}
