package api.utilities;

import java.io.IOException;

public class DataProvider {
//	1/ getAllData method
//	2/ get petId method
	
@org.testng.annotations.DataProvider(name="Data")
public String[][] getAllData() throws IOException {

		
		String path = System.getProperty("user.dir") + "\\testData\\PetData.xlsx";
		
		XLUtility xlUtility = new XLUtility(path);
		int rows = xlUtility.getCountRows("Sheet1");
		
		int cols = xlUtility.getCountCols("Sheet1", 2);
		String[][] data = new String[rows-1][cols]; 
		for (int i = 2; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i-2][j] = xlUtility.getCellData("Sheet1", i, j); 
			}
		}
		return data;
	}
	
	@org.testng.annotations.DataProvider(name = "PetID")
	public String[] getPetId() throws IOException {
String path = System.getProperty("user.dir") + "\\testData\\PetData.xlsx";

		XLUtility xl = new XLUtility(path);
		int rows = xl.getCountRows("Sheet1");
		
		String[] petId = new String[rows-1];
		
		for (int i = 2; i <= rows; i++) {
			
			petId[i-2] = xl.getCellData("Sheet1",i, 1);
			
		}
		return petId;
}
}
