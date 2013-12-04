import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

public class Line extends UntypedActor{
	private final ActorRef queue;
	private final ActorRef bag;
	private final ActorRef body;
	private final ActorRef security;
	private final int lineNum;
	
	public Line(final int lineNum, final ActorRef jail){
		this.lineNum = lineNum;
		
		security = Actors.actorOf(new UntypedActorFactory(){
			@Override
			public Actor create(){
				return new Security(lineNum, jail);
			}
		});
		bag = Actors.actorOf(new UntypedActorFactory(){
			@Override
			public Actor create(){
				return new BagScanner(lineNum, security);
			}
		});
		body = Actors.actorOf(new UntypedActorFactory(){
			@Override
			public Actor create(){
				return new BodyScanner(lineNum, security);
			}
		});
		queue = Actors.actorOf(new UntypedActorFactory(){
			@Override
			public Actor create(){
				return new Queue(lineNum, bag, body, jail);
			}
		});
		
		security.start();
		bag.start();
		body.start();
		queue.start();
		
		security.tell(new QueueMessage(queue));
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof PassengerQueued){
			System.out.println("Line actor: "+lineNum+" received passenger. Message Received.");
			queue.tell(message);
		}
	}
}
