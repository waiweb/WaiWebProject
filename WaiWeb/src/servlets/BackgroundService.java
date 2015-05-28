package servlets;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jobs.ImagCaptureJob;
import utils.SystemStartup;
import exception.UserNotFoundExecption;

/**
 * Servlet implementation class BackgroundService
 */
@WebServlet("/BackgroundService")
public class BackgroundService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() 
	{
	 
	       
	        
	        ImagCaptureJob imageCaptureJob = new ImagCaptureJob(getImageBasePath());
	        
	  


	}
	
	public String getImageBasePath(){
	       String basePath = getServletContext().getRealPath("/WEB-INF/camimages");
	       return basePath;
		
	}
	
	
	public String getConfigPath(){
	       String basePath = getServletContext().getRealPath("/WEB-INF/config");
	       return basePath;
		
	}


    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
