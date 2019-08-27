package com.training.sanity.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.pom.AddCourse;

public class AddCourseTest extends LoginTests {

		//public WebDriver driver;
		private AddCourse addcourse;
		

		@Test(priority=2)
		public void AddNewCourse() throws InterruptedException {
			addcourse = new AddCourse(driver);
			
			//1. Click on Create a Course link
			addcourse.clickNewCourse();
			
			//2. Enter valid credential in Course name text box
			addcourse.sendCourseTitle("Upskill");
			
			//3. This step is invalid
			//4. Select Valid credentials from Category list box
			addcourse.selectCategory("Projects (PROJ)");
					
			//5. Enter valid credential in Course code text box
			addcourse.sendCourseCode("SeleniumWithJava");
			
			//6. Select Valid credentials from Language list box
			addcourse.selectLanguage("English");
			Thread.sleep(2000);
			
			//7. Click on Create this course button
			addcourse.clickCourseSubmit();
			
			//8. click on Add an introduction icon
			addcourse.clickAddIntroLink();
			
			//9. Enter valid credentials in text box
			addcourse.addIntroText("Selenium Reskill Program");
			
			//10. Click on Save intro text
			addcourse.clickIntroSubmit();
		}

}
