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
		if(message instanceof DocumentCheck){ //initial message
			;
		}
		if(message instanceof Security){ //returned results from the security check
			
		}
		
	}

}
