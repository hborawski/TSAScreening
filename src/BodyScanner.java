import java.util.Random;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Body scanner actor class that handles messages received by the body scanner
 * @author Anshul
 */
public class BodyScanner extends UntypedActor{
	private Random random = new Random();
	private int ID;
	private int passengerID;
	private ActorRef security;
	
	/**
	 * Public constructor for the scanner class
	 * @param ID passenger id
	 * @param security reference to the security station it sends the passenger to
	 */
	public BodyScanner(int ID, ActorRef security){
		super();
		this.security = security;
		this.ID = ID;
	}
	
	/**
	 * Method that handles when a passenger is messaged to the body scanner
	 */
	public void onReceive(Object message) throws Exception {
		if(message instanceof CheckPassenger){
			passengerID = ((CheckPassenger) message).getPassengerID();
			PassengerBodyChecked sendMessage;
			if(random.nextInt(10) >= 2){
				sendMessage = new PassengerBodyChecked(passengerID, true);
				System.out.println("Body Scanner actor: Line: " + ID + ". PASSED passengerID " + passengerID);
			}else{
				sendMessage = new PassengerBodyChecked(passengerID, false);
				System.out.println("Body Scanner actor: Line: " + ID + ". FAILED passengerID " + passengerID);
			}
			
			security.tell(sendMessage);
		}
	}

}
