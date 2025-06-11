package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.log;

public class LoginPage {
	
	private WebDriver driver;
	
//	======= Using PageFactory of Selenium ======
	@FindBy(id="Email")
	WebElement usernameTextBox;
	
	@FindBy(id="Password")
	WebElement passwordTextBox;
	
	@FindBy(xpath="/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button")
	WebElement loginButton;
	
	@FindBy(xpath="/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[1]")
	WebElement loginError;
	
//	======= Normal method of declaration of locators =====
//	private By usernameTextBox = By.id("Email");
//	private By passwordTextBox = By.id("Password");
//	private By loginButton = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button");
//	private By loginError = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[1]");	  
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		log.info(username);
//		driver.findElement(usernameTextBox).clear();
//		driver.findElement(usernameTextBox).sendKeys(username);
		usernameTextBox.clear();
		usernameTextBox.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		log.info("Entering Password");
//		driver.findElement(passwordTextBox).clear();
//		driver.findElement(passwordTextBox).sendKeys(password);
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
	}
	
	public void clickLogin() {
		log.info("Submitting Credentials by clicking Login Button...");
//		driver.findElement(loginButton).click();
		loginButton.click();
	}
		
	// Method to return login error message
    public String loginStatus() {
    	log.info("Getting Login Status...");
//        WebElement errorMessage = driver.findElement(loginError);
//        return errorMessage.getText(); // Returning the error message text
    	return loginError.getText();
    }

}
