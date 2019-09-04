package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CreateGroup {
	private WebDriver driver; 
	private JavascriptExecutor js;
	private static String GroupName;
	
	public CreateGroup(WebDriver driver) {
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
	
	//Groups icon
	@FindBy(xpath="//img[@title='Groups']")
	private WebElement groupsIcon;
	
	//Add a new group icon
	@FindBy(xpath="//img[@title='Create new group(s)']")
	private WebElement addGroup;
	
	//Number of groups to create textbox
	@FindBy(id="create_groups_number_of_groups")
	private WebElement numberOfGroup;
	
	//Proceed to create group button
	@FindBy(id="create_groups_submit")
	private WebElement createGroup;
	
	//New Group details table
	@FindBy(xpath="//*[@id='create_groups_step2']/div/table/tbody")
	private WebElement createGroupTable;
	
	//Create group button
	@FindBy(id="create_groups_step2_submit")
	private WebElement createGroupSubmit;
	
	//Groups table
	@FindBy(xpath="//*[contains(@id,'group_category')]/tbody")
	private WebElement groupTable;
	
	//move selected members icon
	@FindBy(id="group_members_rightSelected")
	private WebElement selectMembers;
	
	//Group members list
	@FindBy(xpath="//select[@id='group_members']")
	private WebElement groupMembers;
	
	//Save settings button
	@FindBy(id="group_edit_submit")
	private WebElement saveSettings;
	
	//Save Seetings alert message
	@FindBy(xpath="//*[@class='alert alert-success']")
	private WebElement saveMessage;
	
	//Learners are allowed to self-register in groups checkbox
	@FindBy(xpath="//input[@name='self_registration_allowed']")
	private WebElement selfRegister;
	
	//Learners are allowed to self-unregister in groups checkbox
	@FindBy(xpath="//input[@name='self_unregistration_allowed']")
	private WebElement selfUnRegister;
	
	//Navigate to Course Details page to create a Group
	public void courseDetails(String courseName) {
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
					courseList.get(i).findElement(By.xpath("//td[2]/a")).click();
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
	
	//1. Click on Groups icon
	public void clickGroupsIcon() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		this.groupsIcon.click();
		Assert.assertTrue(this.driver.getTitle().contains("Groups"));
	}
	
	//2. click on create new group icon
	public void addGroupIcon() throws InterruptedException {
		Thread.sleep(2000);
		this.addGroup.click();
		Assert.assertTrue(this.driver.getTitle().contains("New groups creation"));
	}
	
	//3. Enter valid credentials in Number of groups to create textbox
	public void addGroup(String number) throws InterruptedException {
		Thread.sleep(2000);
		this.numberOfGroup.clear();
		this.numberOfGroup.sendKeys(number);
	}
	
	//4. click on proceed to group button
	public void addGroupSubmit() throws InterruptedException {
		Thread.sleep(2000);
		this.createGroup.click();
	}	
	
	//5. Enter valid credentials in Group name textbox
	public void addGroupDetails(String groupName) throws InterruptedException {
		
		this.GroupName = groupName;
		WebElement group = this.createGroupTable.findElement(By.xpath(".//tr[2]/td[2]/input"));
		group.clear();
		Thread.sleep(2000);
		group.sendKeys(groupName);
		Assert.assertEquals(group.getAttribute("value"), groupName);
	}
	
	//6. click on create groups button
	public void createGroupSubmit() throws InterruptedException {
		Thread.sleep(2000);
		this.createGroupSubmit.click();
	}	
	
	//7. click on group member icon
	public void clickGroupMember() throws InterruptedException {
		Thread.sleep(3000);
		boolean noGroup = true;
		int rows = this.groupTable.findElements(By.tagName("tr")).size();
		if(rows==1) {
			Assert.fail("No group added to this course");
		}
		for(int i=2;i<=rows;i++){
				
				WebElement group = this.groupTable.findElement(By.xpath(".//tr[" + i + "]"));			
				String name = group.findElement(By.xpath(".//td[2]/a")).getText();	
				if(GroupName.equalsIgnoreCase(name)) {
					noGroup = false;
					
					// Click on Group member icon
					group.findElement(By.xpath(".//td[5]/a[3]")).click();
					Thread.sleep(3000);
					break;
				}							
			}
			if(noGroup) {
				Assert.fail("No matching group added to this course");
			}
	}
	
	//8. select the students in leftside window
	public void addGroupMembers() throws InterruptedException {
		Thread.sleep(2000);
		Select select = new Select(this.groupMembers);
		select.selectByIndex(1);
		select.selectByIndex(3);
		Thread.sleep(2000);
	}	
	
	//9. click on -> icon
	public void moveSelected() {
		this.selectMembers.click();
	}	
	
	//10. click on save settings button
	public void SaveSettings() throws InterruptedException {
		Thread.sleep(2000);
		this.saveSettings.click();
		Assert.assertEquals(this.saveMessage.getText(), "Group settings modified");
	}	
	
	//11. click on edit settings icon
	public void clickEditSettings() throws InterruptedException {
		Thread.sleep(3000);
		boolean noGroup = true;
		int rows = this.groupTable.findElements(By.xpath(".//tr")).size();
		if(rows==1) {
			Assert.fail("No group added to this course");
		}
		for(int i=2;i<=rows;i++){
				WebElement group = this.groupTable.findElement(By.xpath(".//tr[" + i + "]"));
				String name = group.findElement(By.xpath(".//td[2]/a")).getText();	
				
				if(GroupName.equalsIgnoreCase(name)) {
					noGroup = false;
					
					// Click on Edit Group settings icon
					group.findElement(By.xpath(".//td[5]/a[1]")).click();
					Thread.sleep(3000);
					break;
				}							
		}
		if(noGroup) {
			Assert.fail("No matching group added to this course");
		}
		Assert.assertTrue(this.driver.getTitle().contains("Edit this group"));
	}
	
	//12. click on checkbox beside Learners are allowed to self-register in groups in Registration section
	public void clickSelfRegister() throws InterruptedException {
		Thread.sleep(2000);
		this.selfRegister.click();
	}	
	
	//13. click on checkbox beside Learners are allowed to unregister themselves from groups of Registration section
	public void clickSelfUnRegister() throws InterruptedException {
		Thread.sleep(2000);
		this.selfUnRegister.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}	
}
