package branch.common.BranchAssignment;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ReadProperties {

	HashMap<String, String> props = new HashMap<String, String>();
	static Properties pro = new Properties();
	FileInputStream fis;
	public static String BreakPoint = null;
	public static String browser = null;
	public static String DEVICE_NAME = null;
	public static String PLATFORM_VERSION = null;
	public static String targetDevice = null;
	public static String projectLocation = "src/test/resources";


	public static String getProperty(String key) {
		try {
			System.out.println(key.toUpperCase()+" : "+pro.getProperty(key));
			return pro.getProperty(key);
		} catch (Exception e) {
			return "";
		}
	}
	
	
	
	public  ReadProperties() throws Throwable  {
		fis = new FileInputStream(projectLocation + "/Config.properties");
		pro.load(fis);

		// reading properties form .properties file
		System.out.println("==== Reading properties from .properties file ====");

		BreakPoint = pro.getProperty("breakPoint");
		DEVICE_NAME = pro.getProperty("DEVICE_NAME");
		PLATFORM_VERSION = pro.getProperty("PLATFORM_VERSION");
		browser = pro.getProperty("browser");
		targetDevice = pro.getProperty("targetDevice");

		
	}

}
