import java.util.ArrayList;

import akka.actor.UntypedActor;
import akka.actor.ActorRef;

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
			System.out.println("Jail actor. "+10+" security stations feeding passengers. "+badPassenger+" bad passengers. Message Received");
			System.out.println("Passenger: "+p.getPassengerID()+" sent to jail.");
		}
		
		//TO DO : print end of day message where all passengers are transferred to permanent detention
		
	}

}
