package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.pom.AddInvalidUser;

public class AddInvalidUserTest extends LoginTests {
	private AddInvalidUser addUser;

	@Test(dataProvider = "excel-inputs-invalid", dataProviderClass = LoginDataProviders.class, priority = 2)
	public void addInvalidUser(String firstname,String lastname,String email,String phone,String login,String password ) throws InterruptedException {
		addUser = new AddInvalidUser(driver);
		addUser.clickAdministration();
		Thread.sleep(2000);
		addUser.clickAddUser();
		Thread.sleep(2000);
		addUser.addFirstName(firstname);
		Thread.sleep(2000);
		addUser.addLastName(lastname);
		Thread.sleep(2000);
		addUser.addEmail(email);
		Thread.sleep(2000);
		addUser.addPhone(phone);
		Thread.sleep(2000);
		addUser.addLogin(login);
		Thread.sleep(2000);
		addUser.clickPwdBtn();
		Thread.sleep(2000);
		addUser.addPassword(password);
		Thread.sleep(2000);
		addUser.addProfile();
		Thread.sleep(2000);
		addUser.addUserBtn();
	}
}
