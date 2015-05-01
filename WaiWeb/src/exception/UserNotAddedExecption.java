package exception;

public class UserNotAddedExecption  extends RuntimeException {
	
	public UserNotAddedExecption() {
		super("User konnte nicht hinzugefügt werden!");
	}

}
