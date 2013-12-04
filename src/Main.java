import java.util.ArrayList;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActorFactory;

public class Main {
	private static int count = 11;
	private ArrayList<PassengerEnters> allPassengers = new ArrayList<PassengerEnters>();
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
		
		int totalPassengers= 2; //total number of passengers
		
		
		 ActorRef documentActor = akka.actor.Actors.actorOf(DocumentCheck.class);
		 documentActor.start();
		 
		 addPassengers(documentActor, totalPassengers);
		
	}
}
