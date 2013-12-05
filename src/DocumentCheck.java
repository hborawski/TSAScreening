import java.util.ArrayList;
import java.util.Random;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

public class DocumentCheck extends UntypedActor{
	private Random random = new Random();
	private int passengerID;
	static int checkLinesMade = 0, l;
	private static ArrayList<ActorRef> lines = new ArrayList<ActorRef>();
	
	public void makeLines(int totalLines){
		final ActorRef jailActor = akka.actor.Actors.actorOf(Jail.class);
		jailActor.start();
		
		for( int line=0; line<totalLines; line++){
			final int l = line+1;
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
	public void onReceive(Object message) throws Exception {
		if(message instanceof PassengerEnters){
			if(checkLinesMade==0){
				l = 3; //Number of lines can be changed here!!!
				makeLines(l); 
				System.out.println("Lines made: "+l);
			}
			passengerID = ((PassengerEnters) message).getPassengerID();
			PassengerQueued sendMessage;
			if(random.nextInt(10) >= 2){
				sendMessage = new PassengerQueued(passengerID);
				System.out.println("Document Check actor: PassengerID " + passengerID+" PASSED.");
				
				int selectLine = new Random().nextInt(lines.size());
				//Queue the passenger since they pass
				//ActorRef line = akka.actor.Actors.actorOf(Line.class);
				lines.get(selectLine).tell(sendMessage);
				
			}
			else{
				sendMessage = new PassengerQueued(passengerID);
				System.out.println("Document Check actor: PassengerID " + passengerID+" FAILED.");
			}
			
			
		}
		
	}

}
