import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class Line extends UntypedActor{
	private ActorRef queue;
	private ActorRef bag;
	private ActorRef body;
	private ActorRef security;
	private int lineNum;
	
	public Line(int lineNum, ActorRef jail){
		this.lineNum = lineNum;
		security = (ActorRef) new Security(lineNum, jail);
		bag = (ActorRef) new BagScanner(lineNum, security);
		body = (ActorRef) new BodyScanner(lineNum, security);
		queue = (ActorRef) new Queue(lineNum, bag, body, jail);
		
		security.start();
		bag.start();
		body.start();
		queue.start();
		
		security.tell(new QueueMessage(queue));
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof PassengerQueued){
			System.out.println("Line actor: "+lineNum+"received passenger. Message Received.");
			queue.tell(message);
		}
	}
}
