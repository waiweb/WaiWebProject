package exception;

public class CamNotAddedExecption  extends RuntimeException {
	
	public CamNotAddedExecption(String camname) {
		super(camname+ " konnte nicht hinzugef�gt werden!");
	}

}
