import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import java.util.ArrayList;

public class Security extends UntypedActor{
	
	public static ArrayList<PassengerBagChecked> bagResults = new ArrayList<PassengerBagChecked>();
	public static ArrayList<PassengerBodyChecked> bodyResults = new ArrayList<PassengerBodyChecked>();
	
	
	public void onReceive(Object message) throws Exception{
		if(message instanceof PassengerBagChecked){
			PassengerBagChecked bagScan = (PassengerBagChecked)message;
			bagResults.add(bagScan); //add the bag scan result to an array list
			System.out.println("Passenger: "+bagScan.getPassengerID()+" bag was scanned.");
			if(bagScan.getResult()==false){ // illegal passenger
				Passenger PASSenger = new Passenger(bagScan.getPassengerID());
				PASSenger.setLegality(false);
				//Tell queue the result
				ActorRef queue = akka.actor.Actors.actorOf(Queue.class);
				queue.start();
				queue.tell(PASSenger);
			}
			else if(bodyResults.size() > 1){ //at least 1 body has been scanned
				for(int i=0; i<bodyResults.size(); i++){
					if(bodyResults.get(i).getPassengerID() == bagScan.getPassengerID()){ //both results are present
						if(bodyResults.get(i).getResult()==true){
							Passenger PASSenger = new Passenger(bagScan.getPassengerID());
							PASSenger.setLegality(true);
							//Tell queue the result
							ActorRef queue = akka.actor.Actors.actorOf(Queue.class);
							queue.start();
							queue.tell(PASSenger);
						}
					}
					else{//do nothing
						System.out.println("Passenger: "+bagScan.getPassengerID()+" bag scanned, but body not.");
					}
				}
			}
			else{System.out.println("No body scan has been processed.");}
		}
			
		else if(message instanceof PassengerBodyChecked){
			PassengerBodyChecked bodyScan = (PassengerBodyChecked)message;
			bodyResults.add(bodyScan); //add the body scan result to an array list
			System.out.println("Passenger: "+bodyScan.getPassengerID()+" body was scanned.");
			if(bodyScan.getResult()==false){ // illegal passenger
				Passenger PASSenger = new Passenger(bodyScan.getPassengerID());
				PASSenger.setLegality(false);
				//Tell queue the result
				ActorRef queue = akka.actor.Actors.actorOf(Queue.class);
				queue.start();
				queue.tell(PASSenger);
			}
			else if(bagResults.size() > 1){ //at least 1 bag has been scanned
				for(int i=0; i<bagResults.size(); i++){
					if(bagResults.get(i).getPassengerID() == bodyScan.getPassengerID()){ //both results are present
						if(bodyResults.get(i).getResult()==true){
							Passenger PASSenger = new Passenger(bodyScan.getPassengerID());
							PASSenger.setLegality(true);
							//Tell queue the result
							ActorRef queue = akka.actor.Actors.actorOf(Queue.class);
							queue.start();
							queue.tell(PASSenger);
						}
					}
					else{//do nothing
						System.out.println("Passenger: "+bodyScan.getPassengerID()+" body scanned, but bag not.");
					}
				}
			}
			else{System.out.println("No bags to process yet");}
		}
	
	}

}
