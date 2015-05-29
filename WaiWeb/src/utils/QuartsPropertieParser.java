package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.naming.NamingException;

import jndi.JndiFactory;

public class QuartsPropertieParser {
	
	JndiFactory jndiFactory;
	
	public QuartsPropertieParser(String pathToJobInit){
		
		jndiFactory = JndiFactory.getInstance();
		
		
		String configDirecttory = null;
		try {
			configDirecttory = jndiFactory.getConfigDirectoryPath();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filename = "quartz.properties";
		
        BufferedWriter writer = null;
        
        try {
            //create a temporary file
            File quartsprop = new File(configDirecttory+"/"+filename);
            

            // This will output the full path where the file will be written to...
            System.out.println(quartsprop.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(quartsprop));
            writer.write("org.quartz.scheduler.instanceName = waiHilfQuartzScheduler");
            writer.write("\n");
            writer.write("org.quartz.scheduler.instanceId = AUTO");
            writer.write("\n");
            writer.write(" org.quartz.scheduler.skipUpdateCheck = true");
            writer.write("\n");
            writer.write("org.quartz.threadPool.threadCount = 2");
            writer.write("\n");
            writer.write("org.quartz.threadPool.threadPriority = 5");
            writer.write("\n");
            writer.write("org.quartz.jobStore.misfireThreshold = 60000");
            writer.write("\n");
            writer.write("org.quartz.plugin.triggHistory.class = org.quartz.plugins.history.LoggingJobHistoryPlugin");
            writer.write("\n");
            writer.write("org.quartz.plugin.jobInitializer.class = org.quartz.plugins.xml.XMLSchedulingDataProcessorPlugin");
            writer.write("\n");
            writer.write("org.quartz.threadPool.class=org.quartz.simpl.SimpleThreadPool");
            writer.write("\n");
            writer.write("org.quartz.plugin.jobInitializer.failOnFileNotFound = true");
            writer.write("\n");
            writer.write("org.quartz.plugin.jobInitializer.scanInterval = 0");
            writer.write("\n");
            writer.write("org.quartz.plugin.jobInitializer.wrapInUserTransaction = false");
            writer.write("\n");
            writer.write("org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore");
            writer.write("\n");
            writer.write("org.quartz.plugin.jobInitializer.wrapInUserTransaction=false");
    		writer.write("\n");
            writer.write("org.quartz.plugin.jobInitializer.fileNames = "+pathToJobInit);

            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }

		
		
	}


}
