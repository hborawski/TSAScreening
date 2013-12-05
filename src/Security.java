import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import java.util.ArrayList;

/**
 * Security actor class that process all of scanned bags and bodies.
 * It sends Queue actor messages about passed and failed scans.
 * @author Anshul
 */
public class Security extends UntypedActor{
	
	private ArrayList<PassengerBagChecked> bagResults = new ArrayList<PassengerBagChecked>();
	private ArrayList<PassengerBodyChecked> bodyResults = new ArrayList<PassengerBodyChecked>();
	
	private ActorRef queue;
	private ActorRef jail;
	private int ID;
	
	/**
	 * Public constructor for the Security class
	 * @param ID line id
	 * @param jail Jail actor reference
	 */
	public Security(int ID,ActorRef jail){
		super();
		this.jail = jail;
		this.ID = ID;
	}
	
	/**
	 * onReceive method that knows what to do when scanned bag or scanned body messages are received
	 */
	public void onReceive(Object message) throws Exception{
		if(message instanceof PassengerBagChecked){
			PassengerBagChecked bagScan = (PassengerBagChecked)message;
			bagResults.add(bagScan); //add the bag scan result to an array list
			System.out.println("Security actor: Line: "+ID+". PassengerID: "+bagScan.getPassengerID()+" bag scanned.");
			if(bagScan.getResult()==false){ // illegal passenger
				//bagResults.remove(bagScan);
				Passenger PASSenger = new Passenger(bagScan.getPassengerID());
				PASSenger.setLegality(false);
				//Tell queue the result
				queue.tell(PASSenger);
			}
			else if(bodyResults.size() >= 1){ //at least 1 body has been scanned
				for(int i=0; i<bodyResults.size(); i++){
					if(bodyResults.get(i).getPassengerID() == bagScan.getPassengerID()){ //both results are present
						if(bodyResults.get(i).getResult()==true){
							Passenger PASSenger = new Passenger(bagScan.getPassengerID());
							PASSenger.setLegality(true);
							//Tell queue the result
							queue.tell(PASSenger);
						}
					}
					else{//do nothing
						//System.out.println("Security actor: "+bagScan.getPassengerID()+" bag scanned, but body not.");
					}
				}
			}
			//else{System.out.println("No body processed yet.");}
		}
			
		else if(message instanceof PassengerBodyChecked){
			PassengerBodyChecked bodyScan = (PassengerBodyChecked)message;
			bodyResults.add(bodyScan); //add the body scan result to an array list
			System.out.println("Security actor: Line: "+ID+". PassengerID: "+bodyScan.getPassengerID()+" body scanned.");
			if(bodyScan.getResult()==false){ // illegal passenger
				//bodyResults.remove(bodyScan);
				Passenger PASSenger = new Passenger(bodyScan.getPassengerID());
				PASSenger.setLegality(false);
				//Tell queue the result
				queue.tell(PASSenger);
			}
			else if(bagResults.size() >= 1){ //at least 1 bag has been scanned
				for(int i=0; i<bagResults.size(); i++){
					if(bagResults.get(i).getPassengerID() == bodyScan.getPassengerID()){ //both results are present
						if(bodyResults.get(i).getResult()==true){
							
							Passenger PASSenger = new Passenger(bodyScan.getPassengerID());
							PASSenger.setLegality(true);
							//Tell queue the result
							queue.tell(PASSenger);
						}
					}
					else{//do nothing
						//System.out.println("Security actor: "+bodyScan.getPassengerID()+" body scanned, but bag not.");
					}
				}
			}
			//else{System.out.println("No bags processed yet.");}
		}else if(message instanceof QueueMessage){
			this.queue = ((QueueMessage)message).getQueue();
		}
	
	}

}
