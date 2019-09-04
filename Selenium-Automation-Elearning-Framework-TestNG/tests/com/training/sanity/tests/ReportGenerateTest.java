package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.pom.ReportGenerate;

public class ReportGenerateTest extends LoginTests{
	private ReportGenerate report;
	
	@Test(priority=2)
	public void sendReport() throws InterruptedException {
		report = new ReportGenerate(driver);
		
		//1. Click on Reporting Tab
		report.clickReporting();
		
		//2. click on Followed students link
		report.followStudents();
		
		//3. enter student (first) name in keyword textbox
		report.sendKeyword("test");
		
		//4. click on search button
		report.searchStudent();
		
		//5. click on >> icon of the student
		report.studDetailsIcon("test", "learner");
		
		//6. click on >> icon of the course
		report.courseDetailsIcon("course1");
		
		//7. click on quiz icon of the test section
		report.clickQuiz("test1");
				
		//8. Click on Send Email checkbox
		report.clickSendEmail();
				
		//9. click on correct test button
		report.clickCorrectTest();
				
		//. Click on Course Name link
		report.clickCourse();
	}
}
