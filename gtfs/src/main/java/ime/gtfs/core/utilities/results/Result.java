package ime.gtfs.core.utilities.results;

public class Result {
	
	private String message;
	private boolean success;
	

	public Result(boolean success) {
		super();
		this.success = success;
	}


	public Result(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	public boolean isSuccess() {
		return this.success;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	
	

}
