package com.training.pom;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ReportSend {
		private WebDriver driver; 
		private JavascriptExecutor js;
		
		public ReportSend(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
			js=(JavascriptExecutor)driver;
		}
	
		//Course list link on Admin Page
		@FindBy(xpath="//*[@class='panel panel-default block-admin-courses']/div[2]/div[2]/ul/li[1]/a")
		private WebElement list;
		
		//Search text box
		@FindBy(id="course-search-keyword")
		private WebElement searchBox;
		
		//Search button
		@FindBy(id="search_simple_submit")
		private WebElement searchBtn;
		
		//Course List table
		@FindBy(xpath="//*[@id='course-list']/tbody/tr")
		private List<WebElement> courseList;
		
		//Student List on Reporting Page
		@FindBy(xpath="//*[contains(@id,'users_tracking')]/tbody")
		private WebElement studentList;
		
		//Test List on Learner Details Page
		@FindBy(xpath="//*[@id='cm-content']/div/div[9]/table/tbody")
		private WebElement testList;
		
		//Send Email checkbox
		@FindBy(xpath="//*[@name='send_notification']")
		private WebElement sendEmail;
		
		// Email sent message
		@FindBy(xpath="//div[@class='alert alert-info']")
		private WebElement textEmail;
		
		// Correct Test button
		@FindBy(id="form-email_submit")
		private WebElement correctTest;
		
		//Course link after correct Test submission
		@FindBy(xpath="//*[@id='cm-content']/div/ul/li/a")
		private WebElement course;
		
		
		//1. click on Course created
		public void clickReporting(String courseName) {
			this.list.click();
			this.searchBox.sendKeys(courseName);
			this.searchBtn.click();
			int rows = this.courseList.size();
			boolean noCourse = true;
			if(rows==1)
				System.out.println("No matching course found");
			else {
				int i=1;
				do {
					String title=courseList.get(i).findElement(By.xpath("//td[2]")).getText();	
					if(title.equalsIgnoreCase(courseName)) {
						noCourse = false;
						courseList.get(i).findElement(By.xpath("//td[8]/a[3]")).click();
						break;
					}
					else 
						i++;
						
				}while(i<rows);
				if(noCourse) {
					Assert.fail("No matching course found");
				}
			}
		}
		
		//2. click on >> icon in details section of particular student
		public void clickDetailsIcon(String learnerFirstName,String learnerLastName) throws InterruptedException {
			int noLearner = this.studentList.findElements(By.tagName("tr")).size();
			Thread.sleep(3000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			boolean noStudents = true;
			if(noLearner==1)
				System.out.println("No students assigned to this course");
			else {
				for(int i=2;i<=noLearner;i++)
				{
					WebElement row = studentList.findElement(By.xpath(".//tr[" + i + "]"));
					String firstName=row.findElement(By.xpath(".//td[2]")).getText();	
					String lastName=row.findElement(By.xpath(".//td[3]")).getText();	
					if(firstName.equalsIgnoreCase(learnerFirstName) && lastName.equalsIgnoreCase(learnerLastName)) {
						noStudents = false;
						
						// Click on >> icon
						row.findElement(By.xpath(".//td[16]/center/a")).click();
						Thread.sleep(3000);
						break;
					}							
				}
				if(noStudents) {
					Assert.fail("No matching learner assigned to this course");
				}
			}
		}
		
		//3. click on quiz icon of the test section
		public void clickQuiz(String testName) throws InterruptedException {
			int noTest = this.testList.findElements(By.tagName("tr")).size();
			Thread.sleep(2000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			boolean noTests = true;
			if(noTest==0)
				System.out.println("No tests submitted by the learner");
			else {
				for(int i=1;i<=noTest;i++)
				{
					WebElement row = testList.findElement(By.xpath(".//tr[" + i + "]"));
					String test=row.findElement(By.xpath(".//td[1]")).getText();							
					if(test.equalsIgnoreCase(testName)) {
						noTests = false;
						
						// Click on Quiz icon
						row.findElement(By.xpath(".//td[5]/a")).click();
						Thread.sleep(2000);
						break;
					}							
				}
				if(noTests) {
					Assert.fail("No matching tests submitted by the learner");
				}
			}
		}
		
		//4. Click on Send Email checkbox
		public void clickSendEmail() throws InterruptedException{
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(2000);
			this.sendEmail.click();
			Thread.sleep(3000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");			
			Thread.sleep(2000);
		}
		
		//5. click on correct test button
		public void clickCorrectTest(){
			this.correctTest.click();
			Assert.assertEquals(this.textEmail.getText(), "Message Sent");
		}
		
		//6. Click on Course Name link
		public void clickCourse() throws InterruptedException{
			Thread.sleep(2000);
			this.course.click();
		}
		
}
