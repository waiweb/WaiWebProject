package exception;

public class CamNotFoundExecption extends Exception {
	
	public CamNotFoundExecption(long id) {
		super("User "+id+" konnte nicht gefunden werden!");
	}
	
	public CamNotFoundExecption(String name) {
		super("User "+name+" konnte nicht gefunden werden!");
	}


}
