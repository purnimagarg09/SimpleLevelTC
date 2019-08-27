package com.training.sanity.tests;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.pom.AddCourse;
import com.training.pom.CourseDescription;

public class CourseDescriptionTest extends LoginTests {
	private CourseDescription courseDes;
	
	@Test(priority=2)
	public void EnterCourseDesc() throws InterruptedException {
		courseDes = new CourseDescription(driver);
		
		//1. click on Course created
		courseDes.clickCourse("Upskill");
		
		//2. Click on Course description icon
		courseDes.clickCourseDescLink();
		
		//3. Click on Description icon
		courseDes.clickDescLink();
		
		//4. Enter valid credentials in Title textbox
		courseDes.sendDescTitle("Test Course created");
		
		//5. Enter valid credentials in Content text box
		courseDes.sendDescContent("Test Course Contents added");
		
		//6. Click on Save button
		courseDes.clickDescSave();
	}
}
