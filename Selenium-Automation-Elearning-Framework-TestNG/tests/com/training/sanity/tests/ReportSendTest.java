package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.pom.CourseDescription;
import com.training.pom.ReportSend;

public class ReportSendTest extends LoginTests{
	private ReportSend report;
	
	@Test(priority=2)
	public void sendReport() throws InterruptedException {
		report = new ReportSend(driver);
		
		//1. Click on Reporting icon
		report.clickReporting("course1");
		
		//2. click on >> icon in details section of particular student
		report.clickDetailsIcon("test", "learner");
		
		//3. click on quiz icon of the test section
		report.clickQuiz("test1");
		
		//4. Click on Send Email checkbox
		report.clickSendEmail();
		
		//5. click on correct test button
		report.clickCorrectTest();
		
		//6. Click on Course Name link
		report.clickCourse();
	}
}
