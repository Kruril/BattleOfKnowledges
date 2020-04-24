package exceptions;

public class UserUnknown extends Exception{
	
	public UserUnknown() {
		super("unknown user");
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
