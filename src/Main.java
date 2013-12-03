import akka.actor.ActorRef;

public class Main {

	public static void main(String args[]){
		
		PassengerBodyChecked body1 = new PassengerBodyChecked(1, true);
		PassengerBodyChecked body2 = new PassengerBodyChecked(2, false);
		PassengerBodyChecked body3 = new PassengerBodyChecked(3, true);
		
		PassengerBagChecked bag1 = new PassengerBagChecked(1, true);
		PassengerBagChecked bag2 = new PassengerBagChecked(2, true);
		PassengerBagChecked bag3 = new PassengerBagChecked(3, true);
		
		ActorRef queueActor = akka.actor.Actors.actorOf(Queue.class);
		queueActor.start();
		
		ActorRef securityActor = akka.actor.Actors.actorOf(Security.class);
		securityActor.start();
		
		System.out.println("Starting security...");
		securityActor.tell(body1);
		securityActor.tell(body2);
		securityActor.tell(bag1);
		
		securityActor.tell(body3);
		securityActor.tell(bag2);
		securityActor.tell(bag3);
	}
}
