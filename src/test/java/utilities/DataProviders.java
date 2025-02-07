package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	//Data Provider 1
	
	@DataProvider(name="LoginData")
	public String [][] getDate() throws IOException{
		String path=".\\testData\\data1.xlsx"; // Taking Excel file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path); // Creating an object from Excel Utility 
		
		int totalRows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][]=new String [totalRows][totalcols]; // created for two dimentional array
		
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<totalcols;j++) {
				loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
	}
}
