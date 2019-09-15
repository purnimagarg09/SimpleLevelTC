package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.dao.ELearningDAO;
import com.training.dataproviders.LoginDataProviders;
import com.training.pom.AddUserDB;

public class AddUserDBTest extends LoginTests {
	private AddUserDB addUser;
	
	//Using DB provide for test data input
	@Test(dataProvider = "db-inputs1", dataProviderClass = LoginDataProviders.class, priority = 2)
	public void addUserDB(String firstname,String lastname,String email,String phone,String login,String password, String profile ) throws InterruptedException {
		addUser = new AddUserDB(driver);
		
		//Click on Administration tab
		addUser.clickAdministration();
		Thread.sleep(2000);
		
		//Click on Add a user link
		addUser.clickAddUser();
		Thread.sleep(2000);
		
		//Enter valid credential in First name textbox
		addUser.addFirstName(firstname);
		Thread.sleep(2000);
		
		//Enter valid credential in Last name textbox
		addUser.addLastName(lastname);
		Thread.sleep(2000);
		
		//Enter valid credential in e-mail textbox
		addUser.addEmail(email);
		Thread.sleep(2000);
		
		//Converting Stringvalue into long and Enter valid credential in phone textbox
		long ph= Double.valueOf(phone).longValue();
		addUser.addPhone(ph);
		Thread.sleep(2000);
		
		//Enter valid credential in Login textbox
		addUser.addLogin(login);
		Thread.sleep(2000);
		
		//Click on Enter password radio button
		addUser.clickPwdBtn();
		Thread.sleep(2000);
		
		//Enter valid credential in password textbox
		addUser.addPassword(password);
		Thread.sleep(2000);
		
		//Select Valid credentials from profile list box
		addUser.addProfile(profile);
		Thread.sleep(2000);
		//Click on Add button
		addUser.addUserBtn();
		
		//Login and Database and retrieve the values from user details tables
		new ELearningDAO().getUsers().forEach(System.out :: println);
	}
}
