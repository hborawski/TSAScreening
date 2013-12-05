import akka.actor.ActorRef;

/**
 * Wrapper class that wraps a queue into message
 * @author Anshul
 */
public class QueueMessage {
	private ActorRef queue;
	
	/**
	 * Public constructor for the class
	 * @param queue
	 */
	public QueueMessage(ActorRef queue){
		this.queue = queue;
	}
	
	/**
	 * Getter method to get the queue
	 * @return queue
	 */
	public ActorRef getQueue(){
		return queue;
	}
}
