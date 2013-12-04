import akka.actor.ActorRef;


public class QueueMessage {
	private ActorRef queue;
	
	public QueueMessage(ActorRef queue){
		this.queue = queue;
	}
	
	public ActorRef getQueue(){
		return queue;
	}
}
