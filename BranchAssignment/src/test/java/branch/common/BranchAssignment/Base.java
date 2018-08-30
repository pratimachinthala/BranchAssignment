package branch.common.BranchAssignment;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;



public class Base {
	public static  ReadProperties prop;
	public WebDriver driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	
	public void launchBrowser(){
		
		System.setProperty("webdriver.chrome.driver", "D:/WorkSpace/SampleTest/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		/*String breakPoint = "4";//prop.getProperty("BreakPoint");
		System.setProperty("webdriver.chrome.driver", ReadProperties.projectLocation+"/Drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		switch (breakPoint) {
		case "1":
			options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0.1; Nexus 7 Build/MOB30X) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/%s Mobile Safari/537.36");
			break;
		case "2":
			options.addArguments("--user-agent=Mozilla/5.0 (iPad; CPU OS 10_1_1 like Mac OS X) AppleWebKit/602.2.14 (KHTML, like Gecko) Version/10.0 Mobile/14B100 Safari/602.1");
		}
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
*/	}
	
	
	@AfterMethod
	public void fetchMostRecentTestResult(ITestResult result) {

	    int status = result.getStatus();

	    switch (status) {
	        case ITestResult.SUCCESS:
	            System.out.println("Test Passed");
	            break;
	        case ITestResult.FAILURE:
	        	System.out.println("Test Failed");
	            break;
	        case ITestResult.SKIP:
	        	System.out.println("Test Skipped");
	            break;
	        default:
	            throw new RuntimeException("Invalid status");
	    }
	}

}
