import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parse2
{
  public static void main(String[] args) throws Exception
  {
		String jsonInput = "";
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader("/filepath/tweets.txt"));
			while ((line = br.readLine()) != null) {
				jsonInput += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} 
	  
    JSONObject outerObject = new JSONObject(jsonInput);
    JSONObject innerObject = outerObject.getJSONObject("Tweets");
    JSONArray jsonArray = innerObject.getJSONArray("Array1");
    for (int i = 0, size = jsonArray.length(); i < size; i++)
    {
      JSONObject objectInArray = jsonArray.getJSONObject(i);

      String[] elementNames = JSONObject.getNames(objectInArray);
      System.out.printf("ELEMENTS IN CURRENT OBJECT:\n", elementNames.length);
      for (String elementName : elementNames)
      {
        String value = objectInArray.getString(elementName);
        System.out.printf("name=%s, value=%s\n", elementName, value);
      }
      System.out.println();
    }
  }
}
