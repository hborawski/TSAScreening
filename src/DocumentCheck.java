import java.util.ArrayList;
import java.util.Random;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

/**
 * Document check actor class that handles the creation of lines and putting passengers into lines.
 * Passengers are randomly put into lines.
 * @author Anshul
 */
public class DocumentCheck extends UntypedActor{
	private Random random = new Random();
	private int passengerID;
	static int checkLinesMade = 0;
	private static ArrayList<ActorRef> lines = new ArrayList<ActorRef>();
	int l;
	
	/**
	 * Public constructor to get the number of lines
	 * @param totalLines Total line at TSA screening
	 */
	public DocumentCheck(int totalLines){
		l=totalLines;
	}
	
	/**
	 * Method to create all the lines
	 * @param totalLines number of line to be made
	 */
	public void makeLines(int totalLines){
		final ActorRef jailActor = akka.actor.Actors.actorOf(Jail.class);
		jailActor.start();
		
		for( int line=0; line<totalLines; line++){
			final int l = line;
			final ActorRef lineActor = Actors.actorOf(new UntypedActorFactory(){
				public Actor create(){
					return new Line(l, jailActor);
				}
			});
			lineActor.start();
			lines.add(lineActor);
		} // end of loop
		checkLinesMade = 1;
	}
	
	/**
	 * Method that handles the passing of passengers to lines and their rejection from the TSA
	 * screening site if they fail initial document check.
	 */
	public void onReceive(Object message) throws Exception {
		if(message instanceof PassengerEnters){
			if(checkLinesMade==0){
				makeLines(l); 
				System.out.println("Lines made: "+l);
			}
			passengerID = ((PassengerEnters) message).getPassengerID();
			PassengerQueued sendMessage;
			if(random.nextInt(10) >= 2){
				sendMessage = new PassengerQueued(passengerID);
				System.out.println("Document Check actor: PassengerID " + passengerID+" PASSED. Message Received.");
				
				int selectLine = new Random().nextInt(lines.size());
				//tell the Line actor about passenger
				lines.get(selectLine).tell(sendMessage);
				
			}
			else{
				sendMessage = new PassengerQueued(passengerID);
				System.out.println("Document Check actor: PassengerID " + passengerID+" FAILED. Message Received.");
			}
			
			
		}
		
	}

}
