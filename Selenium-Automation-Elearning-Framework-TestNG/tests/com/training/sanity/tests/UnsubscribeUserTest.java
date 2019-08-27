package com.training.sanity.tests;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.pom.CourseDescription;
import com.training.pom.UnsubscribeUser;

public class UnsubscribeUserTest extends LoginTests{
	private UnsubscribeUser unsubUser;
		
	@Test(priority=2)
	public void EnterCourseDesc() throws InterruptedException {
		unsubUser = new UnsubscribeUser(driver);
		
		//1. click on Course created
		unsubUser.clickCourse("Course1");
		
		//2. Click on Users icon
		unsubUser.clickUsersLink();
		
		//3. Click on checkbox beside the learner details
		//4. Unsubscribe user giving its First and last name details
		//5. Click on OK button of pop up window
		unsubUser.unsubscribeLearner("anil", "more");
		//unsubUser.unsubscribeLearner("test", "learner");
	}

}
