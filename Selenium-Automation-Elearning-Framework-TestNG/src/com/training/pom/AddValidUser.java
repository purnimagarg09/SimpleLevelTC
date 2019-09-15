package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AddValidUser {
	private WebDriver driver; 
	private JavascriptExecutor js;
	
	public AddValidUser(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		js=(JavascriptExecutor)driver;
	}
	
	//'Administration' link
	@FindBy(xpath="//*[@title = 'Administration']")
	private WebElement admin;
	
	//Add a user link
	@FindBy(xpath="//*[contains(text(),'Add a user')]")
	private WebElement addUser;
	
	@FindBy(id="firstname")
	private WebElement firstName;
	
	@FindBy(id="lastname")
	private WebElement lastName;
	
	@FindBy(id="email")
	private WebElement email;
	
	@FindBy(id="phone")
	private WebElement phone;
	
	@FindBy(id="username")
	private WebElement userName;
	
	@FindBy(xpath="//*[contains(text(),'Enter password')]/input")
	private WebElement pwdBtn;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="status_select")
	private WebElement profile;
	
	@FindBy(name="submit")
	private WebElement addBtn;
	
	@FindBy(xpath="//*[@class='alert alert-info']")
	private WebElement message;
	
	
	//Click on Administration tab
	public void clickAdministration(){
		this.admin.click();
	}
	
	//Click on Add a user link
	public void clickAddUser(){
		this.addUser.click();
		Assert.assertTrue(this.driver.getTitle().contains("Add a user"));
	}
	
	//Enter valid credential in First name textbox
	public void addFirstName(String name){
		this.firstName.sendKeys(name);
		Assert.assertEquals(this.firstName.getAttribute("value"),name);
	}
	
	//Enter valid credential in Last name textbox
	public void addLastName(String name){
		this.lastName.sendKeys(name);
		Assert.assertEquals(this.lastName.getAttribute("value"),name);
	}
	
	//Enter valid credential in e-mail textbox
	public void addEmail(String email){
		this.email.sendKeys(email);
		Assert.assertEquals(this.email.getAttribute("value"),email);
	}
	
	//Enter valid credential in phone textbox
	public void addPhone(long phoneNo){
		this.phone.sendKeys(Long.toString(phoneNo));
		Assert.assertEquals(this.phone.getAttribute("value"),Long.toString(phoneNo));
	}
	
	//Enter valid credential in Login textbox
	public void addLogin(String login){
		this.userName.sendKeys(login);
		Assert.assertEquals(this.userName.getAttribute("value"),login);
	}
	
	//Click on Enter password radio button
	public void clickPwdBtn(){
		this.pwdBtn.click();
		Assert.assertTrue(this.pwdBtn.isSelected());
	}
	
	//Enter valid credential in password textbox
	public void addPassword(String pwd){
		this.password.sendKeys(pwd);
		Assert.assertEquals(this.password.getAttribute("value"),pwd);
	}
	
	//Select Valid credentials from profile list box
	public void addProfile(){
		Select select = new Select(this.profile);
		select.selectByValue("1");
		Assert.assertEquals(this.profile.getAttribute("value"),"1");
	}
	
	//Click on Add button
	public void addUserBtn() throws InterruptedException {
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		this.addBtn.click();
		Thread.sleep(5000);
		Assert.assertTrue(this.message.getText().contains("The user has been added"));
	}
	
	
	
}
