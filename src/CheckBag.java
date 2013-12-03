
public class CheckBag {
	private int bagID;
	private boolean result;
	
	public CheckBag(int bagID, boolean result){
		this.bagID = bagID;
		this.result = result;
	}
	
	public int getBagID(){
		return bagID;
	}
	
	public boolean getResult(){
		return result;
	}
}
