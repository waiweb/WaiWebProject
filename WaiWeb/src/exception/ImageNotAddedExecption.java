package exception;

public class ImageNotAddedExecption  extends RuntimeException {
	
	public ImageNotAddedExecption(String imagename) {
		super(imagename+ " konnte nicht hinzugef�gt werden!");
	}
	
	public ImageNotAddedExecption() {
		super("Image konnte nicht hinzugef�gt werden!");
	}


}
