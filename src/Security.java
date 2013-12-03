import akka.actor.ActorRef;
import akka.actor.UntypedActor;


public class Security extends UntypedActor{
	
	private int bagTemp=-1, bodyTemp=-1;
	private int linNum; //the line in which the security scanner is at
	
	private PassengerBagChecked bagChecked=null; //temp bag check to hold the content of the real bag check if bag
										// check happens before body check
	
	private PassengerBodyChecked bodyChecked= null; //temp body check to hold the content of the real body check if body
										  // check happens before bag check
	/**
	 * method that handles when a message is sent to Security class
	 */
	public void onReceive(Object message) throws Exception {
		if(message instanceof PassengerBagChecked){ 
			PassengerBagChecked bag = (PassengerBagChecked)message;
			bagChecked = bag; //assign the bag results to temp bagChecked
			if(bag.getResult() == true){
				bagTemp = 0;
				if((bodyChecked!=null) && (bodyChecked.getResult()==true)){ //both scans passed
					Passenger PASSenger = new Passenger(bagChecked.getPassengerID());
					PASSenger.setLegality(true);
					
					//Tell queue the result
					ActorRef queue = akka.actor.Actors.actorOf(Queue.class);
					
					queue.tell(PASSenger);
				}
			} //bag passed the scan
			else{
				bagTemp =1;
				Passenger PASSenger = new Passenger(bagChecked.getPassengerID());
				PASSenger.setLegality(false); //failed bag scan
				
				//Tell queue the result
				ActorRef queue = akka.actor.Actors.actorOf(Queue.class);
				
				queue.tell(PASSenger);
			} //bad failed the scan
		}
		else if(message instanceof PassengerBodyChecked){
			PassengerBodyChecked body = (PassengerBodyChecked)message;
			bodyChecked = body;
			if(body.getResult() == true){
				bodyTemp = 0;
				if((bagChecked!=null) && (bagChecked.getResult()==true)){ //both scans passed
					Passenger PASSenger = new Passenger(bagChecked.getPassengerID());
					PASSenger.setLegality(true);
					
					//Tell queue the result
					ActorRef queue = akka.actor.Actors.actorOf(Queue.class);
					
					queue.tell(PASSenger);
				}
			} //bag passed the scan
			else{
				bodyTemp =1;
				Passenger PASSenger = new Passenger(bagChecked.getPassengerID());
				PASSenger.setLegality(false); // failed the body scan
				
				//Tell queue the result
				ActorRef queue = akka.actor.Actors.actorOf(Queue.class);
				
				queue.tell(PASSenger);
			} //bad failed the scan
		}
		
	}

}
