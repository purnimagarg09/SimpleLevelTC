package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCourse {
	private WebDriver driver; 
	
	public AddCourse(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//'Create a course' link xpath
	@FindBy(xpath="//*[@class='panel panel-default block-admin-courses']/div[2]/div[2]/ul/li[2]/a")
	private WebElement createCourse;
	
	//Course Title field
	@FindBy(id="update_course_title")
	private WebElement courseTitle;
	
	//Course Category field
	@FindBy(xpath="//*[@id='update_course_category_code']")
	private WebElement category;
	
	//Course Code field
	@FindBy(id="visual_code")
	private WebElement courseCode;
	
	//Course language field
	@FindBy(xpath="//select[@id='update_course_course_language']")
	private WebElement language;
	
	//Course Submit button
	@FindBy(id="update_course_submit")
	private WebElement courseSubmit;
	
	//xpath for new course added alert
	@FindBy(xpath="//div[@class='alert alert-success']/a")
	private WebElement newCourse;
	
	//xpath for 'Add an Introduction' button
	@FindBy(xpath="//*[@class='help-course']/a")
	private WebElement introIcon;
	
	//xpath for 'Add an Introduction' frame
	@FindBy(xpath="//iframe[@class='cke_wysiwyg_frame cke_reset']")
	private WebElement introFrame;
	
	//xpath for 'Add an Introduction' text box. Need to enter text to body tag and not 'p'tag
	@FindBy(xpath="//*[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	private WebElement introText;
	
	@FindBy(id="introduction_text_intro_cmdUpdate")
	private WebElement introSubmit;
	
	
	//1. Click on Create a Course link
	public void clickNewCourse() {
			this.createCourse.click(); 
	}
		
	//2. Enter valid credential in Course name textbox
	public void sendCourseTitle(String courseName) {
		this.courseTitle.clear();
		this.courseTitle.sendKeys(courseName);
	}
	
	//4. Select Valid credentials from Category list box
	public void selectCategory(String categoryName) {
		Select cat = new Select(this.category);
		cat.selectByVisibleText(categoryName);
	}
	
	//5. Enter valid credential in Course code textbox
	public void sendCourseCode(String codeName) {
			this.courseCode.clear();
			this.courseCode.sendKeys(codeName);
	}
	
	//6. Select Valid credentials from Language list box
	public void selectLanguage(String languageName) {
			Select lang = new Select(this.language);
			lang.selectByVisibleText(languageName);
	}
	
	//7. Click on Create this course button
	public void clickCourseSubmit() {
		this.courseSubmit.click(); 
	}
	
	//8. click on Add an introduction icon
	public void clickAddIntroLink() throws InterruptedException {
		Thread.sleep(3000);
		this.newCourse.click();
		this.introIcon.click();

	}
	
	//9. Enter valid credentials in textbox
	public void addIntroText(String introducText) throws InterruptedException {
		Thread.sleep(10000);
		driver.switchTo().frame(introFrame);
		this.introText.clear();
		this.introText.sendKeys(introducText);
	}
	
	//10. Click on Save intro text
	public void clickIntroSubmit() {
		driver.switchTo().defaultContent();
		this.introSubmit.click(); 
		
	}
}
