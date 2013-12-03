import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;


/**
 * Queue actor class that represents each individual queue
 * @author Anshul
 *
 */
public class Queue extends UntypedActor {
	//private ArrayList<ActorRef> queue = new ArrayList<ActorRef>();
	
	private ActorRef document;
	/**
	 * Method that knows what to do when a message is sent to Queue actor
	 */
	public void onReceive(Object message) throws Exception {
		if(message instanceof PassengerQueued){ //initial message
			int id = ((PassengerQueued)message).getPassengerID();
			ActorRef bagScanner = akka.actor.Actors.actorOf(BagScanner.class);
			ActorRef bodyScanner = akka.actor.Actors.actorOf(BodyScanner.class);
			CheckBag bag = new CheckBag(id);
			CheckPassenger pass = new CheckPassenger(id);
			
			bagScanner.tell(bag);
			bodyScanner.tell(pass);
		}
		else if(message instanceof Passenger){ //returned results from the security check
			Passenger p = (Passenger)message;
			System.out.println("Queue: "+p.getId()+" finished security. Legality: "+p.getLegality());
			if( !p.getLegality()){
				JailPassenger j = new JailPassenger(p);
				ActorRef jail = akka.actor.Actors.actorOf(Jail.class);
				jail.tell(j);			
			}else{
				//remove from system?
			}
			
		}
		
	}

}
