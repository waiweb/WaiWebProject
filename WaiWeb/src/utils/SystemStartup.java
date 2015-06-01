package utils;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jndi.JndiFactory;


public final class SystemStartup implements ServletContextListener {

	private static Logger jLog = Logger.getLogger(SystemStartup.class);

	public void contextInitialized(ServletContextEvent event) {
		JndiFactory jndiFactory = JndiFactory.getInstance();

		System.out.println("enter ContextInitialized");
		
		   Webcam.setDriver(new IpCamDriver());

		   

		//Relative paths 
		String projectPath = event.getServletContext().getRealPath("/WEB-INF");
	    String camImagesPath = event.getServletContext().getRealPath("/camimages");
	    String configPath = event.getServletContext().getRealPath("/WEB-INF/config"); 
	    
	    
	    jndiFactory.setCamImagePath(camImagesPath);
	    jndiFactory.setConfigPath(configPath);
	    
	    System.out.println("camImagesPath: "+ camImagesPath);
	    System.out.println("configPath: "+ configPath);


	    
	    //Einrichtung Logfile
	    String log4jprop = "/log4j.properties";
	    
	    
		//Setzen von properties fuerºr die config-files, dass auch dort der Pfad dynamisch eingetragen wird.
		
		try {
			System.setProperty("logFilePath", jndiFactory.getLogFilePath());
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	

		//In dynamisch ge√§ndert.
		try {
			
			// Initialize Logging
			File logConfigFile = new File(configPath + log4jprop);
			

			if (!logConfigFile.exists()) {
				System.out.println("ERROR: Logging configuration file not found: "+configPath + log4jprop);
			} else {
				String logpath = logConfigFile.getPath();
				PropertyConfigurator.configure(logpath);
			}
			
			
			
			jLog.info("ContextInitialized");

		} catch (Exception e) {
			e.printStackTrace();
		}
		



		
		
		
	}


	public void contextDestroyed(ServletContextEvent event) {
		jLog.info("ContextDestroyed");
	}

}
