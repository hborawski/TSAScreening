import java.util.Random;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class DocumentCheck extends UntypedActor{
	private Random random = new Random();
	private int ID;
	private int passengerID;
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof PassengerEnters){
			passengerID = ((PassengerEnters) message).getPassengerID();
			PassengerQueued sendMessage;
			if(random.nextInt(10) >= 2){
				sendMessage = new PassengerQueued(passengerID);
				System.out.println("Document Check: " + ID + " passed passengerID " + passengerID);
				
				//Queue the passenger since they pass
				ActorRef queue = akka.actor.Actors.actorOf(Queue.class);
				
				queue.tell(sendMessage);
			}else{
				sendMessage = new PassengerQueued(passengerID);
				System.out.println("Document Check: " + ID + " failed passengerID " + passengerID);
			}
			
			
		}
		
	}

}
