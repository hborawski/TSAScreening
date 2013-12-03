import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import java.util.Random;


public class BagScanner extends UntypedActor{
	private Random ran = new Random();
	private int ID;
	private int BagID;
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof CheckBag){
			BagID = ((CheckBag)message).getBagID();
			PassengerBagChecked bagMessage;
			if(ran.nextInt(10) >= 2){
				bagMessage = new PassengerBagChecked(BagID, true);
				System.out.println("Bag Scanner: " + ID + " passed bagID " + BagID);
			}else{
				bagMessage = new PassengerBagChecked(BagID, false);
				System.out.println("Bag Scanner: " + ID + " failed bagID " + BagID);
			}
			
			//Tell security the result
			ActorRef security = akka.actor.Actors.actorOf(Security.class);
			
			security.tell(bagMessage);
			
		}
		
	}

}
