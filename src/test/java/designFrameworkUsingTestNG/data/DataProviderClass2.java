package designFrameworkUsingTestNG.data;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class DataProviderClass2 {
	@DataProvider
	public static Object[][] getDataFromJasonFile() throws JsonMappingException, JsonProcessingException {
		String rootDir = System.getProperty("user.dir");
		String jsonFilePath = rootDir + "\\src\\test\\java\\designFrameworkUsingTestNG\\data\\purchaseOrder.jason";
		DataReader dataReaderObj = new DataReader();
		List<HashMap<String,String>> jsonData = dataReaderObj.getJsonDataToMap(jsonFilePath);
		
		Object data[][] = new Object[jsonData.size()][1];
		
		for(int i=0; i<jsonData.size(); i++) {
			data[i][0] = jsonData.get(i);
		}
		
		return data;
	}
}
