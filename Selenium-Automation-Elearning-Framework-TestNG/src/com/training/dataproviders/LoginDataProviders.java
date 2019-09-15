package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.bean.UserBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	//database
	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}		
		return result;
	}
	
	@DataProvider(name = "db-inputs1")
	public Object [][] getDBUser() {

		List<UserBean> list = new ELearningDAO().getUsers(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(UserBean temp : list){
			Object[]  obj = new Object[7]; 
			obj[0] = temp.getFirstname();
			obj[1] = temp.getLastname();
			obj[2] = temp.getEmail();
			obj[3] = temp.getPhone();
			obj[4] = temp.getLogin();
			obj[5] = temp.getPassword();
			obj[6] = temp.getProfile();
			result[count ++] = obj; 
		}		
		return result;
	}
	
	//Apache POI
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
	//	String fileName ="C:/Users/Naveen/Desktop/Testing.xlsx"; 
		String fileName = "C:\\Users\\PurnimaGarg\\Desktop\\Selenium-Reskill\\TestUser.xlsx";
		String SheetName = "Sheet1";
		return new ApachePOIExcelRead().getExcelContent(fileName,SheetName); 
	}
	
	// for multiple sheets reading
	 @DataProvider(name = "excel-inputs-invalid")
	public Object[][] getExcelData1(){
		 String fileName = "C:\\Users\\PurnimaGarg\\Desktop\\Selenium-Reskill\\InvalidData.xlsx";
			String SheetName = "Sheet1";
			return new ApachePOIExcelRead().getExcelContent(fileName,SheetName); 
	 }
	//jxl input
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
}
