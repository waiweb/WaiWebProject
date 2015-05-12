package exception;

public class ImageItemNotFoundExecption extends Exception {
	
	public ImageItemNotFoundExecption(long id) {
		super("ImageItem "+id+" konnte nicht gefunden werden!");
	}
	
	public ImageItemNotFoundExecption(String name) {
		super("ImageItem "+name+" konnte nicht gefunden werden!");
	}


}
