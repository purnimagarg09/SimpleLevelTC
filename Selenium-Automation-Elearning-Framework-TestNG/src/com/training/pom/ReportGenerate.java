package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ReportGenerate {
	private WebDriver driver; 
	private JavascriptExecutor js;
	private int noLearner;
	
	public ReportGenerate(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		js=(JavascriptExecutor)driver;
	}
	
	//Reporting tab
	@FindBy(xpath="//*[@title='Reporting']")
	private WebElement reportingTab;
	
	//Reporting page title
	@FindBy(xpath="//*[@id='cm-content']/div/ul/li")
	private WebElement reportTitle;
	
	//Followed Students Link
	@FindBy(xpath="//*[@id='main_content']/div[3]/div/div/div/div/div/a")
	private WebElement followedStud;
	
	//Learner page title
	@FindBy(xpath="//*[@id='cm-content']/div/h3")
	private WebElement learnerTitle;
	
	//keyword textbox
	@FindBy(id="search_user_keyword")
	private WebElement keyWord;
	
	//Search button
	@FindBy(id="search_user_submit")
	private WebElement searchBtn;
	
	//Student List on Learners Page
	@FindBy(xpath="//table[contains(@id,'tracking_student')]/tbody")
	private WebElement studentList;
			
	//Course List 
	@FindBy(xpath="//*[@id='cm-content']/div/div[7]/table/tbody")
	private WebElement courses;
	
	//Test List on Learner Details Page
	@FindBy(xpath="//*[@id='cm-content']/div/div[8]/table/tbody")
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
	
	//1. click on Reporting tab
	public void clickReporting(){
		this.reportingTab.click();
		Assert.assertEquals(this.reportTitle.getText(), "Reporting");
	}
	
	//2. click on Followed students link
	public void followStudents() throws InterruptedException{
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, 200)");	
		this.followedStud.click();
		Assert.assertEquals(this.learnerTitle.getText(), "Learners");
	}
	
	//3. enter student name in keyword text box
	public void sendKeyword(String keyword) throws InterruptedException{
		Thread.sleep(3000);
		this.keyWord.sendKeys(keyword);
		Assert.assertEquals(this.keyWord.getAttribute("value"), keyword);
	}
	
	//4. click on search button
	public void searchStudent(){
		this.searchBtn.click();
		noLearner = this.studentList.findElements(By.tagName("tr")).size();
		if(noLearner==1)
			Assert.fail("No students assigned to this course");
	}
	
	//5. click on >> icon of the student
	public void studDetailsIcon(String learnerFirstName,String learnerLastName) throws InterruptedException {
		Thread.sleep(3000);
		boolean noStudents = true;
		for(int i=2;i<=noLearner;i++){
				WebElement row = studentList.findElement(By.xpath(".//tr[" + i + "]"));
				String firstName=row.findElement(By.xpath(".//td[1]")).getText();	
				String lastName=row.findElement(By.xpath(".//td[2]")).getText();	
				if(firstName.equalsIgnoreCase(learnerFirstName) && lastName.equalsIgnoreCase(learnerLastName)) {
					noStudents = false;
					
					// Click on >> icon
					row.findElement(By.xpath(".//td[5]/a[2]")).click();
					Thread.sleep(3000);
					break;
				}							
			}
			if(noStudents) {
				Assert.fail("No matching learner assigned to this course");
			}
	}
	
	//6. click on >> icon of the course
	public void courseDetailsIcon(String courseName) throws InterruptedException {
		int noCourses = this.courses.findElements(By.tagName("tr")).size();
		boolean noCourse = true;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		if(noCourses==0)
			System.out.println("No matching course found");
		else {
			for(int i=1;i<=noCourses;i++){
				WebElement row = courses.findElement(By.xpath(".//tr[" + i + "]"));
				String title=row.findElement(By.xpath("//td[1]")).getText();	
				if(title.equalsIgnoreCase(courseName)) {
					noCourse = false;
					Thread.sleep(3000);
					this.courses.findElement(By.xpath("//td[7]/a")).click();
					break;
				}
				else 
					i++;					
			}
			if(noCourse) {
				Assert.fail("No matching course found");
			}
		}
	}
	
	//7. click on quiz icon of the test section
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
							Thread.sleep(2000);
							// Click on Quiz icon
							row.findElement(By.xpath(".//td[5]/a")).click();
							break;
						}							
			}
			if(noTests) {
					Assert.fail("No matching tests submitted by the learner");
			}
		}
	}
			
	//8. Click on Send Email checkbox
	public void clickSendEmail() throws InterruptedException{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
		this.sendEmail.click();
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");			
		Thread.sleep(2000);
	}
			
	//9. click on correct test button
	public void clickCorrectTest(){
		this.correctTest.click();
		Assert.assertEquals(this.textEmail.getText(), "Message Sent");
	}
			
	//10. Click on Course Name link
	public void clickCourse() throws InterruptedException{
		Thread.sleep(2000);
		this.course.click();
	}
	
}