package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private WebDriver driver;
	
	private By usernameTextBox = By.id("Email");
	private By passwordTextBox = By.id("Password");
	private By loginButton = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button");
	private By loginError = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[1]");
//	WebElement errorMessage = driver.findElement(By.className("message-error"));
//	String lgnError = errorMessage.getAttribute("innerText");
	
	
//	public By loginError = lgnError;
	  
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;		
	}
	
	public void enterUsername(String username) {
		driver.findElement(usernameTextBox).clear();
		driver.findElement(usernameTextBox).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys(password);
	}
	
	public void clickLogin() {
		driver.findElement(loginButton).click();	
	}
	
//	public void loginStatus() {
//		driver.findElement(By.className("message-error"));;
//		driver.findElement(loginError);
//		WebElement errorMessage = driver.findElement(loginError);
//		String errorText = errorMessage.getText();
//		System.out.println("Login Error: " + errorText);			
//	}
	
	// Method to return login error message
    public String loginStatus() {
        WebElement errorMessage = driver.findElement(loginError);
        return errorMessage.getText(); // Returning the error message text
    }

}
