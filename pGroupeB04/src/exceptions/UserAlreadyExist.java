package exceptions;

public class UserAlreadyExist extends Exception	{
	
	public UserAlreadyExist() {
		super("User already exists");	
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
