package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.pom.CreateGroup;

public class CreateGroupTest extends LoginTests {
	private CreateGroup group;
	
	@Test(priority=2)
	public void createGroup() throws InterruptedException {
		group = new CreateGroup(driver);
		
		//Navigate to the course details
		group.courseDetails("Course1");
		
		//1. Click on Groups icon
		group.clickGroupsIcon();
		
		//2. click on create new group icon
		group.addGroupIcon();
		
		//3. Enter valid credentials in Number of groups to create textbox
		group.addGroup("1");
		
		//4. click on proceed to group button
		group.addGroupSubmit();
		
		//5. Enter valid credentials in Group name textbox
		group.addGroupDetails("IBM Upskill");
		
		//6. click on create groups button
		group.createGroupSubmit();
		
		//7. click on group member icon
		group.clickGroupMember();
				
		//8. select the students in leftside window
		group.addGroupMembers();
				
		//9. click on -> icon
		group.moveSelected();
				
		//10. click on save settings button
		group.SaveSettings();
		
		//11. click on edit settings icon
		group.clickEditSettings();
		
		//12. click on checkbox beside Learners are allowed to self-register in groups in Registration section
		group.clickSelfRegister();
		
		//13. click on checkbox beside Learners are allowed to self-unregister in groups in Registration section
		group.clickSelfUnRegister();
		
		//14. click on Edit button
		group.SaveSettings();
	}

}
