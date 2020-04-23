package exceptions;

public class UserUnknown extends Exception{
	
	public UserUnknown() {
		super("User not known");
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
