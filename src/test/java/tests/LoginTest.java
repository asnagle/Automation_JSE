package tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.Parameters; //Needed if we want to pass parameters from testng.xml
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.log;

public class LoginTest extends BaseTest {

	@DataProvider(name = "LoginData")
	public Object[][] getloginData() throws IOException {

		String filePath = System.getProperty("user.dir") + "/testData/testdata.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2];

		for (int i = 1; i < rowCount; i++) {

			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Get UserName
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Get Password
		}
		ExcelUtils.closeExcel();
		return data;
	}

//	============= Example of how to get data from DataProvider =================
	@DataProvider(name = "LoginData2")
	public Object[][] getData() {
		return new Object[][] { { "duser1@test.com", "dpass1" }, { "duser2@test.com", "dpass2" },
				{ "duser3@test.com", "dpass3" }, { "admin@yourstore.com", "admin" } };
	}
//	================ Following Code uses hardcoded values for username & password =========
//	@Test(priority=1)
//	public void validLogin() {
//		log.info("Starting to test Login with Valid Credentials...");
//		test = ExtentReportManager.createTest("Login with valid credentials Test");
//		
//		test.info("Navigating to URL");		
//		LoginPage loginPage = new LoginPage(driver);
//		
//		
//		log.info("Providing Credentials...");
//		test.info("Providing Credentials...");
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("admin");
//		test.info("Clicking on Login Button");
//		loginPage.clickLogin();
//
//		System.out.println("Title is: " + driver.getTitle());
//		log.info("Verified Login Page...");
//		test.info("Verifying Page Title");
//		String pageTitle = driver.getTitle();
//		if (pageTitle.equalsIgnoreCase("Just a moment...")) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			log.info("Test completed Successfully ");
//			test.pass("Login Successful");
//		} else if (pageTitle.equalsIgnoreCase("Dashboard / nopCommerce administration")) {
//			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			log.info("Test completed Successfully ");
//			test.pass("Login Successful");
//		}
//
//	}

//	========================= Following Code takes input from excel file for getting username & password =======

	@Test(priority = 1, dataProvider = "LoginData") // Uncomment to use data from
	// data provider LoginData
//	@Test(priority = 1, dataProvider = "LoginData2") //Comment this if you don't want data from data provider LoginData2 and uncomment LoginData statement

//	========== Getting login data from testng.xml make sure testng.xml has parameters added
//	if you don't want to use comment out the paramenters in testng.xml and also comment @Parameter here ===========
//	@Test(priority = 1)
//	@Parameters({ "username", "password" })
	public void validLogin(String username, String password) {
		log.info("Starting to test Login with Valid Credentials...");
		test = ExtentReportManager.createTest("Login with valid credentials Test");

		test.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);

		log.info("Providing Credentials...");
		test.info("Providing Credentials...");
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		test.info("Clicking on Login Button");
		loginPage.clickLogin();

		System.out.println("Title is: " + driver.getTitle());
		log.info("Verified Login Page...");
		test.info("Verifying Page Title");
		String pageTitle = driver.getTitle();
		if (pageTitle.equalsIgnoreCase("Just a moment...")) {
			Assert.assertEquals(driver.getTitle(), "Just a moment...");
			log.info("Test completed Successfully ");
			test.pass("Login Successful");
		} else if (pageTitle.equalsIgnoreCase("Dashboard / nopCommerce administration")) {
			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
			log.info("Test completed Successfully ");
			test.pass("Login Successful");
		}

	}

//	================ Following Code uses hardcoded values for username & password =========
	@Test(priority = 2)
	public void invalidLogin() {
		log.info("Starting to test Login with Invalid Credentials...");
		test = ExtentReportManager.createTest("Login with Invalid credentials Test");
		test.info("Navigating to URL");

		LoginPage loginPage = new LoginPage(driver);

		log.info("Providing Invalid Credentials...");
		test.info("Providing Credentials...");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin123");
		loginPage.clickLogin();
		loginPage.loginStatus();
		// Get the returned error message
		String actualErrorText = loginPage.loginStatus().trim().replaceAll("\\s+", " ");
		// Expected error message
		String expectedErrorText = "Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect"
				.trim().replaceAll("\\s+", " ");
		System.out.println("Title is: " + driver.getTitle());
		log.info("Printing Test result...");
		test.info("Getting Login Page Error");
		System.out.println("Failed Login error: " + actualErrorText);
		Assert.assertEquals(actualErrorText, expectedErrorText, "Test Failed");
		test.pass("Login Failed as expected");
	}
}
