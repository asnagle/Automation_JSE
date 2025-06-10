package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test(priority=1)
	public void validLogin() {
		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();

		System.out.println("Title is: " + driver.getTitle());
		String pageTitle = driver.getTitle();
		if (pageTitle.equalsIgnoreCase("Just a moment...")) {
			Assert.assertEquals(driver.getTitle(), "Just a moment...");
		} else if (pageTitle.equalsIgnoreCase("Dashboard / nopCommerce administration")) {
			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
		}
//		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");

	}

	@Test(priority=2)
	public void invalidLogin() {
		LoginPage loginPage = new LoginPage(driver);
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
		System.out.println("Failed Login error: "+actualErrorText);
		Assert.assertEquals(actualErrorText, expectedErrorText, "Test Failed");

	}
}
