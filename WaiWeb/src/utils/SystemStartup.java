package utils;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jndi.JndiFactory;


public final class SystemStartup implements ServletContextListener {

	private static Logger jLog = Logger.getLogger(SystemStartup.class);

	public void contextInitialized(ServletContextEvent event) {
		JndiFactory jndiFactory = JndiFactory.getInstance();

		System.out.println("enter ContextInitialized");

		//Relative paths (unbenutzt zur zeit)
	    String dynamicBasepath = event.getServletContext().getRealPath("/WEB-INF");
	    String relativeConfigPath = event.getServletContext().getRealPath("/WEB-INF/config");    
	    jndiFactory.setDynamicProjectpath(dynamicBasepath);
	    
	    
	    
	    //Statiche pfade	    
	    String projectpath = null;
	    String log4jprop = "/log4j.properties";
	    String projectpathAndConfigPath = null;
	    
		//Setzen von properties für die config-files		
		try {
			System.setProperty("logFilePath", jndiFactory.getLogFilePath());
			System.setProperty("configFileDirectoryPath", jndiFactory.getConfigDirectoryPath());
	
			//Statische pfade:
			projectpath = jndiFactory.getProjectPath();
			projectpathAndConfigPath = jndiFactory.getConfigDirectoryPath();
		
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		//In dynamisch geändert.
		try {
			
			// Initialize Logging
			File logConfigFile = new File(projectpathAndConfigPath + log4jprop);
			

			if (!logConfigFile.exists()) {
				System.out.println("ERROR: Logging configuration file not found: "+projectpathAndConfigPath + log4jprop);
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
