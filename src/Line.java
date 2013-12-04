import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class Line extends UntypedActor{
	private ActorRef queue;
	private ActorRef bag;
	private ActorRef body;
	private ActorRef security;
	
	public Line(int lineNum, ActorRef jail){
		security = (ActorRef) new Security(jail);
		bag = (ActorRef) new BagScanner(security);
		body = (ActorRef) new BodyScanner(security);
		queue = (ActorRef) new Queue(bag, body, jail);
		
		security.tell(new QueueMessage(queue));
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof PassengerQueued){
			queue.tell(message);
		}
	}
}
