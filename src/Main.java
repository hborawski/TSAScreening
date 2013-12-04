import java.util.ArrayList;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActorFactory;

public class Main {
	private static int count = 11;
	private static ArrayList<ActorRef> lines = new ArrayList<ActorRef>();
	
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
		
		int totalLines = 1; // total number of lines
		int passL1 = 20; // passengers in line 1
		int passL2 = 5; // passengers in line 2
		int passL3 = 2; // passengers in line 3
		
		final ActorRef jailActor = akka.actor.Actors.actorOf(Jail.class);
		jailActor.start();
		
		int line;
		for( line=0; line<totalLines; line++){
			final int l = line+1;
			final ActorRef lineActor = Actors.actorOf(new UntypedActorFactory(){
				public Actor create(){
					return new Line(l, jailActor);
				}
			});
			lineActor.start();
			lines.add(lineActor);
		} //end of loop
		
		addPassengers(lines.get(0), passL1);
		//addPassengersToLine(lines.get(1), passL2);
		//addPassengersToLine(lines.get(2), passL3);
		
	}
}
