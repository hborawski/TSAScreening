/**
 * Wrapper class to wrap a passenger into a jail passenger
 * @author Anshul
 */
public class JailPassenger {
	private Passenger p;
	
	/**
	 * Constructor to create a jail passenger
	 * @param p passenger object
	 */
	public JailPassenger(Passenger p){
		this.p = p;
	}
	
	/**
	 * Getter method to get the jail passenger id
	 * @return id
	 */
	public int getPassengerID(){
		return p.getId();
	}
}
