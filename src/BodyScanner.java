import java.util.Random;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;


public class BodyScanner extends UntypedActor{
	private Random random = new Random();
	private int ID;
	private int passengerID;
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof CheckPassenger){
			passengerID = ((CheckPassenger) message).getPassengerID();
			PassengerBodyChecked sendMessage;
			if(random.nextInt(10) >= 2){
				sendMessage = new PassengerBodyChecked(passengerID, true);
				System.out.println("Body Scanner: " + ID + " passed passengerID " + passengerID);
			}else{
				sendMessage = new PassengerBodyChecked(passengerID, false);
				System.out.println("Body Scanner: " + ID + " failed passengerID " + passengerID);
			}
			
			//Tell security the result
			ActorRef security = akka.actor.Actors.actorOf(Security.class);
			
			security.tell(sendMessage);
		}
	}

}
