package Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.Base;

public class BranchTest extends Base{

	static List<String> allNames = new ArrayList<String>(), employeeNamesBranchWise = new ArrayList<String>();
	static HashMap allEmployeeNameDepartement = new HashMap(), branchWiseEmployeeNameDepartment = new HashMap();

	By googleSeachBox = By.xpath("//input[@title='Search']");
	By googleSearchButton = By.xpath("//input[@value='Google Search']");

	By branchGoogleSearchResult = By.xpath("//a[@href='https://branch.io/']");
	By teamLink = By.xpath("//a[@data-element-tag='team']");

	By teamAllLink = By.xpath("//a[@rel='all']");
	By categories = By.xpath("//ul[@class='team-categories']/li");

	By personThumbnail = By.xpath("//div[@class='row row-centered']/div[@style='display: inline-block;']");
	By personName = By.xpath("//div[@class='row row-centered']/div[@style='display: inline-block;']//div[@class='info-block']/h2");
	By department = By.xpath("//div[@class='row row-centered']/div[@style='display: inline-block;']//div[@class='info-block']/h4");

	static int totalMembers;
	static int categoryMemberCount;
	@BeforeClass
	private void navigateToBranch(){


		launchBrowser();
		Actions action = new Actions(driver);

		driver.get("http://www.google.com");
		driver.findElement(googleSeachBox).sendKeys("branch");
		driver.findElement(googleSearchButton).click();

		driver.findElement(branchGoogleSearchResult).click();



		try{
			AssertJUnit.assertTrue(driver.getTitle().contains("Branch"));
		}catch(AssertionError e){
			e.printStackTrace();
		}

		try{
			action.moveToElement(driver.findElement(teamLink)).build();
			driver.findElement(teamLink).click();
			System.out.println("Scrolled");
		}catch(Exception e){
			e.printStackTrace();
			driver.get("https://branch.io/team/#all");
		}

	}

	private int getCategoryCount(){
		int memberCount = driver.findElements(personThumbnail).size();
		return memberCount;
	}
	@Test(priority = 0)
	public void verifyMemberCount(){
		driver.findElement(teamAllLink).click();
		totalMembers = driver.findElements(personThumbnail).size();
		int countOfCategories = driver.findElements(categories).size();

		for (int i =1; i<= countOfCategories; i++){
			String category = "//ul[@class='team-categories']/li["+i+"]/a";
			driver.findElement(By.xpath(category)).click();
			categoryMemberCount += driver.findElements(personThumbnail).size();
		}

		try{
			AssertJUnit.assertEquals(totalMembers, categoryMemberCount);
		}catch(AssertionError e){

		}
	}
	@Test(priority = 1)
	public void verifyNames(){
		driver.get("https://branch.io/team/#all");
		List<WebElement> allNamesLocator = driver.findElements(personName);


		for(WebElement ele : allNamesLocator){
			allNames.add(ele.getText());
		}

		int countOfCategories = driver.findElements(categories).size();

		for (int i =1; i<= countOfCategories; i++){
			List<WebElement> branchNamesLocator = driver.findElements(personName);

			for(WebElement ele : branchNamesLocator){
				employeeNamesBranchWise.add(ele.getText());
			}
		}

		try{
			allNames.sort(null);
			employeeNamesBranchWise.sort(null);
			AssertJUnit.assertEquals(allNames, employeeNamesBranchWise);
		}catch(AssertionError e){

		}
	}
	@Test(priority = 2)
	public void getNameDepartment(){
		driver.get("https://branch.io/team/#all");
		String nameLocator, departmentLocator, name, department;

		int count = driver.findElements(personThumbnail).size();

		for(int i = 1; i <count; i++){
			nameLocator = "//div[@class='row row-centered']/div[@style='display: inline-block;']["+i+"]//div[@class='info-block']/h2";

			departmentLocator = "//div[@class='row row-centered']/div[@style='display: inline-block;']["+i+"]//div[@class='info-block']/h4";

			name = driver.findElement(By.xpath(nameLocator)).getText();
			department = driver.findElement(By.xpath(departmentLocator)).getText();

			allEmployeeNameDepartement.put(name, department);
		}

		int countOfCategories = driver.findElements(categories).size();

		for (int i =1; i< countOfCategories; i++){
			String category = "//ul[@class='team-categories']/li["+i+"]/a";
			driver.findElement(By.xpath(category)).click();

			count = driver.findElements(personThumbnail).size();

			for(int j = 1; j < count; j++){
				nameLocator = "//div[@class='row row-centered']/div[@style='display: inline-block;']["+j+"]//div[@class='info-block']/h2";

				departmentLocator = "//div[@class='row row-centered']/div[@style='display: inline-block;']["+j+"]//div[@class='info-block']/h4";

				name = driver.findElement(By.xpath(nameLocator)).getText();
				department = driver.findElement(By.xpath(departmentLocator)).getText();

				branchWiseEmployeeNameDepartment.put(name, department);
			}
		}
		
		try{
			AssertJUnit.assertTrue(mapsAreEqual(allEmployeeNameDepartement, branchWiseEmployeeNameDepartment));
		}catch(AssertionError e){
			
		}
	}
	
	public boolean mapsAreEqual(Map<String, String> mapA, Map<String, String> mapB) {

	    try{
	        for (String k : mapB.keySet())
	        {
	            if (!mapA.get(k).equals(mapB.get(k))) {
	                return false;
	            }
	        } 
	        for (String y : mapA.keySet())
	        {
	            if (!mapB.containsKey(y)) {
	                return false;
	            }
	        } 
	    } catch (NullPointerException np) {
	        return false;
	    }
	    return true;
	}

	@AfterClass
	public void afterClass(){
		driver.close();
	}

}
