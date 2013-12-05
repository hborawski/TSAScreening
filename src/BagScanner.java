import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import java.util.Random;

/**
 * Bag scanner Actor class that represents a scanned bag
 * @author Anshul
 *
 */
public class BagScanner extends UntypedActor{
	private Random ran = new Random();
	private int ID;
	private int BagID;
	private ActorRef security;
	
	/**
	 * Constructor for the bag scanner class
	 * @param ID bag id
	 * @param security reference to what security gate it leads to
	 */
	public BagScanner(int ID, ActorRef security){
		super();
		this.ID = ID;
		this.security = security;
	}
	
	/**
	 * Method that know what to do when a bag is received at the scanner
	 */
	public void onReceive(Object message) throws Exception {
		if(message instanceof CheckBag){
			BagID = ((CheckBag)message).getBagID();
			PassengerBagChecked bagMessage;
			if(ran.nextInt(10) >= 2){
				bagMessage = new PassengerBagChecked(BagID, true);
				System.out.println("Bag Scanner actor: Line: " + ID + ". PASSED bagID " + BagID);
			}else{
				bagMessage = new PassengerBagChecked(BagID, false);
				System.out.println("Bag Scanner actor: Line: " + ID + ". FAILED bagID " + BagID);
			}
			
			security.tell(bagMessage);
			
		}
		
	}

}
