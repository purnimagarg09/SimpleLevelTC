package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UnsubscribeUser {
private WebDriver driver; 
	
	public UnsubscribeUser(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
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
		
	//Users icon
	@FindBy(xpath="//img[@title='Users']")
	private WebElement usersLink;
	
	//Student List table
	@FindBy(xpath="//*[@class='table table-bordered data_table']/tbody")
	private WebElement studentList;
	
	//1. click on Course created
	public void clickCourse(String courseName) {
			this.list.click();
			this.searchBox.sendKeys(courseName);
			this.searchBtn.click();
			int rows = this.courseList.size();
			if(rows==1)
				System.out.println("No matching course found");
			else {
				int i=1;
				do {
					String title=courseList.get(i).findElement(By.xpath("//td[2]")).getText();
					if(title.equalsIgnoreCase(courseName)) {
						courseList.get(i).findElement(By.xpath("//td[2]/a")).click();
						break;
					}
					else 
						i++;
						
				}while(i<rows);
				System.out.println("No matching course found");
			}
		}
		
	//2. Click on Course description icon
	public void clickUsersLink(){
			this.usersLink.click();
	}
	
	//3. Click on check box beside the learner details
	//4. Click on Unsubscribe button
	//5. Click on OK button of pop up window
	public void unsubscribeLearner(String learnerFirstName,String learnerLastName) throws InterruptedException {
		int noLearner = studentList.findElements(By.tagName("tr")).size();
		Thread.sleep(2000);
		if(noLearner==1)
			System.out.println("No students assigned to this course");
		else {
			for(int i=2;i<=noLearner;i++)
			{
				WebElement row = studentList.findElement(By.xpath(".//tr[" + i + "]"));
				String firstName=row.findElement(By.xpath(".//td[4]")).getText();	
				String lastName=row.findElement(By.xpath(".//td[5]")).getText();	
				if(firstName.equalsIgnoreCase(learnerFirstName) && lastName.equalsIgnoreCase(learnerLastName)) {
					//3. Click on check box beside the learner details
					row.findElement(By.xpath(".//td[1]/input")).click();
					Thread.sleep(2000);

					//4. Click on Unsubscribe button
					row.findElement(By.xpath(".//td[10]/a[4]")).click();
					Thread.sleep(2000);
					
					//5. Click on OK button of pop up window
					driver.switchTo().alert().accept();
					break;
				}		
					
			}
			System.out.println("No matching learner assigned to this course");
		}
	}
}
