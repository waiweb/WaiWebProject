package exception;

public class ImageNotAddedExecption  extends RuntimeException {
	
	public ImageNotAddedExecption(String imagename) {
		super(imagename+ " konnte nicht hinzugefügt werden!");
	}
	
	public ImageNotAddedExecption() {
		super("Image konnte nicht hinzugefügt werden!");
	}


}
