package designFrameworkUsingTestNG.data;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	@DataProvider
	public List<HashMap<String, String>> getJsonDataToMap(String filePath)
			throws JsonMappingException, JsonProcessingException {

		String jsonFilePath = filePath;
		String jsonContent;
// convert jason to string (inbuilt)
		try {
			jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
		} catch (Exception e) {
			throw new RuntimeException("Error reading JSON file", e);
		}

// convert string into list of Hashmaps (Must use jackson dataBind dependency)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

}
