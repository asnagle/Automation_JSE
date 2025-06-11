package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.log;

public class LoginTest extends BaseTest {

	@Test(priority=1)
	public void validLogin() {
		log.info("Starting to test Login with Valid Credentials...");
		test = ExtentReportManager.createTest("Login with valid credentials Test");
		
		test.info("Navigating to URL");		
		LoginPage loginPage = new LoginPage(driver);
		
		
		log.info("Providing Credentials...");
		test.info("Providing Credentials...");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
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

	@Test(priority=2)
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
		System.out.println("Failed Login error: "+actualErrorText);
		Assert.assertEquals(actualErrorText, expectedErrorText, "Test Failed");
		test.pass("Login Failed as expected");
	}
}
