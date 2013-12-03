import akka.actor.UntypedActor;
import java.util.Random;


public class BagScanner extends UntypedActor{
	private Random ran = new Random();
	private int ID;
	private int BagID;
	private PassengerBagCheck;
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof CheckBag){
			BagID = message.getBagID();
			if(ran.nextInt(10) >= 2){
				PassengerBagCheck = new PassengerBagCheck(BagID, true);
				System.out.println("Bag Scanner: " + ID + " passed bagID " + BagID);
			}else{
				PassengerBagCheck = new PassengerBagCheck(BagID, false);
				System.out.println("Bag Scanner: " + ID + " failed bagID " + BagID);
			}
		}
		
	}

}
