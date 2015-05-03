package exception;

public class UserNotFoundExecption extends Exception {
	
	public UserNotFoundExecption(long id) {
		super("User "+id+" konnte nicht gefunden werden!");
	}
	
	public UserNotFoundExecption(String name) {
		super("User "+name+" konnte nicht gefunden werden!");
	}


}
