package com.training.pom;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CourseDescription {
	
	private WebDriver driver; 
	
	public CourseDescription(WebDriver driver) {
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
	
	//CourseDescription icon
	@FindBy(xpath="//img[@title='Course description']")
	private WebElement courseDescLink;
	
	//Description icon
	@FindBy(xpath="//img[@title='Description']")
	private WebElement descLink;
	
	//Description Title text box
	@FindBy(id="course_description_title")
	private WebElement descTitle;
	
	//xpath for 'Content field' frame
	@FindBy(xpath="//iframe[@class='cke_wysiwyg_frame cke_reset']")
	private WebElement contentFrame;
		
	//xpath for 'Content' text box. Need to enter text to body tag and not 'p'tag
	@FindBy(xpath="//*[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	private WebElement contentText;
		
	@FindBy(id="course_description_submit")
	private WebElement descSave;
	
	//1. click on Course created
	public void clickCourse(String courseName) {
		this.list.click();
		this.searchBox.sendKeys(courseName);
		this.searchBtn.click();
		int rows = this.courseList.size();
		System.out.println(rows);		
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
	public void clickCourseDescLink() {
			this.courseDescLink.click();
	}
	
	//3. Click on Description icon
	public void clickDescLink() {
		this.descLink.click();
	}
	
	//4. Enter valid credentials in Title textbox
	public void sendDescTitle(String courseDescTitle) {
		this.descTitle.clear();
		this.descTitle.sendKeys(courseDescTitle);
	}
	
	//5. Enter valid credentials in Content text box
	public void sendDescContent(String courseDescContent) throws InterruptedException {
		Thread.sleep(10000);
		driver.switchTo().frame(contentFrame);
		this.contentText.clear();
		this.contentText.sendKeys(courseDescContent);
	}
	
	//6. Click on Save button
	public void clickDescSave() {
		driver.switchTo().defaultContent();
		this.descSave.click(); 
	}
}
