package ime.gtfs.core.utilities.results;

public class SuccessResult extends Result {
	
	public SuccessResult() {
		super(true);
	}
	
	public SuccessResult(String message) {
		super(true, message);
		
	}
	
	public boolean getResultType() {
		return true;
	}

}
