
/**
 * Passenger class that contains the passenger id
 * @author Anshul
 *
 */
public class Passenger {

	int id;
	
	/**
	 * Constructor to set the id for a new passenger
	 * @param id
	 */
	public Passenger(int id){
		this.id = id;
	}
	
	/**
	 * Getter method to get the passenger's id.
	 * @return
	 */
	public int getId(){
		return id;
	}
}
