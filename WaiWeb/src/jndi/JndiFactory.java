package jndi;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




import org.apache.log4j.Logger;

/**
 * DataSource is usually configured and managed by the application server instead of your application.
 * DataSource object is retrieved through a lookup operation using JNDI API and then used to create a
 *  Connection object.
 * @author ghaleon
 *
 */

public class JndiFactory {
	
	private boolean useDynamicBasePath = false;
	private static JndiFactory instance = new JndiFactory();
    private static Logger log = Logger.getLogger(JndiFactory.class);   
    private String dynamicProjectpath;
   
    protected JndiFactory() {
       // Exists only to defeat instantiation.
    }
    
    
    /** Holt aus der content.xml eine Variable als String
     * @param envName
     * @return String
     * @throws NamingException
     */
    
    /*
     * java:comp/env is the node in the JNDI tree where you can find properties for the current 
     * Java EE component (a webapp, or an EJB).
     */
    private String getEnvironmentAsString(String envName) throws NamingException {
    	String env = null;
        InitialContext ictx = new InitialContext();
        Context myenv = (Context) ictx.lookup("java:comp/env");
        try {
        	env = (String) myenv.lookup(envName);
        } catch (NamingException n) {
        	log.debug("String Environment '" + envName + "' is missing.");
        }
        return env;
    }
    

	/**
	 * Diese methode kann später ausgetauscht werden  gegen eine dynamische erkennung des Pfades.
	 * @return
	 * @throws NamingException
	 */
    public String getProjectPath() throws NamingException {

    	if(useDynamicBasePath)
    		return dynamicProjectpath;
    	else
    		return getEnvironmentAsString("projectPath");
    	
    }
    
    /**
     * Returns the path to the image directory
     * @return
     * @throws NamingException
     */
    public String getImageDirectoryPath() throws NamingException {

    	
    	return getProjectPath()+getEnvironmentAsString("relativeImagePath");
    	
    }
    
    
    public String getConfigDirectoryPath() throws NamingException {

    	
    	return getProjectPath()+getEnvironmentAsString("relativeConfigPath");
    	
    }
    
    
    public String getLogFilePath() throws NamingException {

    	
    	return getProjectPath()+getEnvironmentAsString("logsPath");
    	
    }
   
   
    


    /** Holt aus der cms.xml eine Variable als Integer
     * @param envName
     * @return Integer
     * @throws NamingException
     */
    private Integer getEnvirontmenAsInteger(String envName) throws NamingException {
    	Integer env = null;
        InitialContext ictx = new InitialContext();
        Context myenv = (Context) ictx.lookup("java:comp/env");
        try {
        	env = (Integer) myenv.lookup(envName);
        } catch (NamingException n) {
        	log.debug("Integer Environment '" + envName + "' is missing.");
        }
        return env;
    }
    
    /** Holt aus der cms.xml eine Variable als Boolean
     * @param envName
     * @return Boolean
     * @throws NamingException
     */
    public Boolean getEnvironmentAsBoolean(String envName) throws NamingException {
    	Boolean env = null;
        InitialContext ictx = new InitialContext();
        Context myenv = (Context) ictx.lookup("java:comp/env");
        try {
        	env = (Boolean) myenv.lookup(envName);
        } catch (NamingException n) {
        	log.debug("Boolean Environment '" + envName + "' is missing.");
        }
        return env;
    }
	
	

	public static JndiFactory getInstance() {	
		return instance;
	}
	
	public Connection getConnection(String Datasource) throws NamingException, SQLException {
		Context initContext = new InitialContext();

		Context envContext = (Context) initContext.lookup("java:/comp/env");

		if (envContext == null)
			throw new NamingException("InitialContext lookup wrong");

		DataSource ds = (DataSource) envContext.lookup(Datasource);
		
		if (ds == null)
			throw new NamingException("No Datasource");
		
		Connection conn = ds.getConnection();

		if (conn == null)
			throw new SQLException("No Connection found");

		return conn;
	}


	public String getDynamicProjectpath() {
		return dynamicProjectpath;
	}


	//Unglücklicherweise hängt die dynamische suche noch ein "/" am ende an. Das wird hier entfernt.
	public void setDynamicProjectpath(String dynamicProjectpath) {
		
		String temp = removeLastChar(dynamicProjectpath).replaceAll("\\\\", "/");

		this.dynamicProjectpath = temp;
		System.out.println("dynamic web path set to: "+temp);
	}
	
	private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
	
	
	
	
}

