import java.util.ArrayList;

import akka.actor.UntypedActor;
import akka.actor.ActorRef;

public class Jail extends UntypedActor{
	
	private ArrayList<Passenger> illegalPeopleList = new ArrayList<Passenger>();
	private ActorRef badPassenger;
	private int numOfQueues;
	/**
	 * onReceive method tells jail actor what to do when a message is received
	 */
	public void onReceive(Object message) throws Exception {
		if( message instanceof Passenger){
			Passenger p = (Passenger)message; 
			illegalPeopleList.add(p); //add the passenger to list of people in jail	
			System.out.println("Jail actor. "+numOfQueues+" security stations feeding passengers. Message Received+");
			System.out.println("Passenger: "+p.getId()+" sent to jail.");
		}
		
		//TO DO : print end of day message where all passengers are transferred to permanent detention
		
	}

}
