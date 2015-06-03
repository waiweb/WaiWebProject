package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jobs.ImagCaptureJob;

/**
 * Servlet implementation class Testservlet
 */
@WebServlet("/Testservlet")
public class Testservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ImagCaptureJob imajob;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testservlet() {
        super();
        // TODO Auto-generated constructor stub
        
        
       // Webcam.setDriver(new IpCamDriver());
        
      
        
        
        /*
		try {
			IpCamDeviceRegistry.register("Lignano", "http://www.mpc-it.de/webcam/big.jpg", IpCamMode.PUSH);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedImage buf =Webcam.getWebcams().get(0).getImage();
		*/
		
        /*
        CamDaoImpl camdao = new CamDaoImpl();
        
       
		ArrayList<Cam> camList = (ArrayList<Cam>) camdao.getAllCams();
		
		
		for(Cam cam : camList){
			
			if(Tool_ImageProcessing.isImage(cam.getUrl())){
				System.out.println("Cam: "+cam.getCamname()+ " is image");
				imageCapture(cam);

			}
			else if(Tool_ImageProcessing.isStream(cam.getUrl())){
				System.out.println("Cam: "+cam.getCamname() + " is stream!!!");
				streamCapture(cam);

			}
			
		}*/
			/*
		    String name = "name";
	        String url = "http://ce3014.myfoscam.org:20054/videostream.cgi";
	        IpCamMode mode = IpCamMode.PUSH;

	        try {
				IpCamDeviceRegistry.register(name, url, mode);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	        /*

		if( buf != null)
		System.out.println("image != null");
		else
			System.out.println("image = null");
		
		
		System.out.println("Size WebcamlistList: "+Webcam.getWebcams().size());

		*/
		/*
		WebcamUtils.capture(Webcam.getWebcams().get(0), "D:/xxx/test2", ImageUtils.FORMAT_JPG);
		
		byte[] bytes = WebcamUtils.getImageBytes(Webcam.getWebcams().get(0), "jpg");
		System.out.println("Bytes length: " + bytes.length);
*/
    }
       
        

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Enter doGet of TestServlet");
		
 
		
		

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
