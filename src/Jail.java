import java.util.ArrayList;

import akka.actor.UntypedActor;
import akka.actor.ActorRef;

/**
 * Jail actor class that handles messages received from the Queue actor about
 * bad passengers.
 * @author Anshul
 */
public class Jail extends UntypedActor{
	
	private ArrayList<JailPassenger> illegalPeopleList = new ArrayList<JailPassenger>();
	private int badPassenger;
	private int numOfQueues;
	
	/**
	 * onReceive method tells jail actor what to do when a message is received
	 */
	public void onReceive(Object message) throws Exception {
		if( message instanceof JailPassenger){
			badPassenger++;
			JailPassenger p = (JailPassenger)message; 
			illegalPeopleList.add(p); //add the passenger to list of people in jail	
			System.out.println("PassengerID: "+p.getPassengerID()+" sent to jail.");
			System.out.println("Jail actor: "+badPassenger+" bad passengers. Message Received.");
		}
	}

}
