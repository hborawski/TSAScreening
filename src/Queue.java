import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Queue actor class that represents each individual queue
 * @author Anshul
 */
public class Queue extends UntypedActor {
	//private ArrayList<ActorRef> queue = new ArrayList<ActorRef>();
	private ActorRef bag;
	private ActorRef body;
	private ActorRef jail;
	private int ID;
	
	/**
	 * Public constructor to construct a queue
	 * @param ID line number
	 * @param bag Bag to bad scanner actor reference
	 * @param body Body scanner actor reference
	 * @param jail Jail reference
	 */
	public Queue(int ID, ActorRef bag, ActorRef body, ActorRef jail){
		super();
		this.bag = bag;
		this.body = body;
		this.jail = jail;
		this.ID = ID;
	}
	
	/**
	 * Method that knows what to do when a message is sent to Queue actor
	 */
	public void onReceive(Object message) throws Exception {
		if(message instanceof PassengerQueued){ //initial message
			
			int id = ((PassengerQueued)message).getPassengerID();
			System.out.println("Queue actor: "+id+" joins queue in line "+ID+". Message Received.");
			CheckBag bagCheck = new CheckBag(id);
			CheckPassenger pass = new CheckPassenger(id);
			
			bag.tell(bagCheck);
			body.tell(pass);
		}
		else if(message instanceof Passenger){ //returned results from the security check
			Passenger p = (Passenger)message;
			System.out.println("Queue actor: "+p.getId()+" completed security. Result: "+p.getLegality()+". Message received.");
			if( p.getLegality()==false){
				JailPassenger j = new JailPassenger(p);
				jail.tell(j);			
			}else{
				//remove passenger from system
				p = null;
			}
			
		}
		
	}

}
